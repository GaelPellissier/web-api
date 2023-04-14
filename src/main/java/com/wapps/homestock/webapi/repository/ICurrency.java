package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICurrency extends CrudRepository<Currency, Long> {
    Optional<Currency> findByTrigram(String trigram);
    Optional<Currency> findByName(String name);
}
