package com.mdp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SysApplication   {
	 
	public static void main(String[] args) {
		SpringApplication.run(SysApplication.class,args);
	}
}
