package com.samcancode;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.samcancode.config.DataServiceConfig;
import com.samcancode.entities.Singer;
import com.samcancode.services.SingerService;

public class QueryTestDemo
{
	public static void main(String[] args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class);
		//////////////////////////////////////////////////////////////////////////////////////
		
		SingerService singerService = (SingerService) ctx.getBean("singerService");

		List<Singer> singers = singerService.findAll();
		singers.forEach(System.out::println);
		
		//////////////////////////////////////////////////////////////////
		
		ctx.close();
	}
	
	
}
