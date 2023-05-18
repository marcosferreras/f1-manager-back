package com.MADG2.model.mappers.surveys;

import com.MADG2.model.dtos.surveys.SurveyDto;
import com.MADG2.model.entities.Survey;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface SurveyMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "driversEntity", source = "drivers")
    @Mapping(target = "votesEntity", source = "votes")
    SurveyDto toDto(Survey survey);

    Survey toBo(SurveyDto dto);

    @Mapping(target = "drivers", ignore = true)
    void merge(@MappingTarget Survey survey, SurveyDto dto);
}
