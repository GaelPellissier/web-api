package com.wapps.homestock.webapi.model.mapper;

import com.wapps.homestock.lib.dto.GlobalItemDTO;
import com.wapps.homestock.lib.dto.RealmDTO;
import com.wapps.homestock.webapi.model.Currency;
import com.wapps.homestock.webapi.model.GlobalItem;
import com.wapps.homestock.webapi.model.Realm;
import com.wapps.homestock.webapi.model.container.RealmPOJO;

import java.util.ArrayList;
import java.util.List;

public class RealmDTOMapper implements BaseDTOMapper<RealmPOJO, RealmDTO> {
    private final GlobalItemDTOMapper mGlobalItemDTOMapper;

    public RealmDTOMapper(GlobalItemDTOMapper globalItemDTOMapper) {
        mGlobalItemDTOMapper = globalItemDTOMapper;
    }

    @Override
    public RealmDTO convertToDTO(RealmPOJO pojo) {
        RealmDTO dto = new RealmDTO();
        dto.setId(pojo.getRealm().getId());
        dto.setOwnerId(pojo.getRealm().getOwnerId());
        dto.setMaxLocationNumber(pojo.getRealm().getMaxLocationNumber());
        dto.setName(pojo.getRealm().getName());
        dto.setDescription(pojo.getRealm().getDescription());
        dto.setCreationDate(pojo.getRealm().getCreationDate());
        dto.setCurrencyId(pojo.getCurrency().getId());
        dto.setCurrencyTrigram(pojo.getCurrency().getTrigram());
        dto.setCurrencyName(pojo.getCurrency().getName());
        dto.setLocationList(pojo.getLocationList());
        List<GlobalItemDTO> globalItemDTOList = new ArrayList<>();
        pojo.getGlobalItemList().forEach(globalItem -> {
            globalItemDTOList.add(mGlobalItemDTOMapper.convertToDTO(globalItem));
        });

        return dto;
    }

    @Override
    public RealmPOJO convertToPOJO(RealmDTO dto) {
        RealmPOJO pojo = new RealmPOJO();

        Realm realm = new Realm();
        realm.setId(dto.getId());
        realm.setOwnerId(dto.getOwnerId());
        realm.setCurrencyId(dto.getCurrencyId());
        realm.setMaxLocationNumber(dto.getMaxLocationNumber());
        realm.setName(dto.getName());
        realm.setDescription(dto.getDescription());
        realm.setCreationDate(dto.getCreationDate());

        Currency currency = new Currency();
        currency.setId(dto.getCurrencyId());
        currency.setTrigram(dto.getCurrencyTrigram());
        currency.setName(dto.getCurrencyName());

        List<GlobalItem> globalItemList = new ArrayList<>();
        dto.getGlobalItemList().forEach(globalItemDTO -> {
            globalItemList.add(mGlobalItemDTOMapper.convertToPOJO(globalItemDTO));
        });

        pojo.setRealm(realm);
        pojo.setCurrency(currency);
        pojo.setLocationList(dto.getLocationList());
        pojo.setGlobalItemList(globalItemList);

        return pojo;
    }
}
