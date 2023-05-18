package com.MADG2.model.mappers.drivers;

import com.MADG2.model.dtos.drivers.DriverDto;
import com.MADG2.model.entities.Driver;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DriverMapper {

    @Mapping(target = "id", source = "id")
    DriverDto toDto(Driver driver);

    @Mapping(target = "teamId", source = "teamId")
    Driver toBo(DriverDto driverDto);

    void merge(@MappingTarget Driver driver, DriverDto dto);
}
