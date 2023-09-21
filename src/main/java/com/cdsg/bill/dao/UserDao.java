package com.cdsg.bill.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "testing_user")
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    private String password;

}
