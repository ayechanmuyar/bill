package com.cdsg.bill.model;

import lombok.Data;

@Data
public class TransactionHistoryResponse {

    private String api_caller;
    private long id;
    private long amount;
    private String reference_no;
    private String phone_number;

}
