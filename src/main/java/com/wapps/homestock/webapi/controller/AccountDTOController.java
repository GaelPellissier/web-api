package com.wapps.homestock.webapi.controller;

import com.wapps.homestock.lib.base.BaseController;
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

@RestController
public class AccountDTOController extends BaseController {
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
        typeId = checkLong(typeId, Long.valueOf(1));

        try {
            pojo.setAccountType(mAccountTypeService.getAccountType(typeId).get());
            pojo.getAccount().setCreationDate(new Date());
            pojo.getAccount().setTypeId(pojo.getAccountType().getId());

            pojo.setAccount(mAccountService.saveAccount(pojo.getAccount()));

            return new ResponseEntity(mAccountMapper.convertToDTO(pojo), HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity getAccount(@PathVariable("id") final Long id) throws NotFoundException {
        try {
            Account account = mAccountService.getAccount(id).get();
            AccountType accountType = mAccountTypeService.getAccountType(account.getTypeId()).get();
            AccountPOJO pojo = new AccountPOJO(account, accountType);

            return new ResponseEntity(mAccountMapper.convertToDTO(pojo), HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity getAccounts() throws NotFoundException {
        try {
            Iterable<Account> accountList = mAccountService.getAccounts();
            List<AccountDTO> dtoList = new ArrayList<>();
            accountList.forEach(account -> {
                AccountPOJO pojo = new AccountPOJO();
                pojo.setAccount(account);
                Long typeId = account.getTypeId();
                pojo.setAccountType(mAccountTypeService.getAccountType(typeId).get());

                dtoList.add(mAccountMapper.convertToDTO(pojo));
            });

            return new ResponseEntity(dtoList, HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/account/{id}")
    public ResponseEntity updateAccount(@PathVariable("id") final Long id, @RequestBody AccountDTO accountDTO) throws NotFoundException {
        AccountPOJO pojo = mAccountMapper.convertToPOJO(accountDTO);
        Long typeId = accountDTO.getTypeId();
        typeId = checkLong(typeId, Long.valueOf(1));

        try {
            Account currentAccount = mAccountService.getAccount(id).get();
            AccountType type = mAccountTypeService.getAccountType(typeId).get();
            pojo.setAccountType(type);
            currentAccount.setTypeId(typeId);

            if (checkString(accountDTO.getUsername()))
                currentAccount.setUserName(accountDTO.getUsername());
            if (checkString(accountDTO.getPassword()))
                currentAccount.setPassword(accountDTO.getPassword());
            if (checkString(accountDTO.getEmail()))
                currentAccount.setEmail(accountDTO.getEmail());
            currentAccount.setLastModificationDate(new Date());

            mAccountService.updateAccount(currentAccount);
            pojo.setAccount(currentAccount);

            return new ResponseEntity(mAccountMapper.convertToDTO(pojo), HttpStatus.FOUND);
        }
        catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") final Long id) throws NotFoundException {
        try {
            mAccountService.deleteEmployee(id);
            return new ResponseEntity(null, HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
