package com.wapps.homestock.webapi.controller;

import com.wapps.homestock.webapi.service.CurrencyService;
import com.wapps.homestock.webapi.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RealmDTOController {
    @Autowired
    private RealmService mRealmService;
    @Autowired
    private CurrencyService mCurrencyService;
    //private final RealmDTOMapper mRealmMapper;
}
