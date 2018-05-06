package com.mla.repositories;

import com.mla.models.NewsTopic;
import org.springframework.data.mongodb.repository.Query;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by rnallamothu on 4/28/18.
 */
public interface TopicImageRepository extends CrudRepository<NewsTopic, String>  {
@Query
public Optional<NewsTopic> findByTopicName(String topicName);

}
