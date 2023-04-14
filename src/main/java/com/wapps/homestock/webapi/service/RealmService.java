package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Realm;
import com.wapps.homestock.webapi.repository.IRealm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class RealmService {
    @Autowired
    private IRealm mRealm;

    public Optional<Realm> getRealm(final Long id) throws NotFoundException {
        if (mRealm.existsById(id))
            return mRealm.findById(id);
        throw new NotFoundException("No realm found for ID : " + id);
    }

    public Iterable<Realm> getRealmsByOwnerId(final Long ownerId) { return mRealm.findByOwnerId(ownerId); }

    public Iterable<Realm> getRealms() { return mRealm.findAll(); }

    public Realm saveRealm(Realm realm) {
        return mRealm.save(realm);
    }

    public Realm updateRealm(Realm realm) throws NotFoundException {
        if (mRealm.existsById(realm.getId()))
            return mRealm.save(realm);
        throw new NotFoundException("No realm found for ID : " + realm.getId());
    }

    public void deleteRealm(final Long id) throws NotFoundException {
        if (mRealm.existsById(id))
            mRealm.deleteById(id);
        throw new NotFoundException("No realm found for ID : " + id);
    }
}
