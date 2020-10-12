package com.wine.to.up.am.parser.service.repository;

import com.wine.to.up.am.parser.service.domain.entity.Sugar;
import com.wine.to.up.am.parser.service.domain.entity.Wine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : SSyrova
 * @since : 29.09.2020, вт
 **/
@Repository
public interface WineRepository extends CrudRepository<Wine, Long> {

    List<Wine> findAllByPriceLessThan(Double price);

    Wine findByImportId(String importId);
}
