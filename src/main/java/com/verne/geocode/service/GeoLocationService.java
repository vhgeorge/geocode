package com.verne.geocode.service;

import com.verne.geocode.model.Entity.LocationEntity;
import com.verne.geocode.model.Geocode;
import com.verne.geocode.model.Location;

import java.util.List;

/**
 * Created by verne on 3/10/17.
 */
public interface GeoLocationService {
    Location getAddress(Geocode geocode);
    List<LocationEntity> getLocationCache();
}
