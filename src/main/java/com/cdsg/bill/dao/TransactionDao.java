package com.cdsg.bill.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "testing_transaction")
public class TransactionDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transaction_id;

    @Column
    private String api_caller;

    @Column
    private String reference_no;

    @Column
    private String phone_number;

    @Column
    private long amount;

    @Column
    private long bill_id;

    @Column
    private String transaction_date;

}
