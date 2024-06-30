package com.example.demo1;

public  class Admin extends User{
    ////just one admin********************************************************************************8
    static private int sw;
    ///bastan va baz
    public static Wallet wallet;
    private static Admin admin = new Admin("Admin","Admini","Admin@gmail.com","09999999999","1111111111");
    public static User getAdmin(){
        return admin;
    }

//    public Admin(String username, String password, String fullName, String email, String phoneNumber, String profilePicture) {
//        super( username,  password,  fullName,  email,  phoneNumber,  profilePicture);
//    }
private Admin(String Name, String LastName, String email, String phoneNumber,String user) {
    super( Name,  LastName,  email,  phoneNumber,user);
}

    ///ekhtelas
}
