package com.material.consumer.repositories;

import com.material.consumer.model.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, String> {
    //Optional<Card> findByCardNumber(String cardNumber);

    @Query("select distinct c.id from Card c")
    List<String> getAllIds();
}
