package com.wapps.homestock.webapi.model.mapper;

import com.wapps.homestock.lib.base.BaseDTO;
import com.wapps.homestock.webapi.model.container.BasePOJO;

public interface BaseDTOMapper<P extends BasePOJO, D extends BaseDTO> {
    D convertToDTO(P pojo);
    P convertToPOJO(D dto);
}
