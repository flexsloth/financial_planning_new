package com.example.myapplication;

public class register_data {
    String Name;
    String Email;
    String Password;
    String Phoneno;
    String Age;
    String id;
    String Occupation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public register_data()
    {}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public register_data(String name, String email, String password, String phoneno, String age, String occupation,String ID) {
        Name = name;
        Email = email;
        Password = password;
        Phoneno = phoneno;
        Age = age;
        Occupation = occupation;
        id=ID;
    }
}
