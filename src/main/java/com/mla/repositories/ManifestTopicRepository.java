package com.mla.repositories;
import com.mla.models.ManifestTopic;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by rnallamothu on 4/29/18.
 */
public interface ManifestTopicRepository  extends CrudRepository<ManifestTopic, String> {
    @Override
    void delete(ManifestTopic deleted);
}
