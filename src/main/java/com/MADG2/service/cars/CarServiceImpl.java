package com.MADG2.service.cars;

import com.MADG2.common.BadRequestException;
import com.MADG2.dao.CarDao;
import com.MADG2.model.dtos.cars.CarDto;
import com.MADG2.model.dtos.team.TeamDto;
import com.MADG2.model.entities.Car;
import com.MADG2.model.mappers.cars.CarMapper;
import com.MADG2.model.mappers.teams.TeamMapper;
import com.MADG2.service.teams.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    CarDao carDao;

    @Autowired
    TeamService teamService;

    @Autowired
    CarMapper carMapper;

    @Autowired
    TeamMapper teamMapper;

    @Override
    public void delete(long id) {
        carDao.deleteById(id);
    }

    @Override
    public void update(Long carId, CarDto carDto) throws ChangeSetPersister.NotFoundException {
        Car car = carDao.findById(carId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        carMapper.merge(car,carDto);
        carDao.save(car);
    }
    @Override
    public Page<CarDto> findAll(Long teamId, Pageable pageable) throws ChangeSetPersister.NotFoundException {
        TeamDto teamDto = teamMapper.toDto(teamService.findById(teamId));
        Page<CarDto> result = carDao.findAllByTeamId(teamId,pageable).map(carMapper::toDto);
        result.forEach(carDto -> carDto.setTeamDto(teamDto));
        return new PageImpl<>(result.toList(),pageable, result.getTotalElements());
    }

    @Override
    public Page<CarDto> findAll(Pageable pageable) {
        Page<CarDto> result = carDao.findAll(pageable).map(carMapper::toDto);
        result.forEach(carDto -> {
            try {
                carDto.setTeamDto(teamMapper.toDto(teamService.findById(carDto.getTeamId())));
            } catch (ChangeSetPersister.NotFoundException e) {
                throw new RuntimeException("Team not found", e);
            }
        });
        return new PageImpl<>(result.toList(),pageable, result.getTotalElements());
    }

    @Override
    public void save(CarDto carDto) throws Exception {
        try{
            carDao.save(carMapper.toBo(carDto));
        }catch (Exception e){
            throw new BadRequestException("Can't add car with a code already in use");
        }
    }
}
