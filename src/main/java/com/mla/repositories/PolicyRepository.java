package com.mla.repositories;

import com.mla.models.Policy;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface PolicyRepository  extends CrudRepository<Policy, String> {
    @Override
    void delete(Policy deleted);
}
