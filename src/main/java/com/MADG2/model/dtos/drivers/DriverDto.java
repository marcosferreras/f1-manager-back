package com.MADG2.model.dtos.drivers;

import com.MADG2.model.dtos.team.TeamDto;
import lombok.Data;


@Data
public class DriverDto {

    private Long id;

    private String name;

    private String lastName;

    private String acronym;

    private Long number;

    private String image;

    private String country;

    private String twitter;
    private Long teamId;

    private TeamDto teamDto;

    //private List<Vote> votes;

    //private List<Survey> surveys;
}
