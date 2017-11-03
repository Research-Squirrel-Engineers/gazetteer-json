# *Gazetteer JSON - How it works!*

## iDAI.gazetteer

Query the iDAI.gazetteer using the API.

### getPlaceById()

**request:**
* **url:** `https://gazetteer.dainst.org/place/:id`
* **method:** `GET`
* **header:** `Accept: application/vnd.geo+json`
* **response:** `JSON`

**used attributes:**
* **prefName:** `json.properties.prefName.{title,language}`
* **altNames:** `json.properties.names[i].{title,language}`
* **geometry:** `json.geometry` [GeoJSON Geometry Object]

### getPlacesByBBox()

**request:**
* **url:** `https://gazetteer.dainst.org/search.json?polygonFilterCoordinates=upperleftLon&polygonFilterCoordinates=upperleftLat&polygonFilterCoordinates=upperrightLon&polygonFilterCoordinates=upperrightLat&polygonFilterCoordinates=lowerrightLon&polygonFilterCoordinates=lowerrightLat&polygonFilterCoordinates=lowerleftLon&polygonFilterCoordinates=lowerleftLat&q=*&fq=types:populated-place`
* **method:** `GET`
* **response:** `JSON`

**used attributes:**
* **prefName:** `json.result[i].prefName.{title,language}`
* **altNames:** `json.result[i].names[i].{title,language}`
* **geometry:** `json.result[i].prefLocation.coordinates[lng, lat]`

### getPlacesByString()

**request:**
* **url:** `https://gazetteer.dainst.org/search.json?q={SearchString}&fq=types:populated-place`
* **method:** `GET`
* **response:** `JSON`

**used attributes:**
* **prefName:** `json.result[i].prefName.{title,language}`
* **altNames:** `json.result[i].names[i].{title,language}`
* **geometry:** `json.result[i].prefLocation.coordinates[lng, lat]`

## GeoNames

Query GeoNames using the API.

### getPlaceById()

**request:**
* **url:** `http://api.geonames.org/get?geonameId=:id&username=chron.ontology`
* **method:** `GET`
* **header:** `Accept: application/xml;charset=UTF-8`
* **response:** `XML`

**used attributes:**
* **prefName:** `geoname.name`
* **altNames:** `geoname.alternateName{text:value,lang:attribute}`
* **geometry:** `geoname.{lat,lng}`

### getPlacesByBBox()

**request:**
* **url:** `http://api.geonames.org/searchJSON?username=chron.ontology&featureClass=A&featureClass=P&style=full&south=upperrightLat&north=lowerleftLat&west=upperrightLon&east=lowerleftLon`
* **method:** `GET`
* **header:** `Accept: application/json;charset=UTF-8`
* **response:** `JSON`

**used attributes:**
* **prefName:** `json.geonames.name`
* **altNames:** `json.geonames.alternateNames[i].{name,lang}`
* **geometry:** `json.geonames.{lat,lng}`

### getPlacesByString()

**request:**
* **url:** `http://api.geonames.org/searchJSON?username=chron.ontology&featureClass=A&featureClass=P&style=full&name={searchString}`
* **method:** `GET`
* **response:** `JSON`

**used attributes:**
* **prefName:** `json.geonames.name`
* **altNames:** `json.geonames.alternateNames[i].{name,lang}`
* **geometry:** `json.geonames.{lat,lng}`
