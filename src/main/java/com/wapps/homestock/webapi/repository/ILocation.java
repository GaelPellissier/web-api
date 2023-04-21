package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocation extends CrudRepository<Location, Long> {
    Iterable<Location> findByRealmId(Long realmId);
}
