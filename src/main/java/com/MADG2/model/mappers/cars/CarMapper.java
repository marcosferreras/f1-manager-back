package com.MADG2.model.mappers.cars;

import com.MADG2.model.dtos.cars.CarDto;
import com.MADG2.model.entities.Car;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CarMapper {

    @Mapping(target = "id", source = "id")
    CarDto toDto(Car entity);

    Car toBo(CarDto dto);

    void merge(@MappingTarget Car car, CarDto dto);
}
