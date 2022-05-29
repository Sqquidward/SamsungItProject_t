package com.example.samsungitproject.Authorization;

public class User {
    public String name;
    public String lastname;
    public String email;
    public String password;
    public String currency;
    public String interval;
    public String course_1;
    public String course_2;
    public String course_3;

    public User() {
    }

    public User(String name, String lastname, String email, String password, String currency, String interval, String course_1, String course_2, String course_3) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.currency = currency;
        this.interval = interval;
        this.course_1 = course_1;
        this.course_2 = course_2;
        this.course_3 = course_3;
    }
}
