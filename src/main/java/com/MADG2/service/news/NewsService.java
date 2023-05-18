package com.MADG2.service.news;


import com.MADG2.model.dtos.news.SearchNewDto;
import com.MADG2.model.entities.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface NewsService {

    List<New> findAll(Sort sort);
    Page<New> findAll(Pageable pageable);
    void delete(Long id);
    void save(New entity);
    Page<New> findNew(SearchNewDto searchNewDto, Pageable pageable);
    void update(Long idNew, New entity) throws Exception;


}
