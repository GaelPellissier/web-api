package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountType extends CrudRepository<AccountType, Long> {
    Optional<AccountType> findByTypeName(String typeName);
}
