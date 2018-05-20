package com.mla.repositories;

import com.mla.models.Mandal4;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface Mandal4Repository extends CrudRepository<Mandal4, String> {
    @Query
    public Optional<Mandal4> findByTopicName(String topicName);

}
