package com.example.demo1;

public class Demo extends  User {
    public Wallet demowallet;
//    public Demo(String username, String password, String fullName, String email, String phoneNumber, String profilePicture) {
//        super( username,  password,  fullName,  email,  phoneNumber,  profilePicture);
//        demowallet.balance=500;
//    }
public Demo(String Name, String LastName, String email, String phoneNumber,String user) {
    super( Name,  LastName,  email,  phoneNumber,user);
    demowallet.balance=500;
}
//**********************************************************************************
//    اطالعات این حالت به حالت اصلی )حالت موجود قبل از سوییچ کردن کاربر به حالت دمو( فرستاده نمیشوند، اما اطالعات تراکنش ها
//            و درخواست های حالت اصلی به عنوان داده در حالت دمو قابل استفاده است.


}
