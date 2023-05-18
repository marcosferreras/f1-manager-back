package com.MADG2.dao;

import com.MADG2.model.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteDao extends JpaRepository<Vote, Long> {
}
