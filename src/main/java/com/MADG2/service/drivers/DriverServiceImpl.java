package com.MADG2.service.drivers;

import com.MADG2.common.BadRequestException;
import com.MADG2.common.Constants;
import com.MADG2.dao.DriverDao;
import com.MADG2.model.dtos.drivers.DriverDto;
import com.MADG2.model.dtos.team.TeamDto;
import com.MADG2.model.entities.Driver;
import com.MADG2.model.mappers.drivers.DriverMapper;
import com.MADG2.model.mappers.teams.TeamMapper;
import com.MADG2.service.teams.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    DriverDao driverDao;

    @Autowired
    TeamService teamService;
    @Autowired
    DriverMapper driverMapper;
    @Autowired
    TeamMapper teamMapper;

    @Override
    public DriverDto findByIdDto(Long idDriver) {
        return driverMapper.toDto(driverDao.findById(idDriver).get());
    }

    @Override
    public Page<DriverDto> findAll(Long teamId, Pageable pageable) throws ChangeSetPersister.NotFoundException {
        TeamDto teamDto = teamMapper.toDto(teamService.findById(teamId));
        Page<DriverDto> result = driverDao.findAllByTeamId(teamId,pageable).map(driverMapper::toDto);
        result.forEach(driverDto -> driverDto.setTeamDto(teamDto));
        return new PageImpl<>(result.toList(),pageable, result.getTotalElements());
    }

    @Override
    public Page<DriverDto> findAll(Pageable pageable) {
        Page<DriverDto> result = driverDao.findAll(pageable).map(driverMapper::toDto);
        result.forEach(driverDto -> {
            try {
                driverDto.setTeamDto(teamMapper.toDto(teamService.findById(driverDto.getTeamId())));
            } catch (ChangeSetPersister.NotFoundException e) {
                throw new RuntimeException("Team not found", e);
            }
        });
        return new PageImpl<>(result.toList(),pageable, result.getTotalElements());
    }
    @Override
    public Driver findById(Long idDriver) throws Exception {
        return driverDao.findById(idDriver).orElseThrow(() -> new Exception("Driver id not found - " + idDriver));
    }
    @Override
    public void delete(long id) {
        driverDao.deleteById(id);
    }

    @Override
    public void save(DriverDto driverDto) throws Exception {
        try {
            String image = driverDto.getImage();
            driverDto.setImage(Constants.UPLOAD_LINK+'/'+image);
            driverDao.save(driverMapper.toBo(driverDto));
        }catch (Exception e){
            throw new BadRequestException("Can't add driver with a code already in use");
        }
    }

    @Override
    public void update(long driverId, DriverDto driverDto) throws ChangeSetPersister.NotFoundException {
        Driver driver = driverDao.findById(driverId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        String image = driverDto.getImage();
        driverDto.setImage(Constants.UPLOAD_LINK+'/'+image);
        driverMapper.merge(driver,driverDto);
        driverDao.save(driver);
    }


}
