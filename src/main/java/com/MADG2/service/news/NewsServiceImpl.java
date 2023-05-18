package com.MADG2.service.news;

import com.MADG2.common.Constants;
import com.MADG2.dao.NewsDao;
import com.MADG2.model.dtos.news.SearchNewDto;
import com.MADG2.model.entities.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<New> findAll(Sort sort) {
        return newsDao.findAll(sort);
    }

    @Override
    public Page<New> findAll(Pageable pageable) {
        return newsDao.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        newsDao.deleteById(id);
    }

    @Override
    public void save(New entity) {
        entity.setImage(Constants.UPLOAD_LINK+'/'+entity.getImage());
        newsDao.save(entity);
    }

    @Override
    public Page<New> findNew(SearchNewDto searchNewDto, Pageable pageable) {
        return newsDao.findNewsByTitleContaining(searchNewDto.getTitle(), pageable);
    }

    @Override
    public void update(Long idNew, New entity) throws Exception {
        New updateNew = newsDao.findById(idNew).orElseThrow(() -> new Exception("New id not found - " + idNew));

        updateNew.setLink(entity.getLink());
        updateNew.setText(entity.getText());
        updateNew.setTitle(entity.getTitle());
        updateNew.setImage(Constants.UPLOAD_LINK+'/'+entity.getImage());

        newsDao.save(updateNew);
    }
}
