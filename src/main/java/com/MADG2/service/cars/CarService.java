package com.MADG2.service.cars;

import com.MADG2.model.dtos.cars.CarDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CarService {
    void delete(long id);
    void update(Long carId, CarDto carDto) throws ChangeSetPersister.NotFoundException;
    Page<CarDto> findAll(Long teamId, Pageable pageable) throws ChangeSetPersister.NotFoundException;
    Page<CarDto> findAll(Pageable pageable);

    void save(CarDto carDto) throws Exception;
}
