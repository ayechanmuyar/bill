package com.cdsg.bill.service.impl;

import com.cdsg.bill.dao.UserDao;
import com.cdsg.bill.model.UserRequest;
import com.cdsg.bill.repository.UserRepository;
import com.cdsg.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDao createNewUser(UserRequest userRequest) {
        UserDao userDao = new UserDao();
        userDao.setUsername(userRequest.getUsername());
        userDao.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return userRepository.save(userDao);
    }

}
