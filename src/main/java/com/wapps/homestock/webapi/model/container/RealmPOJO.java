package com.wapps.homestock.webapi.model.container;

import com.wapps.homestock.webapi.model.Currency;
import com.wapps.homestock.webapi.model.GlobalItem;
import com.wapps.homestock.webapi.model.Realm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RealmPOJO extends BasePOJO {
    private Realm mRealm;
    private Currency mCurrency;
    private HashMap<Long, String> mLocationList;
    private List<GlobalItem> mGlobalItemList;

    public RealmPOJO() {}

    public RealmPOJO(Realm realm, Currency currency) {
        mRealm = realm;
        mCurrency = currency;
        mLocationList = new HashMap<>();
        mGlobalItemList = new ArrayList<>();
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

    public HashMap<Long, String> getLocationList() {
        return mLocationList;
    }

    public void setLocationList(HashMap<Long, String> locationList) {
        mLocationList = locationList;
    }

    public List<GlobalItem> getGlobalItemList() {
        return mGlobalItemList;
    }

    public void setGlobalItemList(List<GlobalItem> globalItemList) {
        mGlobalItemList = globalItemList;
    }
}
