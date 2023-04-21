package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.GlobalItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGlobalItem extends CrudRepository<GlobalItem, Long> {
}
