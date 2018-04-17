package com.mla.repositories;
import com.mla.models.Topic;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rnallamothu on 4/15/18.
 */
    public interface TopicRepository extends CrudRepository<Topic, String> {
        @Override
        void delete(Topic deleted);
    }



