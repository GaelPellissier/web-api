package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
}
