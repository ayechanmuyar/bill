package com.cdsg.bill.repository;

import com.cdsg.bill.dao.TransactionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDao, Long> {

}