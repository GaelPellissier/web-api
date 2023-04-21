package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Location;
import com.wapps.homestock.webapi.repository.ILocation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class LocationService {
    @Autowired
    private ILocation mLocation;
    private final String GET = "GET_LOCATION";
    private final String SAVE = "SAVE_LOCATION";
    private final String UPDATE = "UPDATE_LOCATION";
    private final String DELETE = "DELETE_LOCATION";

    public Optional<Location> getLocation(final Long id) throws NotFoundException {
        if (mLocation.existsById(id))
            return mLocation.findById(id);
        throw new NotFoundException(GET, "location", id.toString());
    }

    public Iterable<Location> getLocationsByRealmId(final Long realmId) {
        return mLocation.findByRealmId(realmId);
    }

    public Iterable<Location> getLocations() {
        return mLocation.findAll();
    }

    public Location saveLocation(Location location) throws AlreadyExistsException {
        return mLocation.save(location);
    }

    public Location updateLocation(Location location) throws NotFoundException {
        if (mLocation.existsById(location.getId()))
            return mLocation.save(location);
        throw new NotFoundException(UPDATE, "location", location.getId().toString());
    }

    public void deleteLocation(final Long id) throws NotFoundException {
        if (mLocation.existsById(id))
            mLocation.deleteById(id);
        throw new NotFoundException(DELETE, "location", id.toString());
    }
}
