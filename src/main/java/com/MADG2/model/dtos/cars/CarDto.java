package com.MADG2.model.dtos.cars;

import com.MADG2.model.dtos.team.TeamDto;
import lombok.Data;

@Data
public class CarDto {

    private Long id;

    private String name;

    private String code;

    private Double ERS_SlowCurve;

    private Double ERS_MediumCurve;

    private Double ERS_FastCurve;

    private Long consumption;

    private Long teamId;
    private TeamDto teamDto;

}
