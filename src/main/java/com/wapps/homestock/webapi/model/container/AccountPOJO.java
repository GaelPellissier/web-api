package com.wapps.homestock.webapi.model.container;

import com.wapps.homestock.webapi.model.Account;
import com.wapps.homestock.webapi.model.AccountType;

public class AccountPOJO extends BasePOJO {
    private Account mAccount;
    private AccountType mAccountType;

    public AccountPOJO() {}

    public AccountPOJO(Account account, AccountType accountType) {
        mAccount = account;
        mAccountType = accountType;
    }

    public Account getAccount() {
        return mAccount;
    }

    public void setAccount(Account account) {
        mAccount = account;
    }

    public AccountType getAccountType() {
        return mAccountType;
    }

    public void setAccountType(AccountType accountType) {
        mAccountType = accountType;
    }
}
