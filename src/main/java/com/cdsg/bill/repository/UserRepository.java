package com.cdsg.bill.repository;

import com.cdsg.bill.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {

    UserDao findByUsername(String username);

}