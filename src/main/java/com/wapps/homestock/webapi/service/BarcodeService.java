package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Barcode;
import com.wapps.homestock.webapi.repository.IBarcode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class BarcodeService {
    @Autowired
    private IBarcode mBarcode;
    private final String GET = "GET_BARCODE";
    private final String SAVE = "SAVE_BARCODE";
    private final String UPDATE = "UPDATE_BARCODE";
    private final String DELETE = "DELETE_BARCODE";

    public Optional<Barcode> getBarcode(final Long id) throws NotFoundException {
        if (mBarcode.existsById(id))
            return mBarcode.findById(id);
        throw new NotFoundException(GET, "barcode", id.toString());
    }

    public Iterable<Barcode> getBarcodes() {
        return mBarcode.findAll();
    }

    public Barcode saveBarcode(Barcode barcode) throws AlreadyExistsException {
        return mBarcode.save(barcode);
    }

    public Barcode updateBarcode(Barcode barcode) throws NotFoundException {
        if (mBarcode.existsById(barcode.getId()))
            return mBarcode.save(barcode);
        throw new NotFoundException(UPDATE, "barcode", barcode.getId().toString());
    }

    public void deleteBarcode(final Long id) throws NotFoundException {
        if (mBarcode.existsById(id))
            mBarcode.deleteById(id);
        throw new NotFoundException(DELETE, "barcode", id.toString());
    }
}
