package com.MADG2.dao;

import com.MADG2.model.entities.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao extends JpaRepository<New, Long> {

    // Recupera un listado de noticias ordenado
    @Query(value = "select e from New e")
    List<New> findAll(Sort sort);

    // Recupera un listado de noticias que se puede paginar (Pageable)
    // Pageable ya incluye ordenamiento
    //@Query(value = "select e from News e", countQuery = "select count(e) from News e")

    Page<New> findAll(Pageable pageable);

    Page<New> findNewsByTitle(String title, Pageable pageable);
    Page<New> findNewsByTitleContaining(String title, Pageable pageable);

}
