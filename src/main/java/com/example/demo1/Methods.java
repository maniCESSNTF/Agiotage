package com.example.demo1;


import java.util.Date;
import java.util.Scanner;
public class Methods {

    double exchangeArray[][]=new double[4][];

    //        double History[][][]=new double[1000][4][];/////
    User exchangeArrayUser[]=new User[1000];


    ////list exchang(buy==0,sell==1)
    public void exchang(User userExchang,int tokenNum,int tokenName,int BuySell,double price){
        addExchangeList( userExchang, tokenNum, tokenName, BuySell, price);
        Date date=new Date();
        addToHistory(userExchang,tokenName,tokenNum,price,date,BuySell);
    }
    public  static  int num=0;
    public void addExchangeList(User userExchang,int tokenNum,int tokenName,int BuySell,double price){
        exchangeArray[0][num]=tokenName;
        exchangeArray[1][num]=tokenNum;
        exchangeArray[2][num]=BuySell;
        exchangeArray[3][num]=price;
        exchangeArrayUser[num]=userExchang;
        num++;

    }
    public double min(double a,double b){
        if(a<b){
            return  a;
        }
        else return b;
    }

    public int min(int a,int b){
        if(a<b){
            return  a;
        }
        else return b;
    }

    public void DoExchang() {
        for (int j = 0; j < num; j++) {
            for (int i = 0; i < num; i++) {
                if (exchangeArray[i][0]==exchangeArray[j][0]&&!exchangeArrayUser[i].equals(exchangeArrayUser[j])&&(exchangeArray[i][2]==0&&exchangeArray[j][2]==1)&&(exchangeArray[i][3]>=exchangeArray[j][3])){
                    double minAmount=min(exchangeArray[i][1],exchangeArray[j][1]);
                    if(exchangeArrayUser[i] instanceof Demo&&exchangeArrayUser[j] instanceof Demo){
                        exchangeArray[i][1] -= minAmount;
                        exchangeArray[j][1] -= minAmount;
                        exchangeArrayUser[i].userWallet.allToken[(int) exchangeArray[0][i]].number += (int) minAmount;
                        exchangeArrayUser[j].userWallet.allToken[(int) exchangeArray[0][i]].number -= (int) minAmount;
                        exchangeArrayUser[i].userWallet.balance -= minAmount * exchangeArray[i][3];
                        exchangeArrayUser[j].userWallet.balance += minAmount * exchangeArray[i][3];
                        exchangeArrayUser[i].userWallet.allToken[(int) exchangeArray[0][i]].price -=  minAmount*exchangeArray[i][3];
                        exchangeArrayUser[j].userWallet.allToken[(int) exchangeArray[0][i]].price += minAmount*exchangeArray[i][3];
                        Date date=new Date();

                        changStateOfHistory(exchangeArrayUser[i], (int)exchangeArray[i][0],(int) exchangeArray[i][1],exchangeArray[i][3], date,(int)exchangeArray[i][2],0);
                        changStateOfHistory(exchangeArrayUser[j], (int)exchangeArray[j][0],(int) exchangeArray[j][1],exchangeArray[j][3], date,(int)exchangeArray[j][2],1);
//                        addHistory(exchangeArrayUser[i], (int) minAmount, (int) exchangeArray[i][0], 0, exchangeArray[i][3]);
//                        addHistory(exchangeArrayUser[j], (int) minAmount, (int) exchangeArray[i][0], 1, exchangeArray[i][3]);
                        Admin.wallet.balance += (0.01 * minAmount * exchangeArray[i][3]);
                        exchangeArrayUser[i].userWallet.balance -= 0.005 * minAmount * exchangeArray[i][3];
                        exchangeArrayUser[j].userWallet.balance -= 0.005 * minAmount * exchangeArray[i][3];

                    }
                    else if(exchangeArrayUser[i] instanceof  Demo){
                        exchangeArray[i][1] -= minAmount;
                        Date date=new Date();

                        exchangeArrayUser[i].userWallet.allToken[(int) exchangeArray[0][i]].number += (int) minAmount;
                        exchangeArrayUser[i].userWallet.balance -= minAmount * exchangeArray[i][3];
                        changStateOfHistory(exchangeArrayUser[i], (int)exchangeArray[i][0],(int) exchangeArray[i][1],exchangeArray[i][3], date,(int)exchangeArray[i][2],0);
//                        addHistory(exchangeArrayUser[i], (int) minAmount, (int) exchangeArray[i][0], 0, exchangeArray[i][3]);
                        exchangeArrayUser[i].userWallet.allToken[(int) exchangeArray[0][i]].price -= minAmount*exchangeArray[i][3];
                        Admin.wallet.balance += (0.01 * minAmount * exchangeArray[i][3]);
                        exchangeArrayUser[i].userWallet.balance -= 0.005 * minAmount * exchangeArray[i][3];

                    }

                    else if(exchangeArrayUser[j] instanceof Demo){
                        exchangeArray[j][1] -= minAmount;
                        Date date=new Date();

                        exchangeArrayUser[j].userWallet.allToken[(int) exchangeArray[0][i]].number-= (int) minAmount;
                        exchangeArrayUser[j].userWallet.balance += minAmount * exchangeArray[i][3];
                        changStateOfHistory(exchangeArrayUser[j], (int)exchangeArray[j][0],(int) exchangeArray[j][1],exchangeArray[j][3], date,(int)exchangeArray[j][2],1);
                        exchangeArrayUser[j].userWallet.allToken[(int) exchangeArray[0][i]].price +=  minAmount*exchangeArray[i][3];
//                        addHistory(exchangeArrayUser[j], (int) minAmount, (int) exchangeArray[i][0], 1, exchangeArray[i][3]);
                        Admin.wallet.balance += (0.01 * minAmount * exchangeArray[i][3]);
                        exchangeArrayUser[j].userWallet.balance -= 0.005 * minAmount * exchangeArray[i][3];

                    }


                    else {
                        exchangeArray[i][1] -= minAmount;
                        exchangeArray[j][1] -= minAmount;
                        exchangeArrayUser[i].userWallet.allToken[(int) exchangeArray[0][i]].number += (int) minAmount;
                        exchangeArrayUser[j].userWallet.allToken[(int) exchangeArray[0][i]].number -= (int) minAmount;
                        exchangeArrayUser[i].userWallet.allToken[(int) exchangeArray[0][i]].price +=  minAmount*exchangeArray[i][3];
                        exchangeArrayUser[j].userWallet.allToken[(int) exchangeArray[0][i]].price -= minAmount*exchangeArray[i][3];
                        Date date=new Date();

                        changStateOfHistory(exchangeArrayUser[i], (int)exchangeArray[i][0],(int) exchangeArray[i][1],exchangeArray[i][3], date,(int)exchangeArray[i][2],0);
                        changStateOfHistory(exchangeArrayUser[j], (int)exchangeArray[j][0],(int) exchangeArray[j][1],exchangeArray[j][3], date,(int)exchangeArray[j][2],1);
                        exchangeArrayUser[i].userWallet.balance -= minAmount * exchangeArray[i][3];
                        exchangeArrayUser[j].userWallet.balance += minAmount * exchangeArray[i][3];
//                        addHistory(exchangeArrayUser[i], (int) minAmount, (int) exchangeArray[i][0], 0, exchangeArray[i][3]);
//                        addHistory(exchangeArrayUser[j], (int) minAmount, (int) exchangeArray[i][0], 1, exchangeArray[i][3]);
                        Admin.wallet.balance += (0.01 * minAmount * exchangeArray[i][3]);
                        exchangeArrayUser[i].userWallet.balance -= 0.005 * minAmount * exchangeArray[i][3];
                        exchangeArrayUser[j].userWallet.balance -= 0.005 * minAmount * exchangeArray[i][3];
                    }

                }
            }
        }
    }

//            public  void addHistory(user userExchang,int tokenNum,int tokenName,int BuySell,double price){
//                  ///  public  history(int typeToken,int amount,double price,Date date,int state){
//
//                    History[userExchang.num][userExchang.transNum][0]=tokenName;
//                History[userExchang.num][userExchang.transNum][1]=tokenNum;
//                History[userExchang.num][userExchang.transNum][2]=BuySell;
//                History[userExchang.num][userExchang.transNum][3]=price;
//
//                userExchang.transNum++;
//            }


    public  void addToHistory(User user, int typeToken, int amount, double price, Date date, int state){
        History newhistory=new  History(typeToken,  amount,  price,  date,  state);
        user.userhistory[user.transNum]=newhistory;
        user.transNum++;
    }

    public  void changStateOfHistory(User user, int typeToken, int amount, double price, Date date, int state,int newState){
        History newhistory=new  History(typeToken,  amount,  price,  date,  state);
        History newhistory1=new  History(typeToken,  amount,  price,  date,  newState);
        for(int i=0;i<1000;i++){
            if(user.userhistory[i].equals(newhistory)){
                user.userhistory[i]=newhistory1;

            }
        }
    }
}