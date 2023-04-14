package com.wapps.homestock.webapi.model.container;

import com.wapps.homestock.webapi.model.Currency;
import com.wapps.homestock.webapi.model.Realm;

public class RealmPOJO extends BasePOJO {
    private Realm mRealm;
    private Currency mCurrency;

    public RealmPOJO() {}

    public RealmPOJO(Realm realm, Currency currency) {
        mRealm = realm;
        mCurrency = currency;
    }

    public Realm getRealm() {
        return mRealm;
    }

    public void setRealm(Realm realm) {
        mRealm = realm;
    }

    public Currency getCurrency() {
        return mCurrency;
    }

    public void setCurrency(Currency currency) {
        mCurrency = currency;
    }
}
