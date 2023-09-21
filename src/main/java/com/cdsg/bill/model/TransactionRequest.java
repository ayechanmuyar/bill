package com.cdsg.bill.model;

import lombok.Data;

@Data
public class TransactionRequest {

    private String api_caller;
    private long bill_id;
    private long amount;
    private String reference_no;
    private String phone_number;

}
