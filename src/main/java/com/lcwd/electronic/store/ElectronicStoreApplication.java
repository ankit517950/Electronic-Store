package com.lcwd.electronic.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
//@ComponentScan("com.lcwd.electronic.store.services")
public class ElectronicStoreApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ElectronicStoreApplication.class, args);
	}
}
