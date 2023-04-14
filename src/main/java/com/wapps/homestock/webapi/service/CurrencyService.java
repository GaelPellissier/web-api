package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Currency;
import com.wapps.homestock.webapi.repository.ICurrency;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class CurrencyService {
    @Autowired
    private ICurrency mCurrency;
    private final String GET = "GET_CURRENCY";
    private final String SAVE = "SAVE_CURRENCY";
    private final String UPDATE = "UPDATE_CURRENCY";
    private final String DELETE = "DELETE_CURRENCY";

    public Optional<Currency> getCurrency(final Long id) throws NotFoundException {
        if (mCurrency.existsById(id))
            return mCurrency.findById(id);
        throw new NotFoundException(GET, "currency", id.toString());
    }

    public Iterable<Currency> getCurrencies() { return mCurrency.findAll(); }

    public Currency saveCurrency(Currency currency) throws AlreadyExistsException {
        if (mCurrency.findByTrigram(currency.getTrigram()).isPresent())
            throw new AlreadyExistsException(SAVE, "Trigram", currency.getTrigram());
        if (mCurrency.findByName(currency.getName()).isPresent())
            throw new AlreadyExistsException(SAVE, "Name", currency.getName());

        return mCurrency.save(currency);
    }

    public Currency updateCurrency(Currency currency) throws NotFoundException {
        if (mCurrency.existsById(currency.getId()))
            return mCurrency.save(currency);
        throw new NotFoundException(UPDATE, "currency", currency.getId().toString());
    }

    public void deleteCurrency(final Long id) throws NotFoundException {
        if (mCurrency.existsById(id))
            mCurrency.deleteById(id);
        throw new NotFoundException(DELETE, "currency", id.toString());
    }
}
