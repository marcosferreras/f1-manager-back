package com.MADG2.model.dtos.votes;

import lombok.Data;

@Data
public class VoteDto {

    private Long id;

    private String name;

    private String email;

    private Long driverId;

    private Long surveyId;
}
