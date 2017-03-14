package com.verne.geocode.repository;

import com.verne.geocode.model.Entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by verne on 3/12/17.
 */
@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    public List<LocationEntity> findFirst10ByOrderByCreatedAtDesc();
}
