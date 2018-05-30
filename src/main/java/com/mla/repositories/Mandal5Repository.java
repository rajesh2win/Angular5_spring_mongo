package com.mla.repositories;

import com.mla.models.Mandal5;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface Mandal5Repository extends CrudRepository<Mandal5, String> {
    @Query
    public Optional<Mandal5> findByTopicName(String topicName);

}
