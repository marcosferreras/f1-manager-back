package com.MADG2.model.mappers.votes;

import com.MADG2.model.dtos.votes.VoteDto;
import com.MADG2.model.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface VoteMapper {

    VoteDto toDto(Vote entity);

    Vote toBo(VoteDto dto);
}
