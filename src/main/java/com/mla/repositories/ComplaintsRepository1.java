package com.mla.repositories;

import com.mla.models.Complaints1;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface ComplaintsRepository1 extends CrudRepository<Complaints1, String>

    {
        @Override
        void delete(Complaints1 deleted);
    }



