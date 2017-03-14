package com.verne.geocode.controller;

import com.verne.geocode.model.Entity.LocationEntity;
import com.verne.geocode.model.Location;
import com.verne.geocode.model.Geocode;
import com.verne.geocode.service.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/geolocation")
public class GeoLocationController {

    @Autowired()
    private GeoLocationService geoLocationService;

    /**
     * Takes a latitude,longitude geocode and returns an address
     * @param latlng
     * @return Location response
     */
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ResponseEntity<Location> getAddress(@RequestParam("latlng") String latlng){
        ResponseEntity<Location> responseEntity = null;

        //Splits geocode into individual latitude and longitude
        Geocode geocode = splitLatLng(latlng);

        // Get address from geocode
        Location location = geoLocationService.getAddress(geocode);

        if (location.getAddress() != null){
            responseEntity= new ResponseEntity<Location>(location, HttpStatus.OK);
        }else{
            responseEntity= new ResponseEntity<Location>(location, HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    /**
     * Returns a list of the last 10 geocode requests
     * @return
     */
    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public ResponseEntity<List<LocationEntity>> getCache(){
        ResponseEntity<List<LocationEntity>> responseEntity = null;

        // Get last 10 geocode lookups
        List<LocationEntity> location = geoLocationService.getLocationCache();

        if (location != null && location.size() > 0){
            responseEntity= new ResponseEntity<List<LocationEntity>>(location, HttpStatus.OK);
        }else{
            responseEntity= new ResponseEntity<List<LocationEntity>>(location, HttpStatus.NO_CONTENT);
        }


        return responseEntity;
    }

    /**
     * Converts geocode String into Geocode object
     * @param latlng
     * @return Geocode object
     */
    private Geocode splitLatLng(String latlng) {
        // Throws IllegalArgumentExceptions if latlng isnt formatted correctly
        if(latlng == null || latlng.equals("")){
            throw new IllegalArgumentException("Request Parameter, latlng is formatted incorrectly: " + latlng);
        }
        String[] result = latlng.split(",");

        // if result size != 2 throw exception
        if (result == null || result.length != 2){
            throw new IllegalArgumentException("Request Parameter, latlng is formatted incorrectly: " + latlng);
        }

        Geocode geocode = new Geocode();
        geocode.setLatitude(new Double(result[0]));
        geocode.setLongitude(new Double(result[1]));

        return geocode;
    }
}
