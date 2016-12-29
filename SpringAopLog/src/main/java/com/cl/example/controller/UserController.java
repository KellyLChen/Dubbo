package com.cl.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.example.annotation.SystemLog;
import com.cl.example.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/delete")
	@ResponseBody
	@SystemLog(module="user",method="deleteUser")
	public String deleteUser(){
		String userName = userService.deleteUser();
		System.out.println("My name is "+userName);
		return userName;
	}
}
