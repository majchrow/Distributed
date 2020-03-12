# DESCRIBED LIST OF USED REST APIS AND ENDPOINTS


## 1. BIKES:

### Main route 
`http://api.citybik.es/`

### Endopoints (only important resources described)

`/v2/networks` - List of all avaliable bike networks

- **networks(list)**
	- **location**
		- **city**  - city name
		- **country** - country alpha number (2-letter)
	- **href** - network endpoint (network_id)

`/v2/networks/{network_id}` - Search by network_id

- **network**
	- **stations(list)**
		- **free_bikes** - number of free bikes in given station


## 2. COUNTRIES

### Main route :
`https://restcountries.eu/`

### Endopoints (only important resources described)

`/rest/v2/{name}` - Search by country name. It can be the native name or partial name

- **(list)**
	- **alpha2Code** - country alpha number (2-letter)
	- **name** - country name
	- **area** - area [km²]
	- **gini** - gini coefficient [%]
	- **population** - population [exact number]

`/rest/v2/alpha/{alpha}` - Search by ISO 3166-1 2-letter or 3-letter country code

- **name** - country name
- **area** - area [km²]
- **gini** - gini coefficient [%]
- **population** - population [exact number]

`/rest/v2/region/{region}` - Search by region: Africa, Americas, Asia, Europe, Oceania

- **(list)**
	- **alpha2Code** - country alpha number (2-letter)
	- **name** - country name
	- **area** - area [km²]
	- **gini** - gini coefficient [%]
	- **population** - population [exact number]
