package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUserName(String userName);
    Optional<Account> findByEmail(String email);
}
