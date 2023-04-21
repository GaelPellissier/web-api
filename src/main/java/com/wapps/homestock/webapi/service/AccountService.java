package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Account;
import com.wapps.homestock.webapi.repository.IAccount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class AccountService {
    @Autowired
    private IAccount mAccount;
    private final String GET = "GET_ACCOUNT";
    private final String SAVE = "SAVE_ACCOUNT";
    private final String UPDATE = "UPDATE_ACCOUNT";
    private final String DELETE = "DELETE_ACCOUNT";

    public Optional<Account> getAccount(final Long id) throws NotFoundException {
        if (mAccount.existsById(id))
            return mAccount.findById(id);
        throw new NotFoundException(GET, "account", id.toString());
    }

    public Iterable<Account> getAccounts() {
        return mAccount.findAll();
    }

    public Account saveAccount(Account account) throws AlreadyExistsException {

        if (mAccount.findByUserName(account.getUserName()).isPresent())
            throw new AlreadyExistsException(SAVE, "UserName", account.getUserName());
        if (mAccount.findByEmail(account.getEmail()).isPresent())
            throw new AlreadyExistsException(SAVE, "Email", account.getEmail());

        return mAccount.save(account);
    }

    public Account updateAccount(Account account) throws NotFoundException {
        if (mAccount.existsById(account.getId()))
            return mAccount.save(account);
        throw new NotFoundException(UPDATE, "account", account.getId().toString());
    }

    public void deleteAccount(final Long id) throws NotFoundException {
        if (mAccount.existsById(id))
            mAccount.deleteById(id);
        throw new NotFoundException(DELETE, "account", id.toString());
    }
}
