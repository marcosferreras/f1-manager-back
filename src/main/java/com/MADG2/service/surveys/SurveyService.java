package com.MADG2.service.surveys;

import com.MADG2.model.dtos.surveys.ResultDto;
import com.MADG2.model.dtos.surveys.SurveyDto;
import com.MADG2.model.dtos.votes.VoteDto;
import com.MADG2.model.entities.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SurveyService {
    List<Survey> findAll(Sort sort);
    Page<SurveyDto> findAll(Pageable pageable);
    void delete(Long id);
    void save(SurveyDto dto);
    void update(Long idSurvey, SurveyDto dto) throws Exception;
    List<SurveyDto> searchSurvey();
    void addVote(Long idSurvey, VoteDto voteDto) throws Exception;

    ResultDto showResults(Long idSurvey) throws Exception;
}
