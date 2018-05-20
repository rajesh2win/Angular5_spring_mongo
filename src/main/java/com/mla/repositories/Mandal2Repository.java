package com.mla.repositories;

import com.mla.models.Mandal2;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface Mandal2Repository extends CrudRepository<Mandal2, String> {
    @Query
    public Optional<Mandal2> findByTopicName(String topicName);

}
