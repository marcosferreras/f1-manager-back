package com.MADG2.model.mappers.teams;

import com.MADG2.model.dtos.team.TeamDto;
import com.MADG2.model.entities.Team;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface TeamMapper {

    TeamDto toDto(Team team);
    void merge(@MappingTarget Team team, TeamDto dto);
}
