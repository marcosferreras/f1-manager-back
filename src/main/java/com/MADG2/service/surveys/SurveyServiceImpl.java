package com.MADG2.service.surveys;

import com.MADG2.common.BadRequestException;
import com.MADG2.dao.SurveyDao;
import com.MADG2.model.dtos.surveys.ResultDto;
import com.MADG2.model.dtos.surveys.SurveyDto;
import com.MADG2.model.dtos.votes.DriverVoteDto;
import com.MADG2.model.dtos.votes.VoteDto;
import com.MADG2.model.entities.Driver;
import com.MADG2.model.entities.Survey;
import com.MADG2.model.entities.Vote;
import com.MADG2.model.mappers.drivers.DriverMapper;
import com.MADG2.model.mappers.surveys.SurveyMapper;
import com.MADG2.model.mappers.teams.TeamMapper;
import com.MADG2.model.mappers.votes.VoteMapper;
import com.MADG2.service.drivers.DriverService;
import com.MADG2.service.teams.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    SurveyDao surveyDao;

    @Autowired
    DriverService driverService;

    @Autowired
    SurveyMapper surveyMapper;

    @Autowired
    DriverMapper driverMapper;

    @Autowired
    VoteMapper voteMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    TeamService teamService;

    @Override
    public List<Survey> findAll(Sort sort) {
        return surveyDao.findAll(sort);
    }

    @Override
    public Page<SurveyDto> findAll(Pageable pageable) {
        List<SurveyDto> list = surveyDao.findAll(pageable).stream().map(surveyMapper::toDto).collect(Collectors.toList());
        list.forEach(surveyDto -> surveyDto.setDrivers(surveyDto.getDriversEntity().stream().map(driverMapper::toDto).map(driver -> {
            try {
                driver.setTeamDto(teamMapper.toDto(teamService.findById(driver.getTeamId())));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return driver;
        }).collect(Collectors.toList())));
        list.forEach(surveyDto -> surveyDto.setVotes(surveyDto.getVotesEntity().stream().map(voteMapper::toDto).collect(Collectors.toList())));
        return new PageImpl<>(list,pageable, surveyDao.findAll(pageable).getTotalElements());
    }

    @Override
    public void delete(Long id) {
        surveyDao.deleteById(id);
    }

    @Override
    public void save(SurveyDto dto) {
        List<Driver> drivers = new ArrayList<>();
        Survey entity = surveyMapper.toBo(dto);
        dto.getDriversId().forEach(driverId -> {try {drivers.add(driverService.findById(driverId));} catch (Exception e) {throw new RuntimeException(e);}});
        entity.setDrivers(drivers);
        surveyDao.save(entity);
    }

    @Override
    public void update(Long idSurvey, SurveyDto dto) throws Exception {
        Survey updateSurvey = surveyDao.findById(idSurvey).orElseThrow(() -> new Exception("Survey id not found - " + idSurvey));
        List<Driver> drivers = new ArrayList<>();
        dto.getDriversId().forEach(driverId -> {try {drivers.add(driverService.findById(driverId));} catch (Exception e) {throw new RuntimeException(e);}});
        updateSurvey.setDrivers(drivers);
        surveyMapper.merge(updateSurvey, dto);
        surveyDao.save(updateSurvey);
    }

    @Override
    public List<SurveyDto> searchSurvey() {
        List<SurveyDto> surveyDtos = surveyDao.searchSurveyByLimitDateAfter(new Date()).stream().map(surveyMapper::toDto).collect(Collectors.toList());
        surveyDtos.forEach(surveyDto -> surveyDto.setDrivers(surveyDto.getDriversEntity().stream().map(driverMapper::toDto).collect(Collectors.toList())));
        surveyDtos.forEach(surveyDto -> surveyDto.setVotes(surveyDto.getVotesEntity().stream().map(voteMapper::toDto).collect(Collectors.toList())));

        return surveyDtos;
    }

    @Override
    public void addVote(Long idSurvey, VoteDto voteDto) throws Exception {
        Survey updateSurvey = surveyDao.findById(idSurvey).orElseThrow(() -> new Exception("Survey id not found - " + idSurvey));
        voteDto.setSurveyId(idSurvey);
        updateSurvey.getVotes().forEach(vote -> {
                try {
                    if(Objects.equals(vote.getEmail(), voteDto.getEmail()))
                        throw new BadRequestException("Email already used");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        });
        updateSurvey.getVotes().add(voteMapper.toBo(voteDto));
        surveyDao.save(updateSurvey);
    }

    @Override
    public ResultDto showResults(Long idSurvey) throws Exception {
        ResultDto result = new ResultDto();
        result.setTotalVotes((long)0);
        Survey survey = surveyDao.findById(idSurvey).orElseThrow(() -> new Exception("Survey id not found - " + idSurvey));
        List<Vote> votes = survey.getVotes();

        votes.sort(Comparator.comparing(Vote::getDriverId));

        Long previousIdDriver = (long)0;
        DriverVoteDto driverVoteDto;
        List<DriverVoteDto> driverVoteDtoList = new ArrayList<>();
        result.setDriverVoteDtos(driverVoteDtoList);
        for(Vote vote : votes){
            if(!Objects.equals(vote.getDriverId(), previousIdDriver)){
                driverVoteDto = new DriverVoteDto();
                driverVoteDto.setIdDriver(vote.getDriverId());
                driverVoteDto.setVotes((long) 1);

                driverVoteDtoList.add(driverVoteDto);
            } else{
                driverVoteDto = result.getDriverVoteDtos().get(0);
                driverVoteDto.setIdDriver(vote.getDriverId());
                driverVoteDto.setVotes(driverVoteDto.getVotes() + 1);

                int index = driverVoteDtoList.size() - 1;
                driverVoteDtoList.remove(index);
                driverVoteDtoList.add(driverVoteDto);
            }
            result.setTotalVotes(result.getTotalVotes() + 1 );
            previousIdDriver = vote.getDriverId();


        }


        return result;
    }
}
