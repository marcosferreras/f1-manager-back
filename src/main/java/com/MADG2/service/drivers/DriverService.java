package com.MADG2.service.drivers;

import com.MADG2.model.dtos.drivers.DriverDto;
import com.MADG2.model.entities.Driver;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverService {
    DriverDto findByIdDto(Long idDriver);
    Driver findById(Long idDriver) throws Exception;
    Page<DriverDto> findAll(Long teamId, Pageable pageable) throws ChangeSetPersister.NotFoundException;
    Page<DriverDto> findAll(Pageable pageable);
    void delete(long id);
    void save(DriverDto driverDto) throws Exception;
    void update(long driverId, DriverDto driverDto) throws ChangeSetPersister.NotFoundException;
}
