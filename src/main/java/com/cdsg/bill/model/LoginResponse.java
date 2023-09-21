package com.cdsg.bill.model;

import lombok.Data;

@Data
public class LoginResponse {

    private String status_message;
    private String access_token;

}
