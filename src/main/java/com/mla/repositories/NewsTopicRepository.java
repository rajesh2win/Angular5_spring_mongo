package com.mla.repositories;
import com.mla.models.NewsTopic;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rnallamothu on 4/15/18.
 */
public interface NewsTopicRepository extends CrudRepository<NewsTopic, String> {
    @Override
    void delete(NewsTopic deleted);
}



