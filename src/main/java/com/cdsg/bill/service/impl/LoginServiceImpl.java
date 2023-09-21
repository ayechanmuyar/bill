package com.cdsg.bill.service.impl;

import com.cdsg.bill.dao.UserDao;
import com.cdsg.bill.model.LoginResponse;
import com.cdsg.bill.model.UserRequest;
import com.cdsg.bill.repository.UserRepository;
import com.cdsg.bill.service.LoginService;
import com.cdsg.bill.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse login(UserRequest userRequest) {
        UserDao userDao = userRepository.findByUsername(userRequest.getUsername());

        if (userDao != null) {
            LoginResponse response = new LoginResponse();
            List<SimpleGrantedAuthority> roleList = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            response.setAccess_token(jwtUtil.generateToken(new User(userDao.getUsername(), userDao.getPassword(), roleList)));
            response.setStatus_message("Login is successful");
            return response;
        } else {
            LoginResponse response = new LoginResponse();
            response.setAccess_token("");
            response.setStatus_message("Login is not successful");
            return response;
        }
    }

}
