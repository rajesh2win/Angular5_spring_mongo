package com.mla.repositories;

import com.mla.models.PhoneNumbers;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rnallamothu on 5/1/18.
 */
public interface PhoneNumbersRepository extends CrudRepository<PhoneNumbers, String> {
    @Override
    void delete(PhoneNumbers deleted);
}
