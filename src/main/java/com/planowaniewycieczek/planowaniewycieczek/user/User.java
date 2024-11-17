package com.planowaniewycieczek.planowaniewycieczek.user;

public class User {
    private int id;
    private String name;


//    public User( int id, String name) {
//        this.name = name;
//        this.id = id;
//    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

@Override
public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
}
}
