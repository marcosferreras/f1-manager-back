package com.MADG2.service.teams;

import com.MADG2.model.dtos.team.TeamDto;
import com.MADG2.model.entities.Team;
import com.MADG2.security.entity.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TeamService {

    List<Team> findAll(Sort sort);
    Page<Team> findAll(Pageable pageable);
    Team findById(Long id) throws ChangeSetPersister.NotFoundException;
    void delete(long id);
    Team save(Team team);
    Team findByManager(User user);
    Team update(TeamDto team, long id) throws ChangeSetPersister.NotFoundException;
}
