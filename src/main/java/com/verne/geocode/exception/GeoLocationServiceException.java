package com.verne.geocode.exception;

import com.verne.geocode.service.GeoLocationService;

/**
 * Created by verne on 3/12/17.
 */
public class GeoLocationServiceException extends RuntimeException {
    public GeoLocationServiceException(String message){
        super(message);
    }
}
