package com.MADG2.service.simulations;


import com.MADG2.model.dtos.simulations.SimulationDTO;

public interface SimulationsService {

    Double consumptionLap (SimulationDTO simulationDTO) throws Exception;
    Double consumptionTotal (SimulationDTO simulationDTO) throws Exception;
    Double ersByLap (SimulationDTO simulationDTO) throws Exception;
    Double ersChargeBatery (SimulationDTO simulationDTO) throws Exception;
}
