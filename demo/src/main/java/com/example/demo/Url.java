package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String ID;
    String TINYURL;
    String LONGURL;

    public Url(){
        this.TINYURL = "tinyUrl";
        this.LONGURL = "longUrl";
    }
    public Url(String tinyUrl, String longUrl){
        this.TINYURL = tinyUrl;
        this.LONGURL = longUrl;
    }

    public String convertToBase62(){
        List<String> base62List = new ArrayList<String>();
        int index;
        for (index = 0; index < 10; index ++){
            base62List.add(Integer.toString(index));
        }
        String alphabetLowercase = "abcdefghijklmnopqrstuvwxyz";
        addAlphabetToBase62List(alphabetLowercase, base62List);
        String alphabetUppercase = alphabetLowercase.toUpperCase();
        addAlphabetToBase62List(alphabetUppercase, base62List);
        return "";
    }

    public List<String> addAlphabetToBase62List(String alphabet, List<String> base62List){
        String [] alphabetSplit = alphabet.split("");
        for (int index = 0; index < alphabetSplit.length; index ++){
            base62List.add(alphabetSplit[index]);
        }
        return base62List;
    }

    public String convertLongUrlToTiny(String longUrl){
        String tinyUrlHeader = "www.raysurl.com/";
        int lastDatabaseIndex = 0;
        String urlBody = "";
        String tinyUrl = tinyUrlHeader;
        System.out.println("Tiny url is " + tinyUrl);
        return tinyUrl;
    }
}
