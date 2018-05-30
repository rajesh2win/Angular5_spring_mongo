package com.mla.repositories;

import com.mla.models.WorkCategory;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface WorkCategoryRepository extends CrudRepository<WorkCategory, String> {
    @Query
    public Optional<WorkCategory> findByTopicName(String topicName);

}
