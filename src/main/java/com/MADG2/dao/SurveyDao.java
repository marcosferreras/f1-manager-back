package com.MADG2.dao;

import com.MADG2.model.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SurveyDao extends JpaRepository<Survey, Long> {

    List<Survey> searchSurveyByLimitDateAfter(Date date);
}
