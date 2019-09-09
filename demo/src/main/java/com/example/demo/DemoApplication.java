package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Url myUrl  = new Url("www.facebook.com", "");
		myUrl.convertToBase36();
		String longUrl = "Enter a long url cowboy";
		myUrl.convertLongUrlToTiny(longUrl);
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("HA");
		saveUrl();
		System.out.println("HA");
	}

	public static void saveUrl() {
		EntityStuff es = new EntityStuff();
		EntityManager em = es.getEntityManager();
		em.getTransaction().begin();
		Url myUrl  = new Url("www.facebook.com", "");
		em.persist(myUrl);
		em.getTransaction().commit();
	}
}
