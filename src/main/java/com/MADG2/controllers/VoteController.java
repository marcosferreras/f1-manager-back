package com.MADG2.controllers;

import com.MADG2.model.dtos.votes.VoteDto;
import com.MADG2.service.votes.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class VoteController {
    @Autowired
    VoteService voteService;

    @GetMapping
    public Page<VoteDto> findALl(Pageable pageable) {
        return voteService.findAll(pageable);
    }

    @PostMapping
    public void save(@RequestBody VoteDto voteDto){
        voteService.save(voteDto);
    }

}
