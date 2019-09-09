package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String shortUrl;
    @Column
    String longUrl;

    public Url(){
        this.shortUrl = "tinyUrl";
        this.longUrl = "longUrl";
    }

    public Url(String tinyUrl, String longUrl){
        this.shortUrl = tinyUrl;
        this.longUrl = longUrl;
    }

    public String convertToBase36(){
        List<String> base36List = new ArrayList<String>();
        int index;
        for (index = 0; index < 10; index ++){
            base36List.add(Integer.toString(index));
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String [] alphabetSplit = alphabet.split("");
        for (index = 0; index < alphabetSplit.length; index ++){
            base36List.add(alphabetSplit[index]);
        }        return "";
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
