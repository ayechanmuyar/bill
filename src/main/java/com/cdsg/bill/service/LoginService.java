package com.cdsg.bill.service;

import com.cdsg.bill.model.LoginResponse;
import com.cdsg.bill.model.UserRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService {

    public LoginResponse login(UserRequest userRequest);

}
