package com.example.demo1;

public class User {
    ///پروفایل/کیف پول/تاریخچه
    private String username;
    private String password;
    private String fullName;
    private String Name;
    private String LastName;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    public  Wallet userWallet;
    public  History userhistory[]=new History[1000];

    public  int num;////ta 1000 user
    public  int transNum;

    public User( String Name, String LastName, String email, String phoneNumber) {
    //    this.username = username;
    //    this.password = password;
        this.Name=Name;
        this.LastName=LastName;
        this.fullName = Name+LastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    //    this.profilePicture = profilePicture;
        this.userWallet.balance = 0.0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


    public void setUserWallet(Wallet userWallet) {
        this.userWallet = userWallet;
    }

    public Wallet getUserWallet() {
        return this.userWallet;
    }
}

