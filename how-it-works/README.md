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
