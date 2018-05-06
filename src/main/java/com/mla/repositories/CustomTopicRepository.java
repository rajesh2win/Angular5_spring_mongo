package com.mla.repositories;
import com.mla.models.Topic;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by rnallamothu on 4/28/18.
 */
public interface CustomTopicRepository extends CrudRepository<Topic, String>  {
    @Query
    public Optional<Topic> findByTopicName(String topicName);

}
