package com.wapps.homestock.webapi.controller;

import com.wapps.homestock.lib.base.BaseController;
import com.wapps.homestock.lib.dto.RealmDTO;
import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Currency;
import com.wapps.homestock.webapi.model.Realm;
import com.wapps.homestock.webapi.model.container.RealmPOJO;
import com.wapps.homestock.webapi.model.mapper.GlobalItemDTOMapper;
import com.wapps.homestock.webapi.model.mapper.RealmDTOMapper;
import com.wapps.homestock.webapi.service.CurrencyService;
import com.wapps.homestock.webapi.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RealmDTOController extends BaseController {
    @Autowired
    private RealmService mRealmService;
    @Autowired
    private CurrencyService mCurrencyService;
    private final RealmDTOMapper mRealmMapper;
    private final GlobalItemDTOMapper mGlobalItemDTOMapper;
    public RealmDTOController() {
        mGlobalItemDTOMapper = new GlobalItemDTOMapper();
        mRealmMapper = new RealmDTOMapper(mGlobalItemDTOMapper); }

    @PostMapping("/realm")
    public ResponseEntity createRealm(@RequestBody RealmDTO realmDTO) throws AlreadyExistsException, NotFoundException {
        RealmPOJO pojo = mRealmMapper.convertToPOJO(realmDTO);

        Long currencyId = pojo.getRealm().getCurrencyId();
        currencyId = checkLong(currencyId, Long.valueOf(1));

        try {
            pojo.setCurrency(mCurrencyService.getCurrency(currencyId).get());
            pojo.getRealm().setCreationDate(new Date());
            pojo.getRealm().setCurrencyId(pojo.getCurrency().getId());

            pojo.setRealm(mRealmService.saveRealm(pojo.getRealm()));

            return new ResponseEntity(mRealmMapper.convertToDTO(pojo), HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/realm/{id}")
    public ResponseEntity getRealm(@PathVariable("id") final Long id) throws NotFoundException {
        try {
            Realm realm = mRealmService.getRealm(id).get();
            Currency currency = mCurrencyService.getCurrency(realm.getCurrencyId()).get();
            RealmPOJO pojo = new RealmPOJO(realm, currency);

            return new ResponseEntity(mRealmMapper.convertToDTO(pojo), HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/realms")
    public ResponseEntity getRealms() throws NotFoundException {
        try {
            Iterable<Realm> realmList = mRealmService.getRealms();
            List<RealmDTO> dtoList = new ArrayList<>();
            realmList.forEach(realm -> {
                RealmPOJO pojo = new RealmPOJO();
                pojo.setRealm(realm);
                Long currencyId = realm.getCurrencyId();
                pojo.setCurrency(mCurrencyService.getCurrency(currencyId).get());

                dtoList.add(mRealmMapper.convertToDTO(pojo));
            });

            return new ResponseEntity(dtoList, HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/realms/{id}")
    public ResponseEntity getRealmsByOwnerId(@PathVariable("id") final Long id) throws NotFoundException {
        try {
            Iterable<Realm> realmList = mRealmService.getRealmsByOwnerId(id);
            List<RealmDTO> dtoList = new ArrayList<>();
            realmList.forEach(realm -> {
                RealmPOJO pojo = new RealmPOJO();
                pojo.setRealm(realm);
                Long currencyId = realm.getCurrencyId();
                pojo.setCurrency(mCurrencyService.getCurrency(currencyId).get());

                dtoList.add(mRealmMapper.convertToDTO(pojo));
            });

            return new ResponseEntity(dtoList, HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/realm/{id}")
    public ResponseEntity updateRealm(@PathVariable("id") final Long id, @RequestBody RealmDTO realmDTO) throws NotFoundException {
        RealmPOJO pojo = mRealmMapper.convertToPOJO(realmDTO);

        Long currencyId = realmDTO.getCurrencyId();
        currencyId = checkLong(currencyId, Long.valueOf(1));

        try {
            Realm currentRealm = mRealmService.getRealm(id).get();
            Currency currentCurrency = mCurrencyService.getCurrency(currencyId).get();
            pojo.setCurrency(currentCurrency);
            currentRealm.setCurrencyId(currencyId);

            if (checkLong(realmDTO.getOwnerId()))
                currentRealm.setOwnerId(realmDTO.getOwnerId());
            if (checkInt(realmDTO.getMaxLocationNumber()))
                currentRealm.setMaxLocationNumber(realmDTO.getMaxLocationNumber());
            if (checkString(realmDTO.getName()))
                currentRealm.setName(realmDTO.getName());
            if (checkString(realmDTO.getDescription()))
                currentRealm.setDescription(realmDTO.getDescription());
            currentRealm.setLastModificationDate(new Date());

            mRealmService.updateRealm(currentRealm);
            pojo.setRealm(currentRealm);

            return new ResponseEntity(mRealmMapper.convertToDTO(pojo), HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/realm/{id}")
    public ResponseEntity deleteRealm(@PathVariable("id") final Long id) throws NotFoundException {
        try {
            mRealmService.deleteRealm(id);
            return new ResponseEntity(null, HttpStatus.FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
