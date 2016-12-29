package com.cl.example.service;

import org.springframework.stereotype.Service;

@Service
public class LogService {
	
	public void printLog(String log){
		System.out.println("-----------"+log+"---------");
	}
}
