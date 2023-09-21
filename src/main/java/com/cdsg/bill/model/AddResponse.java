package com.cdsg.bill.model;

import lombok.Data;

@Data
public class AddResponse {

    private String status_message;
    private String date_time;
    private String bill_id;
    private String name;
    private String description;

}
