package com.example.demo1;

import java.sql.*;

class mmmm {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/agiotage2";
        String dbUser = "root";
        String dbPassword = "";
        String targetDate = "2024-06-15";
        String targetTime = "11:35:35";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

            String sql = "SELECT * FROM prices WHERE (DATE > ?) OR (DATE = ? AND TIMA >= ?) ORDER BY DATE, TIMA";

            try (PreparedStatement preparedStatementYOU = conn.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, targetDate);
                preparedStatementYOU.setString(2, targetDate);
                preparedStatementYOU.setString(3, targetTime);

                ResultSet rs1 = preparedStatementYOU.executeQuery();

                while (rs1.next()) {
                    System.out.println("DATE: " + rs1.getString("DATE") + ", TIMA: " + rs1.getString("TIMA"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
