package com.wapps.homestock.webapi.model.mapper;

import com.wapps.homestock.lib.dto.AccountDTO;
import com.wapps.homestock.webapi.model.container.AccountPOJO;
import com.wapps.homestock.webapi.model.Account;
import com.wapps.homestock.webapi.model.AccountType;

public class AccountDTOMapper {

    public AccountDTO convertToDTO(AccountPOJO pojo) {
        AccountDTO dto = new AccountDTO();
        dto.setId(pojo.getAccount().getId());
        dto.setUsername(pojo.getAccount().getUserName());
        dto.setPassword(pojo.getAccount().getPassword());
        dto.setEmail(pojo.getAccount().getEmail());
        dto.setCreationDate(pojo.getAccount().getCreationDate());
        dto.setLastModificationDate(pojo.getAccount().getLastModificationDate());
        dto.setTypeId(pojo.getAccountType().getId());
        dto.setTypeName(pojo.getAccountType().getTypeName());
        dto.setMaxOwnedRealm(pojo.getAccountType().getMaxOwnedRealmNbr());
        dto.setMaxLocationNbr(pojo.getAccountType().getMaxLocationNbr());
        dto.setMaxContainerNbr(pojo.getAccountType().getMaxContainerPerLocationNbr());

        return dto;
    }

    public AccountPOJO convertToPOJO(AccountDTO dto) {
        AccountPOJO pojo = new AccountPOJO();
        Account account = new Account();
        account.setId(dto.getId());
        account.setTypeId(dto.getTypeId());
        account.setUserName(dto.getUsername());
        account.setPassword(dto.getPassword());
        account.setCreationDate(dto.getCreationDate());
        account.setLastModificationDate(dto.getLastModificationDate());
        account.setEmail(dto.getEmail());

        AccountType type = new AccountType();
        type.setId(dto.getTypeId());
        type.setTypeName(dto.getTypeName());
        type.setMaxOwnedRealmNbr(dto.getMaxOwnedRealm());
        type.setMaxLocationNbr(dto.getMaxLocationNbr());
        type.setMaxContainerPerLocationNbr(dto.getMaxContainerNbr());

        pojo.setAccount(account);
        pojo.setAccountType(type);

        return pojo;
    }
}
