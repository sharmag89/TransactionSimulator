package com.material.authorization.repository;

import com.material.authorization.model.entity.Merchant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MerchantRepository extends CrudRepository<Merchant, Integer> {
    @Query("select distinct m.id from Merchant m")
    List<Integer> getAllIds();
}
