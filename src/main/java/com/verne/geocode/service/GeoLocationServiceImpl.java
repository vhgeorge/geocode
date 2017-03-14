package com.verne.geocode.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.verne.geocode.exception.GeoLocationServiceException;
import com.verne.geocode.model.Entity.LocationEntity;
import com.verne.geocode.model.Geocode;
import com.verne.geocode.model.Location;
import com.verne.geocode.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by verne on 3/12/17.
 */
@Service
public class GeoLocationServiceImpl implements GeoLocationService {
    // Typically would not have API key hard coded in source code
    private static final String GOOGLE_MAPS_API_KEY = "AIzaSyC8f4we_EF0Oq8JgVAUcH8QJ3LtPfXi4wI";

    @Autowired
    LocationRepository locationRepository;

    /**
     * Takes geocode and returns an address
     * @param geocode
     * @return
     */
    @Override
    public Location getAddress(Geocode geocode) {

        Location location = null;

        try {
            // Call to google maps api
            GeoApiContext context = new GeoApiContext().setApiKey(GOOGLE_MAPS_API_KEY);
            GeocodingResult[] results =  GeocodingApi.reverseGeocode(
                    context,new LatLng(geocode.getLatitude(), geocode.getLongitude())).await();

            if (results != null && results.length > 0) {
                // The first result is the best estimate
                String address = results[0].formattedAddress;
                location = new Location(address);

                LocationEntity locationEntity = new LocationEntity();
                locationEntity.setAddress(address);
                locationEntity.setGeocode("{" + geocode.getLatitude() + "," + geocode.getLongitude() + "}");
                locationEntity.setCreatedAt(new Date());
                // Persist location data
                locationRepository.saveAndFlush(locationEntity);
            }

        } catch (Exception e) {
            String error = "Error occurred while communicating with google maps api";
            // "Logging"
            System.out.println(error);
            System.out.println(e.getMessage());
            throw new GeoLocationServiceException(error);
        }
        return location;
    }

    /**
     * Retrieves last 10 geocode requests
     * @return
     */
    @Override
    public List<LocationEntity> getLocationCache() {
        return locationRepository.findFirst10ByOrderByCreatedAtDesc();
    }
}
