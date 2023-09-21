package com.cdsg.bill.controller;

import com.cdsg.bill.model.UserRequest;
import com.cdsg.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/createNewUser", method = RequestMethod.POST)
	public ResponseEntity<?> createNewUser(@RequestBody UserRequest userRequest) throws Exception {
		return ResponseEntity.ok(userService.createNewUser(userRequest));
	}

}
