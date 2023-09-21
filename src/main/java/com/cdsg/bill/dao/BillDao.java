package com.cdsg.bill.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "testing_bill")
public class BillDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bill_id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String date_time;

}
