package com.test;

import org.apache.log4j.PropertyConfigurator;

public class Start {
	 static{  
	        PropertyConfigurator.configure("src/main/resources/log4j.properties");  
	    }  
	public static void main(String[] args) {
		
		com.alibaba.dubbo.container.Main.main(args);
	}
}
