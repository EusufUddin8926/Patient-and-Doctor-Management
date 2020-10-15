package com.example.hackathonviewpagerlogin;

public class ModelDoctor {
    private String name;
    private String designation;
    private String phone;
    private String email;
    private int position;

    public ModelDoctor(String name, String designation, String phone, String email, int position) {
        this.name = name;
        this.designation = designation;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

}

