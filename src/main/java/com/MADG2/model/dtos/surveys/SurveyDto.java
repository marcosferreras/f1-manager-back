package com.MADG2.model.dtos.surveys;

import com.MADG2.model.dtos.drivers.DriverDto;
import com.MADG2.model.dtos.votes.VoteDto;
import com.MADG2.model.entities.Driver;
import com.MADG2.model.entities.Vote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class SurveyDto {

    private Long id;

    private String link;

    private String title;

    private String description;

    private Date limitDate;

    @JsonIgnore
    private List<Vote> votesEntity;

    private List<VoteDto> votes;

    private List<DriverDto> drivers;

    @JsonIgnore
    private List<Driver> driversEntity;

    private List<Long> driversId;

}
