package com.example.demo1;

import java.sql.*;

public class read {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/agiotage2";
        String user = "root";
        String password = "";

        boolean sw24 = true;

        double usd24=0;
        double gbp24=0;
        double toman24=0;
        double yen24=0;
        double eur24=0;

        double usd=0;
        double gbp=0;
        double toman=0;
        double yen=0;
        double eur=0;

        double usdmax=-1000000000;
        double gbpmax=-1000000000;
        double tomanmax=-1000000000;
        double yenmax=-1000000000;
        double eurmax=-1000000000;

        double usdmin=1000000000;
        double gbpmin=1000000000;
        double tomanmin=1000000000;
        double yenmin=1000000000;
        double eurmin=1000000000;

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String initialEmail = "2024-06-12";

            // همه ردیف‌هایی که ایمیل آن‌ها بزرگتر یا مساوی با ایمیل مورد نظر است را بخوانید
            String sql = "SELECT * FROM prices WHERE DATE >= ? ORDER BY DATE";

            try (PreparedStatement preparedStatementYOU = conn.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, initialEmail);
                ResultSet rs = preparedStatementYOU.executeQuery();

                while (rs.next()) {

                    if(sw24){
                     usd24=rs.getDouble("USD");
                     gbp24=rs.getDouble("GBP");
                     toman24=rs.getDouble("TOMAN");
                     yen24=rs.getDouble("YEN");
                     eur24=rs.getDouble("EUR");
                    sw24=false;
                    }

                     usd=rs.getDouble("USD");
                     gbp=rs.getDouble("GBP");
                     toman=rs.getDouble("TOMAN");
                     yen=rs.getDouble("YEN");
                     eur=rs.getDouble("EUR");

                     if(usd>usdmax)usdmax=usd;
                    if(gbp>gbpmax)gbpmax=gbp;
                    if(toman>tomanmax)tomanmax=toman;
                    if(yen>yenmax)yenmax=yen;
                    if(eur>eurmax)eurmax=eur;

                    if(usd<usdmin)usdmin=usd;
                    if(gbp<gbpmin)gbpmin=gbp;
                    if(toman<tomanmin)tomanmin=toman;
                    if(yen<yenmin)yenmin=yen;
                    if(eur<eurmin)eurmin=eur;
                }
                System.out.println("USD: " + usdmin+"//////////////////" +usdmax);
                System.out.println("GBP: " + gbpmin+"//////////////////" +gbpmax);
                System.out.println("yen: " + yenmin+"//////////////////"+ yenmax);
                System.out.println("toman: " + tomanmin+"//////////////////"+ tomanmin);
                System.out.println("eur: " + eurmin+"//////////////////" +eurmax);


                rs.close();
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
