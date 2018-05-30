package com.mla.repositories;

import com.mla.models.Ads;
import org.springframework.data.repository.CrudRepository;

public interface AdsRepository extends CrudRepository<Ads, String> {
    @Override
    void delete(Ads deleted);
}