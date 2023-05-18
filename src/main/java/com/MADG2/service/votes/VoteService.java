package com.MADG2.service.votes;

import com.MADG2.model.dtos.votes.VoteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VoteService {
    Page<VoteDto> findAll(Pageable pageable);
    void save(VoteDto dto);
}
