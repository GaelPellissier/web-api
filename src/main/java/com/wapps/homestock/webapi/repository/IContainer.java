package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.Container;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContainer extends CrudRepository<Container, Long> {
    Iterable<Container> findByLocationId(Long locationId);
}
