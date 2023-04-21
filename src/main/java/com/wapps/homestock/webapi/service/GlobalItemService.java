package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.GlobalItem;
import com.wapps.homestock.webapi.repository.IGlobalItem;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class GlobalItemService {
    @Autowired
    private IGlobalItem mGlobalItem;
    private final String GET = "GET_GLOBALITEM";
    private final String SAVE = "SAVE_GLOBALITEM";
    private final String UPDATE = "UPDATE_GLOBALITEM";
    private final String DELETE = "DELETE_GLOBALITEM";

    public Optional<GlobalItem> getGlobalItem(final Long id) throws NotFoundException {
        if (mGlobalItem.existsById(id))
            return mGlobalItem.findById(id);
        throw new NotFoundException(GET, "global item", id.toString());
    }

    public Iterable<GlobalItem> getGlobalItems() {
        return mGlobalItem.findAll();
    }

    public GlobalItem saveGlobalItem(GlobalItem globalItem) throws AlreadyExistsException {
        return mGlobalItem.save(globalItem);
    }

    public GlobalItem updateGlobalItem(GlobalItem globalItem) throws NotFoundException {
        if (mGlobalItem.existsById(globalItem.getId()))
            return mGlobalItem.save(globalItem);
        throw new NotFoundException(UPDATE, "global item", globalItem.getId().toString());
    }

    public void deleteGlobalItem(final Long id) throws NotFoundException {
        if (mGlobalItem.existsById(id))
            mGlobalItem.deleteById(id);
        throw new NotFoundException(DELETE, "global item", id.toString());
    }
}
