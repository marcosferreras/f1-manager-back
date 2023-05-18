package com.MADG2.controllers;


import com.MADG2.model.dtos.simulations.SimulationDTO;
import com.MADG2.service.simulations.SimulationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class SimulationsController {

    @Autowired
    SimulationsService simulationsService;

    @PostMapping("/consumptionLap")
    public Double consumptionByLap(@RequestBody SimulationDTO simulationDTO) throws Exception {
        return simulationsService.consumptionLap(simulationDTO);
    }

    @PostMapping("/consumptionTotal")
    public Double consumptionByCircuit(@RequestBody SimulationDTO simulationDTO) throws Exception {
        return simulationsService.consumptionTotal(simulationDTO);
    }

    @PostMapping("/ersLap")
    public Double ersByLap(@RequestBody SimulationDTO simulationDTO) throws Exception{
        return simulationsService.ersByLap(simulationDTO);
    }

    @PostMapping("/ersCharge")
    public Double ersChargeBattery(@RequestBody SimulationDTO simulationDTO) throws Exception{
        return simulationsService.ersChargeBatery(simulationDTO);
    }
}
