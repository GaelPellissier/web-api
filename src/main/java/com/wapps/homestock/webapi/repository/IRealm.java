package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.Realm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRealm extends CrudRepository<Realm, Long> {
    Iterable<Realm> findByOwnerId(Long ownerId);
}
