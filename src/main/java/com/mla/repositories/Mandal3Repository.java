package com.mla.repositories;

import com.mla.models.Mandal3;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface Mandal3Repository extends CrudRepository<Mandal3, String> {
    @Query
    public Optional<Mandal3> findByTopicName(String topicName);

}
