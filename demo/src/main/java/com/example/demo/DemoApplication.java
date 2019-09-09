package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Url myUrl  = new Url("www.facebook.com", "");
		myUrl.convertToBase36();
		String longUrl = "Enter a long url cowboy";
		myUrl.convertLongUrlToTiny(longUrl);
		SpringApplication.run(DemoApplication.class, args);

		/*EntityManager em = null;
        em.getTransaction().begin();
        em.persist(myUrl);
        em.getTransaction().commit();*/
	}
}
