package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "url")
public class Url {
    private int uid;

    private String long_url;
    private String short_url;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    @Column(name = "uid", unique = true)
    public int getUid(){
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Column(name = "short_url")
    public String getshort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    @NotNull
    @Column(name = "long_url")
    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public Url(){
    }

    public Url(String long_url, String tinyUrl){
        this.short_url = tinyUrl;
        this.long_url = long_url;
    }

    public Url(String long_url){
        this.long_url = long_url;
        this.short_url = "";
    }

    public List<String> createBase36(){
        List<String> base36List = new ArrayList<String>();
        int index;
        for (index = 0; index < 10; index ++){
            base36List.add(Integer.toString(index));
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String [] alphabetSplit = alphabet.split("");
        for (index = 0; index < alphabetSplit.length; index ++){
            base36List.add(alphabetSplit[index]);
        }        return base36List;
    }

    public String convertLongUrlToTiny(String longUrl) {
        String tinyUrlHeader = "www.raysurl.com/";
        int lastDatabaseIndex = 0;
        String urlBody = "";
        String tinyUrl = tinyUrlHeader;
        System.out.println("Tiny url is " + tinyUrl);
        return tinyUrl;
    }
}
