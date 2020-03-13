import http.client as http  # low lvl library for http (GET) requests
import json
import random
from urllib.parse import quote  # url string encode for countries (spaces, non ascii characters, etc)

from flask import Flask, request, abort, render_template

app = Flask(__name__, static_url_path='')


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
        app.logger.info('Request %s successful', endpoint)
        data = json.loads(resp.read().decode('utf-8'))
        data = random.sample(data, 2)  # GET ONLY 2 countries at random, due to long queries
        countries = [country['name'] for country in data]
    else:
        app.logger.warning('Could not make request to endpoint %s', endpoint)
        return {}
    if conn:
        app.logger.info('Disconnected from %s', Country.URL)
        conn.close()
    return country_request(countries, minbikes, maxbikes, full=True)


def country_request(inp, minbikes, maxbikes, full=False):
    result = {}
    codes = []
    conn = http.HTTPConnection(Country.URL)
    if conn:
        app.logger.info('Connected to %s', Country.URL)
    else:
        app.logger.info('Failed to connect %s', Country.URL)
        return result
    for country in inp:
        endpoint = Country.NAME + quote(country)  # Watch out for the country UTF-8 encoding in low level requests!
        if full:
            endpoint += '?fullText=true'
        conn.request("GET", endpoint)
        resp = conn.getresponse()
        if resp.status == 200:
            app.logger.info('Request %s successful', endpoint)
            data = json.loads(resp.read().decode('utf-8'))
            if len(data) != 1:
                app.logger.warning('Ambiguity with country %s skipping', country)
                continue
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
    codes = {}
    conn = http.HTTPConnection(Bikes.URL)
    if conn:
        app.logger.info('Connected to %s', Bikes.URL)
        conn.request("GET", Bikes.NETWORKS)
        resp = conn.getresponse()
        if resp.status == 200:
            app.logger.info('Request %s successful', Bikes.NETWORKS)
            networks = json.loads(resp.read().decode('utf-8'))
            for network in networks['networks']:
                if network['location']['city'] in inp:
                    code = network['location']['country']
                    city = network['location']['city']
                    bikes = count_bikes(conn, network['href'], minbikes, maxbikes)
                    cities_info = codes.get(code, [])
                    cities_info.append((city, bikes))
                    codes[code] = cities_info
        else:
            app.logger.warning('Failed to request', Bikes.NETWORKS)
    else:
        app.logger.info('Failed to connect %s', Bikes.URL)
    if conn:
        app.logger.info('Disconnecting from %s', Bikes.URL)
        conn.close()
    countries = {}
    for code, cities_info in codes.items():
        country_name, details = get_basic_country_info(code)
        details['cities'] = cities_info
        countries[country_name] = details
    return countries


def get_basic_country_info(code):
    country, country_info = 'UNK', {}
    conn = http.HTTPConnection(Country.URL)
    if conn:
        app.logger.info('Connected to %s', Country.URL)
    else:
        app.logger.info('Failed to connect %s', Country.URL)
        return country, country_info
    endpoint = Country.ALPHA + code
    conn.request("GET", endpoint)
    resp = conn.getresponse()
    if resp.status == 200:
        app.logger.info('Request %s successful', endpoint)
        data = json.loads(resp.read().decode('utf-8'))
        country = data['name']
        country_info['area'] = data['area']
        country_info['gini'] = data['gini']
        country_info['population'] = data['population']
    else:
        app.logger.warning('Could not make request to endpoint %s skipping', endpoint)
    if conn:
        app.logger.info('Disconnected from %s', Country.URL)
        conn.close()
    return country, country_info


def get_cities_info(codes, minbikes, maxbikes):
    alpha_codes = {code for _, code in codes}
    cities_dict = {code: [] for code in alpha_codes}
    conn = http.HTTPConnection(Bikes.URL)
    if conn:
        app.logger.info('Connected to %s', Bikes.URL)
        conn.request("GET", Bikes.NETWORKS)
        resp = conn.getresponse()
        if resp.status == 200:
            app.logger.info('Request %s successful', Bikes.NETWORKS)
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
            app.logger.info('Request %s successful', endpoint)
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
        option, inp, minbikes, maxbikes = [data.get(attr, None) for attr in attrs]
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
        else:  # city
            parsed = city_request(inp, minbikes, maxbikes)
    return render_template("countires.html", parsed=parsed)


@app.route('/')
def root():
    return app.send_static_file('index.html')


if __name__ == "__main__":
    app.run(debug=True)
