package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Url myUrl  = new Url("www.facebook.com", "");
		myUrl.convertToBase62();
		String longUrl = "Enter a long url cowboy";
		myUrl.convertLongUrlToTiny(longUrl);
		SpringApplication.run(DemoApplication.class, args);
	}
}
