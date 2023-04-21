package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Item;
import com.wapps.homestock.webapi.repository.IItem;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ItemService {
    @Autowired
    private IItem mItem;
    private final String GET = "GET_ITEM";
    private final String SAVE = "SAVE_ITEM";
    private final String UPDATE = "UPDATE_ITEM";
    private final String DELETE = "DELETE_ITEM";

    public Optional<Item> getItem(final Long id) throws NotFoundException {
        if (mItem.existsById(id))
            return mItem.findById(id);
        throw new NotFoundException(GET, "item", id.toString());
    }

    public Iterable<Item> getItems() {
        return mItem.findAll();
    }

    public Item saveItem(Item item) throws AlreadyExistsException {
        return mItem.save(item);
    }

    public Item updateItem(Item item) throws NotFoundException {
        if (mItem.existsById(item.getId()))
            return mItem.save(item);
        throw new NotFoundException(UPDATE, "item", item.getId().toString());
    }

    public void deleteItem(final Long id) throws NotFoundException {
        if (mItem.existsById(id))
            mItem.deleteById(id);
        throw new NotFoundException(DELETE, "item", id.toString());
    }
}
