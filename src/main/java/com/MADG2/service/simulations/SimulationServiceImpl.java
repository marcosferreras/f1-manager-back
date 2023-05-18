package com.MADG2.service.simulations;

import com.MADG2.dao.CarDao;
import com.MADG2.dao.CircuitDao;
import com.MADG2.model.dtos.simulations.SimulationDTO;
import com.MADG2.model.entities.Car;
import com.MADG2.model.entities.Circuit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SimulationServiceImpl implements SimulationsService {

    @Autowired
    CarDao carDao;
    @Autowired
    CircuitDao circuitDao;

    public Double consumptionLap(SimulationDTO simulationDTO) throws Exception {

        Car car = carDao.findById(simulationDTO.getCarId()).orElseThrow(() -> new Exception("Car id not found - "));
        Circuit circuit = circuitDao.findById(simulationDTO.getCircuitId()).orElseThrow(() -> new Exception("Circuit id not found - "));

        simulationDTO.setConsumption(car.getConsumption().doubleValue());
        simulationDTO.setMeters(circuit.getMeters().doubleValue());
        Double km = simulationDTO.getMeters()/1000;


        return  (simulationDTO.getConsumption()*km)/100;
    }

    public Double consumptionTotal(SimulationDTO simulationDTO) throws Exception {
        Car car = carDao.findById(simulationDTO.getCarId()).orElseThrow(() -> new Exception("Car id not found - "));
        Circuit circuit = circuitDao.findById(simulationDTO.getCircuitId()).orElseThrow(() -> new Exception("Circuit id not found - "));

        simulationDTO.setConsumption(car.getConsumption().doubleValue());
        simulationDTO.setMeters(circuit.getMeters().doubleValue());
        simulationDTO.setLaps(circuit.getLaps().doubleValue());

        Double kmTotal = (simulationDTO.getMeters()/1000) * simulationDTO.getLaps();

        return  (simulationDTO.getConsumption()*kmTotal)/100;
    }

    @Override
    public Double ersByLap(SimulationDTO simulationDTO) throws Exception {
        Car car = carDao.findById(simulationDTO.getCarId()).orElseThrow(() -> new Exception("Car id not found - "));
        Circuit circuit = circuitDao.findById(simulationDTO.getCircuitId()).orElseThrow(() -> new Exception("Circuit id not found - "));

        Double ersLap = 0.0;
        Double drivingPercentage = 0.0;

        switch (simulationDTO.getDrivingType()){
            case "Economic":
                drivingPercentage = 1.05;
                break;
            case "Normal":
                drivingPercentage = 0.75;
                break;
            case "Sport":
                drivingPercentage = 0.40;
                break;
            default:
                throw new Exception("Driving type not found");
        }

        // numero de curvas rapidas (circuito) * Ers curvas rapidas (coche) * %tipo conduccion
        Double ersFast = circuit.getFastCurves() * car.getERS_FastCurve();
        Double ersFastDriving = ersFast * drivingPercentage;
        ersLap += ersFastDriving;

        // numero de curvas lentas (circuito) * Ers curvas lentas (coche) * %tipo conduccion
        Double ersSlow = circuit.getSlowCurves() * car.getERS_SlowCurve();
        Double ersSlowDriving = ersSlow * drivingPercentage;
        ersLap += ersSlowDriving;

        // numero de curvas medias (circuito) * Ers curvas medias (coche) * %tipo conduccion
        Double ersMed = circuit.getMediumCurves() * car.getERS_MediumCurve();
        Double ersMediumDriving = ersMed * drivingPercentage;
        ersLap += ersMediumDriving;

        return ersLap;
    }

    @Override
    public Double ersChargeBatery(SimulationDTO simulationDTO) throws Exception {
        Double batery = 1.2;
        Double ersLap = ersByLap(simulationDTO);
        Double numberOfLaps = 0.0;

        while(batery > 0.0){
            batery -= ersLap;
            numberOfLaps += 1.0;
        }

        return numberOfLaps;
    }
}
