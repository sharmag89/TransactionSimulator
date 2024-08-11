package com.material.authorization.repository;

import com.material.authorization.model.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, String> {

    @Query("select distinct c.id from Card c")
    List<String> getAllIds();
}
