package com.example.demo1;

import java.util.Random;
import java.util.Scanner;

public class Wallet {

    public double balance;
    String walletId;
    //    تمام ارز های فرد به همراه قیمت و مقدار
    TokenUser allToken[]={new TokenUser("0",0),new TokenUser("1",0),new TokenUser("2",0),new TokenUser("3",0),new TokenUser("4",0),new TokenUser("5",0)};
    TokenMain allTokenMain[]={new TokenMain("0"),new TokenMain("1"),new TokenMain("2"),new TokenMain("3"),new TokenMain("4"),new TokenMain("5")};



    ///****************************************************نمودار ساالنه دارایی///////////////////////////////

// مقدار دارایی فعلی فرد به ارز پایه

    //public user findUserByID(String ID){
//    return new user();
//    ///////
//}*******************************************************************************************
/////Num====>token =0,1,2,3,4,5,vajh=10/11
    public  void transfer(User user,int Num ,double amount){
        if (user instanceof Demo) {
            System.out.println("You cannot withdraw or deposit money from the account because you are in demo mode");
        }
        else if (user instanceof Admin) {
            System.out.println("You cannot withdraw or deposit money from the account because you are admin");
        }
        else {
            if (Num == 10) {
                deposit(user, amount);
            } else if (Num == 11) {
                withdraw(user, amount);
            } else {
                user.userWallet.allToken[Num].number += amount;
                user.userWallet.allToken[Num].price+=amount*allTokenMain[Num].priceNow;
                user.userWallet.balance+=amount*allTokenMain[Num].priceNow;
//            deposit(user,amount);
            }
        }


    }

    //  Deposit
    public void deposit(User user, double amount) {


        System.out.println("\nPlease Enter The Depositing Amount! ");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - ");

        System.out.print("\nDEPOSIT... ");

        user.userWallet.balance+=amount;


        // Displaying the Amount
        System.out.print("\n\nAmount	:" + amount);

        System.out.println("\nBalance	:" + balance + "\n");

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - ");
        System.out.println("Amount deposited successfully!\n");

    }
    // Withdraw
    public void withdraw(User user,double amount) {



        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - ");

        if(balance>amount)
        {
            balance -= amount;

            System.out.println("\nBalance	:"+ balance+"\n");

            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - ");
            System.out.println("Amount withdrew successfully!\n");
        }

        else
        {
            System.out.println("\n\nSorry, Insufficient Funds to proceed!");
        }
    }


}
