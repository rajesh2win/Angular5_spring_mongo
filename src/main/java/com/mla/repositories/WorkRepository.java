package com.mla.repositories;

import com.mla.models.Work;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface WorkRepository extends CrudRepository<Work, String> {
    @Query
    public Optional<Work> findByTopicName(String topicName);

}
