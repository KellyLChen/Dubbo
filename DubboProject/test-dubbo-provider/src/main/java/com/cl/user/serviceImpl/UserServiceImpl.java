package com.cl.user.serviceImpl;

import org.springframework.stereotype.Service;

import com.cl.user.servicei.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Override
	public String sayHello() {
		
		System.out.println("hello world----------------------------");
		
		return "hello world";
		
	}
}
