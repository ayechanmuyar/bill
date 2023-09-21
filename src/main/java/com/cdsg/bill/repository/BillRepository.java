package com.cdsg.bill.repository;

import com.cdsg.bill.dao.BillDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillDao, Long> {

}