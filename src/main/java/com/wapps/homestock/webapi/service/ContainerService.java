package com.wapps.homestock.webapi.service;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import com.wapps.homestock.webapi.model.Container;
import com.wapps.homestock.webapi.repository.IContainer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ContainerService {
    @Autowired
    private IContainer mContainer;
    private final String GET = "GET_CONTAINER";
    private final String SAVE = "SAVE_CONTAINER";
    private final String UPDATE = "UPDATE_CONTAINER";
    private final String DELETE = "DELETE_CONTAINER";

    public Optional<Container> getContainer(final Long id) throws NotFoundException {
        if (mContainer.existsById(id))
            return mContainer.findById(id);
        throw new NotFoundException(GET, "container", id.toString());
    }

    public Iterable<Container> getContainersByLocationId(final Long locationId) {
        return mContainer.findByLocationId(locationId);
    }
    public Iterable<Container> getContainers() {
        return mContainer.findAll();
    }

    public Container saveContainer(Container container) throws AlreadyExistsException {
        return mContainer.save(container);
    }

    public Container updateContainer(Container container) throws NotFoundException {
        if (mContainer.existsById(container.getId()))
            return mContainer.save(container);
        throw new NotFoundException(UPDATE, "container", container.getId().toString());
    }

    public void deleteContainer(final Long id) throws NotFoundException {
        if (mContainer.existsById(id))
            mContainer.deleteById(id);
        throw new NotFoundException(DELETE, "container", id.toString());
    }
}
