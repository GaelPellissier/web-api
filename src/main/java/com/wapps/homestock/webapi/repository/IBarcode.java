package com.wapps.homestock.webapi.repository;

import com.wapps.homestock.webapi.model.Barcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarcode extends CrudRepository<Barcode, Long> {
}
