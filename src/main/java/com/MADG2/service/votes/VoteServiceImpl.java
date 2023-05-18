package com.MADG2.service.votes;

import com.MADG2.dao.VoteDao;
import com.MADG2.model.dtos.votes.VoteDto;
import com.MADG2.model.mappers.votes.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    VoteDao voteDao;

    @Autowired
    VoteMapper voteMapper;

    @Override
    public Page<VoteDto> findAll(Pageable pageable) {
        return voteDao.findAll(pageable).map(voteMapper::toDto);
    }

    @Override
    public void save(VoteDto dto) {
        voteDao.save(voteMapper.toBo(dto));
    }
}
