package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.AccountType;
import com.wapps.homestock.webapi.repository.AccountTypeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class AccountTypeService {
    @Autowired
    private AccountTypeRepository mAccountTypeRepository;

    public Optional<AccountType> getAccountType(final Long id) throws NotFoundException {
        if (mAccountTypeRepository.findById(id).isEmpty())
            throw new NotFoundException("No account type found for ID : " + id);
        return mAccountTypeRepository.findById(id);
    }

    public Iterable<AccountType> getAccountTypes() {
        return mAccountTypeRepository.findAll();
    }
/*
    public AccountType saveAccountType(AccountType accountType) throws AlreadyExistsException {
        AccountType savedAccountType = mAccountTypeRepository.save(accountType);
        return savedAccountType;
    }

    public void deleteAccountType(final Long id) throws NotFoundException {
        mAccountTypeRepository.deleteById(id);
    }*/
}
