package com.cdsg.bill.model;

import lombok.Data;

import java.util.List;

@Data
public class BillListResponse {

    private String status_message;
    private String date_time;
    private List<BillResponse> billers;

}
