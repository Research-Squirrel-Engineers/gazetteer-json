/*
 GET url with accept: application/vnd.geo+json 

add pleiades search in bounding box lookup and cendroid search

is periopleo api the right way to do it?

https://github.com/pelagios/peripleo/blob/master/README.md#bbox

http://pelagios.org/peripleo/search?bbox=23.716,23.7266,37.97,37.978&types=place

https://github.com/pelagios/peripleo/blob/master/README.md#lat-lon-radius

http://pelagios.org/peripleo/search?lat=37.97&lon=23.72&radius=3&types=place

----------------

<Is this the right way to use it? Because the Pelagios API is tagged with 
“deprecated”.>

Yes, that should work. Be sure to check not just the "identifier" field, 
though, but also the "matches" field, if it exists. I think in practice, the 
records will pretty much always have the Pleiades URI in the "identifier", 
and only the alternative gazetteers (DARE, Vici, Trismegistos, etc.) in the 
"matches" - but it's not guaranteed!

With the deprecation warning, I assume you are referring to the note on the 
GitHub project? Yes, that's because I'm currently working on the new version 
at

http://github.com/pelagios/peripleo2

It will still take a while until Peripleo 2 replaces the current one though. 
Also, I'm planning a backwards compatible API crosswalk, so that existing 
clients will (hopefully) notice no difference. (But you really won't want to 
use that, since the new API will be so much cooler 

<I also looking for the lat, lon, radius function >

Yes, that should work fine, too.

Let me know if you build something interesting! We're constantly on the 
lookout for people making use of our stuff in their own work.

Cheers,
Rainer

----------------

Hi @ all,

one simple question: I try to query Pleiades Places within a Bounding Box or 
via Lat, Lon and Radius.

I thought in this case the Peripleo API could solve the problem.

If I use e.g. 
http://pelagios.org/peripleo/search?bbox=23.716,23.7266,37.97,37.978&types=place 
and filter the results where “identifier” contains “plaiades” I could get 
some Pleiades places.

Is this the right way to use it? Because the Pelagios API is tagged with 
“deprecated”.

I also looking for the lat, lon, radius function 
(https://github.com/pelagios/peripleo/blob/master/README.md#lat-lon-radius).

Best Regards from Mainz,


 */
package org.linkedgeodesy.gazetteerjson.gazetteer;

public class Pleiades {
    
}
