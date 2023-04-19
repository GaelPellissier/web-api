package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.Realm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRealm extends CrudRepository<Realm, Long> {
    Iterable<Realm> findByOwnerId(Long ownerId);
    Optional<Realm> findByName(String name);
}
