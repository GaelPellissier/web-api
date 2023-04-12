package com.wapps.homestock.webapi.controller;

import com.wapps.homestock.lib.dto.AccountDTO;
import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Account;
import com.wapps.homestock.webapi.model.AccountType;
import com.wapps.homestock.webapi.model.container.AccountPOJO;
import com.wapps.homestock.webapi.model.mapper.AccountDTOMapper;
import com.wapps.homestock.webapi.service.AccountService;
import com.wapps.homestock.webapi.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountDTOController {
    @Autowired
    private AccountService mAccountService;
    @Autowired
    private AccountTypeService mAccountTypeService;

    private final AccountDTOMapper mAccountMapper;

    public AccountDTOController() {
        mAccountMapper = new AccountDTOMapper();
    }

    @PostMapping("/account")
    public ResponseEntity createAccount(@RequestBody AccountDTO accountDTO) throws AlreadyExistsException, NotFoundException {
        AccountPOJO pojo = mAccountMapper.convertToPOJO(accountDTO);

        Long typeId = pojo.getAccount().getTypeId();
        if (typeId == null)
            typeId = Long.valueOf(1);
        pojo.setAccountType(mAccountTypeService.getAccountType(typeId).get());

        pojo.getAccount().setCreationDate(new Date());
        pojo.getAccount().setTypeId(pojo.getAccountType().getId());

        try {
            pojo.setAccount(mAccountService.saveAccount(pojo.getAccount()));
            return new ResponseEntity(mAccountMapper.convertToDTO(pojo), HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity getAccount(@PathVariable("id") final Long id) throws NotFoundException {
        try {
            Account account = mAccountService.getAccount(id).orElse(null);
            AccountType accountType = mAccountTypeService.getAccountType(account.getTypeId()).get();
            AccountPOJO pojo = new AccountPOJO(account, accountType);

            return new ResponseEntity(mAccountMapper.convertToDTO(pojo), HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accounts")
    public Iterable<AccountDTO> getAccounts() throws NotFoundException {
        Iterable<Account> accountList = mAccountService.getAccounts();
        Iterable<AccountType>typelist = mAccountTypeService.getAccountTypes();
        List<AccountDTO> dtoList = new ArrayList<>();
        accountList.forEach(account -> {
            AccountPOJO item = new AccountPOJO();
            item.setAccount(account);
            typelist.forEach(type -> {
                if (type.getId() == account.getTypeId()) {
                    item.setAccountType(type);
                }
            });

            dtoList.add(mAccountMapper.convertToDTO(item));
        });

        return dtoList;
    }

    @PutMapping("/account/{id}")
    public AccountDTO updateAccount(@PathVariable("id") final Long id, @RequestBody AccountDTO accountDTO) throws NotFoundException {
        Optional<Account> a = mAccountService.getAccount(id);
        if (a.isPresent()) {
            AccountPOJO pojo = mAccountMapper.convertToPOJO(accountDTO);
            Account currentAccount = a.get();
            Long typeId = accountDTO.getTypeId();
            Optional<AccountType> type = mAccountTypeService.getAccountType(typeId);
            if (typeId == null || !type.isPresent()) {
                typeId = Long.valueOf(1);
                type = mAccountTypeService.getAccountType(typeId);
            }
            pojo.setAccountType(type.get());
            currentAccount.setTypeId(typeId);

            String username = accountDTO.getUsername();
            if (username != null)
                currentAccount.setUserName(username);
            String password = accountDTO.getPassword();
            if (password != null)
                currentAccount.setPassword(password);
            String email = accountDTO.getEmail();
            if (email != null)
                currentAccount.setEmail(email);
            currentAccount.setLastModificationDate(new Date());

            mAccountService.updateAccount(currentAccount);

            pojo.setAccount(currentAccount);
            return mAccountMapper.convertToDTO(pojo);
        }
        else
            return null;
    }

    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable("id") final Long id) throws NotFoundException {
        if (mAccountService.getAccount(id).isEmpty())
            throw new NotFoundException("");
        mAccountService.deleteEmployee(id);
    }
}
