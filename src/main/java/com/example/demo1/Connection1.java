package com.example.demo1;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection1 {
   // Connection
    public static void main(String[] args) throws SQLException {
        try {
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agiotage2", "root", "");
            Statement statement = connection.createStatement();
//            statement.executeUpdate("INSERT INTO user (FirstName,LastName,Password,Username,Email,PhoneNumber)VALUES(0,11,22,33,44,55)");
//            statement.executeUpdate("INSERT INTO user (FirstName,LastName,Password,Username,Email,PhoneNumber)VALUES(66,111,222,333,444,555)");
            statement.executeUpdate("UPDATE user SET FirstName = 40333 WHERE (FirstName = 0 AND LastName = 11 ) OR (Email=444)" );
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("FirstName"));
//                System.out.println(resultSet.getString("LastName"));
//                System.out.println(resultSet.getString("Password"));
//                System.out.println(resultSet.getString("Username"));
//                System.out.println(resultSet.getString("Email"));
//                System.out.println(resultSet.getString("PhoneNumber"));
//
//
//            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
