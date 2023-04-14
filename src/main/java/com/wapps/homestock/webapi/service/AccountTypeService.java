package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.AccountType;
import com.wapps.homestock.webapi.repository.IAccountType;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class AccountTypeService {
    @Autowired
    private IAccountType mAccountType;
    private final String GET = "GET_ACCTYPE";
    private final String SAVE = "SAVE_ACCTYPE";
    private final String UPDATE = "UPDATE_ACCTYPE";
    private final String DELETE = "DELETE_ACCTYPE";

    public Optional<AccountType> getAccountType(final Long id) throws NotFoundException {
        if (mAccountType.findById(id).isEmpty())
            throw new NotFoundException(GET, "account type", id.toString());
        return mAccountType.findById(id);
    }

    public Iterable<AccountType> getAccountTypes() {
        return mAccountType.findAll();
    }

    public AccountType saveAccountType(AccountType accountType) throws AlreadyExistsException {
        if (mAccountType.findByTypeName(accountType.getTypeName()).isPresent())
            throw new AlreadyExistsException(SAVE, "Type name", accountType.getTypeName());
        return mAccountType.save(accountType);
    }

    public AccountType updateAccountType(AccountType accountType) throws NotFoundException {
        if (mAccountType.existsById(accountType.getId()))
            return mAccountType.save(accountType);
        throw new NotFoundException(UPDATE, "account type", accountType.getId().toString());
    }

    public void deleteAccountType(final Long id) throws NotFoundException {
        if (mAccountType.existsById(id))
            mAccountType.deleteById(id);
        throw new NotFoundException(DELETE, "account type", id.toString());
    }
}
