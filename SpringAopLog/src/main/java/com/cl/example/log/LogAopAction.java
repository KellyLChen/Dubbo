package com.cl.example.log;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cl.example.annotation.SystemLog;
import com.cl.example.service.LogService;

@Aspect
@Component
public class LogAopAction {
	
	@Resource
	private LogService logService;
	
	@Pointcut("execution(* com.cl.example.controller..*.*(..))")  
    private void controllerAspect(){}//定义一个切入点
	
	@Before("controllerAspect() && args(name)")
    public void doAccessCheck(String name){
         System.out.println(name);
         System.out.println("前置通知--------------------------");
    }
	
	 @AfterReturning("controllerAspect()")
     public void doAfter(){
         System.out.println("后置通知-------------------------");
     }
     
     @After("controllerAspect()")
     public void after(){
         System.out.println("最终通知---------------------------");
     }
     
     @AfterThrowing(pointcut = "controllerAspect()",throwing="e")
     public void doAfterThrow(Exception e){
         System.out.println("异常通知-----------------------------------");
         System.out.println(e.getMessage());
     }
     
     @Around("controllerAspect()")
     public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
         System.out.println("进入环绕通知------------------------------");
         
         //获取登录用户账户
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         
         //获得当前登陆的用户
         String name = (String) request.getSession().getAttribute("userName");
         
         Object object = pjp.proceed();//执行该方法
         Class<?> classType = pjp.getTarget().getClass();  //获得Class
         String methodName = pjp.getSignature().getName(); //获得方法名字
         System.out.println("================="+methodName);
         Class<?>[] parameters = ((MethodSignature)pjp.getSignature()).getParameterTypes(); //获得方法参数对用的Class 类型
         Method taMethod = classType.getDeclaredMethod(methodName, parameters); //获得方法
         SystemLog systemLog = taMethod.getAnnotation(SystemLog.class);
         if(null != systemLog){
        	String module = systemLog.module();
        	String method = systemLog.method();
        	System.out.println(module+";"+method);
        	
        	//将 module 和 metho的 保存到数据库 
         }
         
         
         System.out.println("退出方法--------------------");
         return object;
     }
}
