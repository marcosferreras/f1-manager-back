package com.MADG2.controllers;

import com.MADG2.model.dtos.surveys.ResultDto;
import com.MADG2.model.dtos.surveys.SurveyDto;
import com.MADG2.model.dtos.votes.VoteDto;
import com.MADG2.service.surveys.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @GetMapping
    public Page<SurveyDto> findALl(Pageable pageable) {
        return surveyService.findAll(pageable);
    }

    @GetMapping ("/search-survey")
    public List<SurveyDto> searchSurvey(){
        return surveyService.searchSurvey();
    }

    @PostMapping("/new")
    public void createSurvey(@RequestBody SurveyDto dto){
        surveyService.save(dto);
    }

    @DeleteMapping("/{idSurvey}")
    public void deleteSurvey(@PathVariable Long idSurvey){
        surveyService.delete(idSurvey);
    }

    @PutMapping("/{idSurvey}")
    public void updateSurvey(@PathVariable Long idSurvey, @RequestBody SurveyDto dto) throws Exception {
        surveyService.update(idSurvey, dto);
    }

    @PostMapping("/{idSurvey}/add-vote")
    public void addVote(@PathVariable Long idSurvey, @RequestBody VoteDto voteDto) throws Exception {
        surveyService.addVote(idSurvey, voteDto);
    }

    @GetMapping("/{idSurvey}")
    public ResultDto showResultsSurvey(@PathVariable Long idSurvey) throws Exception {
        return surveyService.showResults(idSurvey);
    }
}
