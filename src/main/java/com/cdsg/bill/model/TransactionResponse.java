package com.cdsg.bill.model;

import lombok.Data;

@Data
public class TransactionResponse {

    private String status_message;
    private long transaction_id;
    private long amount;
    private String transaction_date;
    private String phone_number;

}
