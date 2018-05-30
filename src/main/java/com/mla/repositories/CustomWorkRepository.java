package com.mla.repositories;

import com.mla.models.Work;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 4/28/18.
 */
public interface CustomWorkRepository extends CrudRepository<Work, String>  {
    @Query
    public Iterable<Work> findByItem(String item);

}