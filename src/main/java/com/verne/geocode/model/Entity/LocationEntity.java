package com.verne.geocode.model.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by verne on 3/12/17.
 */
@Entity
@Table(name="Location")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String geocode;

    @Column
    private String address;

    @Column
    private Date createdAt;

    public long getId() {
        return id;
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
