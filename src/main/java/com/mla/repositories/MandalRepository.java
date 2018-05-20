package com.mla.repositories;

import com.mla.models.Mandal;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface MandalRepository extends CrudRepository<Mandal, String> {
    @Query
    public Optional<Mandal> findByTopicName(String topicName);

}
