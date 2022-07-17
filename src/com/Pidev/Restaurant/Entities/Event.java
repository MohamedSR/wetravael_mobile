package com.Pidev.Restaurant.Entities;

import java.util.Date;

public class Event {


    private int id;

    public Event() {
    }

    public Event(int id, String name, int capacity, java.sql.Date date, String adresse, String ville, String pays) {
        this.id=id;
        this.name=name;
        this.capacity=capacity;
        this.date=date;
        this.adresse=adresse;
        this.ville=ville;
        this.pays=pays;
    }


    public Event(int capacity) {
        this.capacity=capacity;
    }

    public Event(int capacity, String text, String text1, String text2, String text3, java.sql.Date date) {
        this.capacity=capacity;
        this.name=text;
        this.adresse=text1;
        this.ville=text2;
        this.pays=text3;
        this.date=date;
    }
    public Event(int capacity, String text, String text1, String text2, String text3, java.sql.Date date, String path) {
        this.capacity=capacity;
        this.name=text;
        this.adresse=text1;
        this.ville=text2;
        this.pays=text3;
        this.date=date;
        this.image=path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private String name, adresse, ville, pays,image;
    private Date date;
    private int capacity;

    @Override
    public String toString() {
        return "vous etes les bienvenue à notre Event Numero" +
                "id=" + id +
                ", qu'on l'appele " + name +
                ",visité nous à l'adresse " + adresse +
                ",à " + ville +
                ",dans notre cher pays " + pays +
                ",c'est le " + date +
                ", on a une capacity de " + capacity +
                "personnes";
    }

    public Event(String name, String adresse, int capacity, String ville, String pays, Date date) {
        this.name = name;
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
        this.date = date;
        this.capacity = capacity;
    }
    public Event(String name, String adresse, int capacity, String ville, String pays, Date date,String image) {
        this.name = name;
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
        this.date = date;
        this.capacity = capacity;
        this.image = image;
    }
    public Event(int id, String name, String adresse, String ville, String pays, Date date, int capacity) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
        this.date = date;
        this.capacity = capacity;
    }
    public Event(int id, String name, String adresse, String ville, String pays, Date date, int capacity, String image) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
        this.date = date;
        this.capacity = capacity;
        this.image = image;
    }

    public String toJSON() {
        return "{\"name\":\""+this.name+"\",\"capacity\":"+this.capacity+",\"adresse\":\""+this.adresse+"\",\"ville\":\""+this.ville+"\",\"pays\":\""+this.pays+"\",\"date\":\""+this.date+"\",\"image\":\""+this.image+"\"}";

    }
}
