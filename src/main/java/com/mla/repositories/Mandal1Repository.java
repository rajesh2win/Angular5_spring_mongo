package com.mla.repositories;

import com.mla.models.Mandal1;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface Mandal1Repository extends CrudRepository<Mandal1, String> {
    @Query
    public Optional<Mandal1> findByTopicName(String topicName);

}
