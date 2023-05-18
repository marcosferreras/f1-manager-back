package com.MADG2.model.dtos.simulations;

import lombok.Data;

@Data
public class SimulationDTO {
    private Long carId;
    private Long circuitId;
    private String drivingType;
    private Double laps;

    private Double meters;

    private Double consumption;
    private Double ErsLap;
    private Double ErsBatery;
}
