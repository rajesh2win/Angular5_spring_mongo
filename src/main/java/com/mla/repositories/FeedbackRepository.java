package com.mla.repositories;
import com.mla.models.Feedback;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface FeedbackRepository extends CrudRepository<Feedback, String> {
    @Override
    void delete(Feedback deleted);
}



