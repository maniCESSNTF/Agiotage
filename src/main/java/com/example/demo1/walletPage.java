//package com.example.demo1;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TextField;
//
//public class PleaseProvideControllerClassName {
//
//
//    boolean accountToWallet,walletToAccount=false;
//
//    public  void transfer(User user,int Num ,double amount){
//        if (user instanceof Demo) {
////            System.out.println("You cannot withdraw or deposit money from the account because you are in demo mode");
//        }
//        else if (user instanceof Admin) {
////            System.out.println("You cannot withdraw or deposit money from the account because you are admin");
//        }
//        else {
//            if (Num == 10) {
//                deposit(user, amount);
//            } else if (Num == 11) {
//                withdraw(user, amount);
//            } else {
//                user.userWallet.allToken[Num].number += amount;
//                user.userWallet.allToken[Num].price+=amount*allTokenMain[Num].priceNow;
//                user.userWallet.balance+=amount*allTokenMain[Num].priceNow;
////            deposit(user,amount);
//            }
//        }
//
//
//    }
//
//    //  Deposit
//    public void deposit(User user, double amount) {
//
//        user.userWallet.balance+=amount;
//
//    }
//    // Withdraw
//    public void withdraw(User user,double amount) {
//
//        if(balance>amount)
//        {
//            balance -= amount;
//        }
//
//        else{
//            ///////
//        }
//    }
//
//    @FXML
//    void RbtnAccountToWallet(ActionEvent event) {
//        accountToWallet = true;
//        walletToAccount = false;
//    }
//
//    @FXML
//    void RbtnWalletToAccount(ActionEvent event) {
//        walletToAccount = true;
//        accountToWallet = false;
//    }
//
//    @FXML
//    private Button btnHome;
//
//    @FXML
//    void btnTransform(ActionEvent event) {
//        if (txtAmount.getText().isEmpty() || txtAccountNum.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Please enter amount and account number");
//            alert.showAndWait();
//        } else {
//            if (accountToWallet) {
//                transfer(User, 10, Double.parseDouble(txtAmount.getText()));
//                txtMsg.setText("Transfer account to wallet successful!");
//            } else if (walletToAccount) {
//                transfer(User, 11, Double.parseDouble(txtAmount.getText()));
//                txtMsg.setText("Transfer wallet to account successful!");
//            } else {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Warning");
//                alert.setHeaderText(null);
//                alert.setContentText("You should choose one option!");
//                alert.showAndWait();
//            }
//        }
//    }
//
//
//    @FXML
//    private TextField txtAccountNum;
//
//    @FXML
//    private TextField txtMsg;
//
//    @FXML
//    private TextField txtAmount;
//
//    @FXML
//    void PbtnHome(ActionEvent event) {
//
//    }
//
//}
