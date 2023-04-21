package com.wapps.homestock.webapi.model.mapper;

import com.wapps.homestock.lib.dto.GlobalItemDTO;
import com.wapps.homestock.webapi.model.GlobalItem;

public class GlobalItemDTOMapper implements BaseDTOMapper<GlobalItem, GlobalItemDTO> {
    @Override
    public GlobalItemDTO convertToDTO(GlobalItem pojo) {
        GlobalItemDTO dto = new GlobalItemDTO();
        dto.setId(pojo.getId());
        dto.setName(pojo.getName());
        dto.setDescription(pojo.getDescription());
        dto.setStockLow(pojo.isStockLow());
        dto.setPrice(pojo.getPrice());

        return dto;
    }

    @Override
    public GlobalItem convertToPOJO(GlobalItemDTO dto) {
        GlobalItem pojo = new GlobalItem();
        pojo.setId(dto.getId());
        pojo.setName(dto.getName());
        pojo.setDescription(dto.getDescription());
        pojo.setStockLow(dto.isStockLow());
        pojo.setPrice(dto.getPrice());

        return pojo;
    }
}
