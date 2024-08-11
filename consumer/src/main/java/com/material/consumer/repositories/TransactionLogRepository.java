package com.material.consumer.repositories;

import com.material.consumer.model.entity.TransactionLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionLogRepository extends CrudRepository<TransactionLog, UUID> {
    List<TransactionLog> findByCardNumber(String cardNumber);

//    @Query(value = "SELECT SUM(t.amount) FROM TransactionLog t where Date(t.ins_date) = Date(:date) and transaction_type = 'DEBIT' and card_number = :cardNumber", nativeQuery = true)
//    Double amountWithdrawnToday(String cardNumber, LocalDateTime date);
}
