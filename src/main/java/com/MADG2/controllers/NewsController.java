package com.MADG2.controllers;

import com.MADG2.model.dtos.news.SearchNewDto;
import com.MADG2.model.entities.New;
import com.MADG2.service.news.NewsService;
import com.MADG2.service.uploads.UploadFileService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

//@Api(value = "CRUD News")
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    UploadFileService uploadFileService;

    @GetMapping
    public Page<New> findALl(Pageable pageable) {
        return newsService.findAll(pageable);
    }

    @PostMapping("/findNew")
    public Page<New> findNew(@RequestBody SearchNewDto searchNewDto, Pageable pageable){
        return newsService.findNew(searchNewDto, pageable);
    }

    @PostMapping("/new")
    public void createNew(@RequestBody New entity){
        newsService.save(entity);
    }

    @DeleteMapping("/{idNew}")
    public void deleteNew(@PathVariable Long idNew){
        newsService.delete(idNew);
    }

    @PutMapping("/{idNew}")
    public void updateNew(@PathVariable Long idNew, @RequestBody New entity) throws Exception {
        newsService.update(idNew, entity);
    }

}
