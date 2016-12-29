package com.cl.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cl.user.servicei.UserService;

public class MyTest {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/*.xml" });
		UserService service = (UserService) context.getBean("userService");
		System.out.println(service.sayHello());
	}
}
