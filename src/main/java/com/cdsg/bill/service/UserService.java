package com.cdsg.bill.service;

import com.cdsg.bill.dao.UserDao;
import com.cdsg.bill.model.UserRequest;

public interface UserService {

    public UserDao createNewUser(UserRequest userRequest);

}
