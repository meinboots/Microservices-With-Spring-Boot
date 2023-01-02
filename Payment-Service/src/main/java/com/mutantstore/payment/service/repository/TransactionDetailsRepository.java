package com.mutantstore.payment.service.repository;

import com.mutantstore.payment.service.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {

    //@Query(value = "SELECT * FROM payment_db.transaction_details where order_id = ?1", nativeQuery = true)
    TransactionDetails findByOrderId(long orderId);

}
