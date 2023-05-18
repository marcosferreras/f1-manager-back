package com.MADG2.model.dtos.surveys;

import com.MADG2.model.dtos.votes.DriverVoteDto;
import lombok.Data;

import java.util.List;

@Data
public class ResultDto {
    private List<DriverVoteDto> driverVoteDtos;
    private Long totalVotes;

}
