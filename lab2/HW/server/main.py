import http.client as http
import json
import random

from flask import Flask, request, abort

app = Flask(__name__)


class Bikes:
    URL = "api.citybik.es"
    NETWORKS = "/v2/networks"


class Country:
    URL = "www.restcountries.eu"
    NAME = "/rest/v2/name/"
    ALPHA = "/rest/v2/alpha/"
    REGION = "/rest/v2/region/"


def region_request(inp, minbikes, maxbikes):
    conn = http.HTTPConnection(Country.URL)
    if conn:
        app.logger.info('Connected to %s', Country.URL)
    else:
        app.logger.info('Failed to connect %s', Country.URL)
        return {}
    endpoint = Country.REGION + inp
    conn.request("GET", endpoint)
    resp = conn.getresponse()
    if resp.status == 200:
        data = json.loads(resp.read().decode('utf-8'))
        data = random.sample(data, 2)  # GET ONLY 2 countries at random, due to long queries
        countries = [country['name'] for country in data]
    else:
        app.logger.warning('Could not make request to endpoint %s', endpoint)
        return {}
    if conn:
        app.logger.info('Disconnected from %s', Country.URL)
        conn.close()
    return country_request(countries, minbikes, maxbikes)


def country_request(inp, minbikes, maxbikes):
    result = {}
    codes = []
    conn = http.HTTPConnection(Country.URL)
    if conn:
        app.logger.info('Connected to %s', Country.URL)
    else:
        app.logger.info('Failed to connect %s', Country.URL)
        return result
    for country in inp:
        endpoint = Country.NAME + country
        conn.request("GET", endpoint)
        resp = conn.getresponse()
        if resp.status == 200:
            data = json.loads(resp.read().decode('utf-8'))
            if len(data) != 1:
                app.logger.warning('Ambiguity with country %s skipping', country)
            data = data[0]
            country_info = {}
            country = data['name']
            country_info['area'] = data['area']
            country_info['gini'] = data['gini']
            country_info['population'] = data['population']
            code = data['alpha2Code']
            codes.append((country, code))
            result[country] = country_info
        else:
            app.logger.warning('Could not make request to endpoint %s skipping', endpoint)
    if conn:
        app.logger.info('Disconnected from %s', Country.URL)
        conn.close()
    for country, cities in get_cities_info(codes, minbikes, maxbikes):
        result[country]['cities'] = cities
    return result


def city_request(inp, minbikes, maxbikes):
    cities_info = []
    conn = http.HTTPConnection(Bikes.URL)
    if conn:
        app.logger.info('Connected to %s', Bikes.URL)
        conn.request("GET", Bikes.NETWORKS)
        resp = conn.getresponse()
        if resp.status == 200:
            networks = json.loads(resp.read().decode('utf-8'))
            for network in networks['networks']:
                if network['location']['city'] in inp:
                    city = network['location']['city']
                    bikes = count_bikes(conn, network['href'], minbikes, maxbikes)
                    cities_info.append((city, bikes))
        else:
            app.logger.warning('Failed to request', Bikes.NETWORKS)
    else:
        app.logger.info('Failed to connect %s', Bikes.URL)
    if conn:
        app.logger.info('Disconnecting from %s', Bikes.URL)
        conn.close()
    return cities_info


def get_cities_info(codes, minbikes, maxbikes):
    alpha_codes = {code for _, code in codes}
    cities_dict = {code: [] for code in alpha_codes}
    conn = http.HTTPConnection(Bikes.URL)
    if conn:
        app.logger.info('Connected to %s', Bikes.URL)
        conn.request("GET", Bikes.NETWORKS)
        resp = conn.getresponse()
        if resp.status == 200:
            networks = json.loads(resp.read().decode('utf-8'))
            for network in networks['networks']:
                code = network['location']['country']
                if code in alpha_codes:
                    city = network['location']['city']
                    bikes = count_bikes(conn, network['href'], minbikes, maxbikes)
                    cities_dict[code].append((city, bikes))
        else:
            app.logger.warning('Failed to request', Bikes.NETWORKS)
    else:
        app.logger.info('Failed to connect %s', Bikes.URL)
    if conn:
        app.logger.info('Disconnecting from %s', Bikes.URL)
        conn.close()
    cities_list = [(country, cities_dict[code]) for country, code in codes]
    return cities_list


def count_bikes(conn, endpoint, minbikes, maxbikes):
    bikes = 0
    conn.request("GET", endpoint)
    if conn:
        app.logger.info('Connected to %s', endpoint)
        resp = conn.getresponse()
        if resp.status == 200:
            stations = json.loads(resp.read().decode('utf-8'))
            bikes = sum(station['free_bikes'] for station in stations['network']['stations'] if
                        minbikes <= station['free_bikes'] <= maxbikes)
        else:
            app.logger.warning('Failed to request', Bikes.NETWORKS)
    else:
        app.logger.warning('Failed to connect', endpoint)
    return bikes


def check_params(option, inp, minbikes, maxbikes):
    if option not in {'region', 'country', 'city'}:
        return 400, "Wrong option provided, option should be one of 'region', 'country', 'city'"
    try:
        not minbikes or float(minbikes)
        not maxbikes or float(maxbikes)
    except ValueError:
        return 400, "Minbikes and maxbikes should be float or empty"
    if option == 'region' and inp not in {'africa', 'americas', 'asia', 'europe', 'oceania'}:
        return 400, "Wrong option provided, region should be one of 'africa', 'americas', 'asia', 'europe', 'oceania'"
    return None


@app.route('/rest/bikes', methods=['POST'])
def calculate():
    attrs = ['option', 'input', 'minbikes', 'maxbikes']
    if request.is_json:
        data = request.get_json()
        option, inp, minbikes, maxbikes = [data[attr] for attr in attrs]
    else:
        option, inp, minbikes, maxbikes = [request.form.get(attr) for attr in attrs]
    msg = check_params(option, inp, minbikes, maxbikes)
    minbikes = float(minbikes) if minbikes else 0
    maxbikes = float(maxbikes) if maxbikes else 1e5
    if msg:
        print(msg)
        abort(*msg)
    if option == 'region':
        parsed = region_request(inp, minbikes, maxbikes)
    else:
        inp = [x.strip() for x in inp.split(',')]  # strip and split input
        inp = list(set(inp))  # eliminate duplicates
        if option == 'country':
            parsed = country_request(inp, minbikes, maxbikes)
        else:
            parsed = city_request(inp, minbikes, maxbikes)
            print(parsed)
            parsed = {}
    return parsed


@app.route('/')
def hello_world():
    return 'Hey!'


if __name__ == "__main__":
    app.run(debug=True)
