package com.MADG2.service.teams;

import com.MADG2.common.Constants;
import com.MADG2.dao.TeamDao;
import com.MADG2.model.dtos.team.TeamDto;
import com.MADG2.model.entities.Team;
import com.MADG2.model.mappers.teams.TeamMapper;
import com.MADG2.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<Team> findAll(Sort sort) {
        return teamDao.findAll(sort);
    }

    @Override
    public Page<Team> findAll(Pageable pageable) {
        return teamDao.findAll(pageable);
    }

    @Override
    public Team findById(Long id) throws ChangeSetPersister.NotFoundException {
        return teamDao.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public void delete(long id) {

        teamDao.deleteById(id);
    }

    @Override
    public Team save(Team team) {
        String image = team.getLogo();
        team.setLogo(Constants.UPLOAD_LINK+'/'+image);
        return teamDao.save(team);
    }

    @Override
    public Team findByManager(User user) {
        return teamDao.findByManagersContaining(user);
    }

    public Team update(TeamDto team, long id) throws ChangeSetPersister.NotFoundException {
        String image = team.getLogo();
        team.setLogo(Constants.UPLOAD_LINK+'/'+image);
        Team entityTeam = findById(id);
        teamMapper.merge(entityTeam, team);
        return teamDao.save(entityTeam);
    }

}
