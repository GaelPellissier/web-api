package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItem extends CrudRepository<Item, Long> {
}
