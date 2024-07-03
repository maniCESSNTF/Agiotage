package com.example.demo1;

public class User {
    ///پروفایل/کیف پول/تاریخچه
    int demo=0;
    public String username;
    private String password;
    private String fullName;
    private String Name;
    private String LastName;
    private String email;
    private String phoneNumber;
    private String WalletId;
    private String profilePicture;
    public  Wallet userWallet = new Wallet();
    public  History userhistory[]=new History[1000];

    public  int num;////ta 1000 user
    public  int transNum;

    public User( String Name, String LastName, String email, String phoneNumber,String username,int demo) {
        //    this.username = username;
        //    this.password = password;
        this.demo=demo;
        this.Name=Name;
        this.LastName=LastName;
        this.fullName = Name+LastName;
        this.email = email;
        this.username=username;
        this.phoneNumber = phoneNumber;
        //    this.profilePicture = profilePicture;
        this.userWallet.balance = 0.0;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() <= 18 && lastName.matches("[a-zA-Z]+")) {
            this.LastName = lastName;
        } else throw new InvalidIDException("PLEASE ENTER A FAMILYNAME WITH MAXIMUM 18 LETTERS");
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        if (name.length() <= 18 && name.matches("[a-zA-Z]+")) {
            this.Name = name;
        } else throw new InvalidIDException("PLEASE ENTER A FAMILYNAME WITH MAXIMUM 18 LETTERS");
    }

    public String getWalletId() {
        return WalletId;
    }

    public void setWalletId(String walletId) {
        WalletId = walletId;
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
        if((email.matches("[a-zA-Z0-9.]{1,18}@[a-z.-]{1,8}\\.[a-z]{1,4}"))){
            this.email = email;
        }
        else throw new InvalidIDException("PLEASE ENTER A EMAIL WITH CORRECT FORMAT ---> ....@gmail.com & MUST BE ODD");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if((phoneNumber.length()==11&&phoneNumber.matches("[0-9]+")&&phoneNumber.startsWith("09"))) {
            this.phoneNumber=phoneNumber;}
        else throw new InvalidIDException("wrong format .enter a new phone number");
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

