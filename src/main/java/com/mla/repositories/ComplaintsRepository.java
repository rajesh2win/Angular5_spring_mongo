package com.mla.repositories;

import com.mla.models.Complaints;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface ComplaintsRepository extends CrudRepository<Complaints, String>

    {
        @Override
        void delete(Complaints deleted);
    }



