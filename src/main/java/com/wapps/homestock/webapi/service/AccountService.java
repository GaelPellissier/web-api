package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Account;
import com.wapps.homestock.webapi.repository.AccountRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class AccountService {
    @Autowired
    private AccountRepository mAccountRepository;

    public Optional<Account> getAccount(final Long id) throws NotFoundException {
        if (mAccountRepository.existsById(id))
            return mAccountRepository.findById(id);
        throw new NotFoundException("No account found for ID : " + id);
    }

    public Iterable<Account> getAccounts() {
        return mAccountRepository.findAll();
    }

    public Account saveAccount(Account account) throws AlreadyExistsException {

        if (mAccountRepository.findByUserName(account.getUserName()).isPresent())
            throw new AlreadyExistsException("UserName already used : " + account.getUserName());
        if (mAccountRepository.findByEmail(account.getEmail()).isPresent())
            throw new AlreadyExistsException("Email already used : " + account.getEmail());

        return mAccountRepository.save(account);
    }

    public Account updateAccount(Account account) throws NotFoundException {
        if (mAccountRepository.existsById(account.getId()))
            return mAccountRepository.save(account);
        throw new NotFoundException("No account found for ID : " + account.getId());
    }

    public void deleteEmployee(final Long id) throws NotFoundException {
        mAccountRepository.deleteById(id);
    }
}
