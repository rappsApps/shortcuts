package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        //saveUrl();

        System.out.println("Inserting 100 unique web addresses into the DB and 1 duplicate");
        List<String> exampleUrls = new ArrayList<String>();
        for (int i = 1; i < 100; i++) {
            exampleUrls.add("www.website" + i + ".com");
        }
        exampleUrls.add("www.website2.com");
        exampleUrls.forEach(longurl -> {
            String shortUrl = "";
            if (checkUrlIsInDatabase(longurl)) {
                shortUrl = getShortUrlForLongUrl(longurl);
                System.out.println("Already in Database. Short url for " + longurl + " is " + shortUrl);
            } else {
                shortUrl = getNextShortUrlAvailable();
                addUrlToDatabase(longurl, shortUrl);
                if (checkUrlIsInDatabase(longurl)) {
                    System.out.println("After add to Database, Short url for " + longurl + " is " + shortUrl);
                }
            }
        });
    }

    private static boolean checkUrlIsInDatabase(String longUrl) {
        boolean urlIsInDatabase = false;
        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/urls?allowPublicKeyRetrieval=true&useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

            String query = "select short_url from url where long_url = '" + longUrl + "';";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            if (resultSet.next()) {
                urlIsInDatabase = true;
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception while checking DB!");
            System.err.println(e.getMessage());
        }
        return urlIsInDatabase;
    }

    private static String getNextShortUrlAvailable() {
        String lastShortUrl = "";
        String nextShortUrlAvailable = "www.raysurl.com/";
        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/urls?allowPublicKeyRetrieval=true&useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

            String query = "select short_url from url ORDER BY uid DESC LIMIT 1;";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            if (resultSet.next()) {
                lastShortUrl = resultSet.getString("short_url");
            } else System.out.println("WARN: Empty result returned in getNextShortUrl");
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception while checking DB!");
            System.err.println(e.getMessage());
        }
        if (lastShortUrl.isEmpty()) {
            nextShortUrlAvailable += "0";
        } else {
            String lastCharacterInLastShortUrl = lastShortUrl.substring(lastShortUrl.length() - 1);
            if (lastCharacterInLastShortUrl.equals("z")) {
                nextShortUrlAvailable = lastShortUrl.substring(0, lastShortUrl.length() - 1) + "00";
            } else if (lastCharacterInLastShortUrl.equals("9")) {
                nextShortUrlAvailable = lastShortUrl.substring(0, lastShortUrl.length() - 1) + "a";
            } else if (isNumeric(lastCharacterInLastShortUrl)) {
                int lastCharacterAsInt = Integer.parseInt(lastCharacterInLastShortUrl) + 1;
                nextShortUrlAvailable = lastShortUrl.substring(0, lastShortUrl.length() - 1) + lastCharacterAsInt;
            } else {
                String alphabet = "abcdefghijklmnopqrstuvwxyz";
                String[] alphabetSplit = alphabet.split("");
                if (alphabet.contains(lastCharacterInLastShortUrl)) {
                    int indexOfLastCharacter = alphabet.indexOf(lastCharacterInLastShortUrl);
                    nextShortUrlAvailable = lastShortUrl.substring(0, lastShortUrl.length() - 1) + alphabetSplit[indexOfLastCharacter + 1];
                }
            }
        }
        return nextShortUrlAvailable;
    }

    private static String getShortUrlForLongUrl(String longUrl) {
        String shortUrl = "";
        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/urls?allowPublicKeyRetrieval=true&useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

            String query = "select short_url from url where long_url = '" + longUrl + "';";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            if (resultSet.next()) {
                shortUrl = resultSet.getString("short_url");
            } else System.out.println("WARN: Empty result returned in getShortUrlForLongUrl");
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception while checking DB!");
            System.err.println(e.getMessage());
        }
        return shortUrl;
    }

    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static void saveUrl() {
        EntityStuff es = new EntityStuff();
        EntityManager em = es.getEntityManager();
        em.getTransaction().begin();
        Url myUrl = new Url("www.facebook.com", "");
        em.persist(myUrl);
        em.getTransaction().commit();
    }

    public static void addUrlToDatabase(String longUrl, String shortUrl) {
        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/urls?allowPublicKeyRetrieval=true&useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

            String query = " insert into url (long_url, short_url)"
                    + " values (?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, longUrl);
            preparedStmt.setString(2, shortUrl);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
