package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

public class HomePage {
    public int[] sort ={0,1,2,3,4};

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

    @FXML
    private Button btnavalanche;

    @FXML
    private Button btnconversion;

    @FXML
    private Button btncurrency;

    @FXML
    private Button btnday;

    @FXML
    private Button btnlightcoin;

    @FXML
    private Button btnmax;

    @FXML
    private Button btnmin;

    @FXML
    private Button btnprice;

    @FXML
    private Button btnprofile;

    @FXML
    private Button btnrippel;

    @FXML
    private Button btnstellar;

    @FXML
    private Text txt11;

    @FXML
    private Text txt12;

    @FXML
    private Text txt13;

    @FXML
    private Text txt14;

    @FXML
    private Text txt21;

    @FXML
    private Text txt22;

    @FXML
    private Text txt23;

    @FXML
    private Text txt24;

    @FXML
    private Text txt31;

    @FXML
    private Text txt32;

    @FXML
    private Text txt33;

    @FXML
    private Text txt34;

    @FXML
    private Text txt41;

    @FXML
    private Text txt42;

    @FXML
    private Text txt43;

    @FXML
    private Text txt44;

    @FXML
    private Text txt51;

    @FXML
    private Text txt52;

    @FXML
    private Text txt53;

    @FXML
    private Text txt54;

    @FXML
    void Pbtnavalanche(ActionEvent event) throws IOException {
        LineChartExample.typeCoin=2;
        Stage stage = (Stage) btnrippel.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("AvalanchPage.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void Pbtnday(ActionEvent event) throws IOException {
        LineChartExample.typeCoin=4;
        Stage stage = (Stage) btnrippel.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("DayPage.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void Pbtnlightcoin(ActionEvent event) throws IOException {
        LineChartExample.typeCoin=3;
        Stage stage = (Stage) btnrippel.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("LightCoinPage.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void Pbtnrippel(ActionEvent event) throws IOException {
        LineChartExample.typeCoin=1;
        Stage stage = (Stage) btnrippel.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("RippelPage.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void Pbtnstellar(ActionEvent event) throws IOException {
        LineChartExample.typeCoin=5;
        Stage stage = (Stage) btnrippel.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("StellarPage.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void Pbtncurrency(ActionEvent event) {
        CalendarTime calendarTime = new CalendarTime();
        long milicec = calendarTime.now() - 24*60*60*1000L;
        String agotime=calendarTime.formatTime35(milicec);
        String agodate=calendarTime.formatDate(milicec);
        String url = "jdbc:mysql://localhost:3306/agiotage2";
        String user = "root";
        String password = "";

        boolean sw24 = true;

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // همه ردیف‌هایی که ایمیل آن‌ها بزرگتر یا مساوی با ایمیل مورد نظر است را بخوانید
            String sql = "SELECT * FROM prices WHERE DATE >= ? ORDER BY DATE";

            try (PreparedStatement preparedStatementYOU = conn.prepareStatement(sql)) {
                preparedStatementYOU.setString(1, agodate);
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


        DecimalFormat df = new DecimalFormat("#.##");
//        double roundedNumber = Double.valueOf(df.format(number));

        txt11.setText(String.valueOf(Double.valueOf(df.format(usd))));
        txt12.setText(String.valueOf((Double.valueOf(df.format((usd-usd24)*100/usd)))));
        txt13.setText(String.valueOf(Double.valueOf(df.format(usdmax))));
        txt14.setText(String.valueOf(Double.valueOf(df.format(usdmin))));
        txt21.setText(String.valueOf(Double.valueOf(df.format(eur))));
        txt22.setText(String.valueOf(Double.valueOf(df.format((eur-eur24)*100/eur))));
        txt23.setText(String.valueOf(Double.valueOf(df.format(eurmax))));
        txt24.setText(String.valueOf(Double.valueOf(df.format(eurmin))));
        txt31.setText(String.valueOf(Double.valueOf(df.format(toman))));
        txt32.setText(String.valueOf(Double.valueOf(df.format((toman-toman24)*100/toman))));
        txt33.setText(String.valueOf(Double.valueOf(df.format(tomanmax))));
        txt34.setText(String.valueOf(Double.valueOf(df.format(tomanmin))));
        txt41.setText(String.valueOf(Double.valueOf(df.format(yen))));
        txt42.setText(String.valueOf(Double.valueOf(df.format((yen-yen24)*100/yen))));
        txt43.setText(String.valueOf(Double.valueOf(df.format(yenmax))));
        txt44.setText(String.valueOf(Double.valueOf(df.format(yenmin))));
        txt51.setText(String.valueOf(Double.valueOf(df.format(gbp))));
        txt52.setText(String.valueOf(Double.valueOf(df.format((gbp-gbp24)*100/gbp))));
        txt53.setText(String.valueOf(Double.valueOf(df.format(gbpmax))));
        txt54.setText(String.valueOf(Double.valueOf(df.format(gbpmin))));






    }

    @FXML
    void Pbtnconversion(ActionEvent event) {
        int[] sorted = sortDoublesDescending(((usd-usd24)*100/usd),((eur-eur24)*100/eur),((toman-toman24)*100/toman),((yen-yen24)*100/yen),(gbp-gbp24)*100/gbp);
        changeTartib(sorted);

    }




    @FXML
    void Pbtnmax(ActionEvent event) {

        int[] sorted = sortDoublesDescending(usdmax,eurmax,tomanmax,yenmax,gbpmax);
        changeTartib(sorted);

    }

    @FXML
    void Pbtnmin(ActionEvent event) {

        int[] sorted = sortDoublesDescending(usdmin,eurmin,tomanmin,yenmin,gbpmin);
        changeTartib(sorted);


    }

    @FXML
    void Pbtnprice(ActionEvent event) {
        int[] sorted = sortDoublesDescending(usd,eur,toman,yen,gbp);
        changeTartib(sorted);

    }

    @FXML
    void Pbtnprofile(ActionEvent event) throws IOException {
        if(Profile.thisUsername.equals("1111111111")){
            Stage stage = (Stage) btnprofile.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Scene scene = new Scene(root, 794, 637);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else {
        Stage stage = (Stage) btnprofile.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene scene = new Scene(root, 794, 637);
        primaryStage.setScene(scene);
        primaryStage.show();}
    }

    static class ValueWithIndex {
        double value;
        int index;

        ValueWithIndex(double value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static int[] sortDoublesDescending(double a, double b, double c, double d, double e) {
        // ایجاد آرایه‌ای از اشیاء ValueWithIndex
        ValueWithIndex[] array = {
                new ValueWithIndex(a, 0),
                new ValueWithIndex(b, 1),
                new ValueWithIndex(c, 2),
                new ValueWithIndex(d, 3),
                new ValueWithIndex(e, 4)
        };

        // مرتب‌سازی آرایه به ترتیب نزولی بر اساس مقدار
        Arrays.sort(array, new Comparator<ValueWithIndex>() {
            @Override
            public int compare(ValueWithIndex o1, ValueWithIndex o2) {
                return Double.compare(o2.value, o1.value); // مرتب‌سازی نزولی
            }
        });

        // استخراج اندیس‌ها از آرایه مرتب‌شده
        int[] indices = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = array[i].index;
        }

        // بازگرداندن آرایه اندیس‌ها
        return indices;
    }

    void changeTartib(int sorted[]){














        DecimalFormat df = new DecimalFormat("#.##");




        txt11.setText(String.valueOf(Double.valueOf(df.format(usd))));
        txt12.setText(String.valueOf((Double.valueOf(df.format((usd-usd24)*100/usd)))));
        txt13.setText(String.valueOf(Double.valueOf(df.format(usdmax))));
        txt14.setText(String.valueOf(Double.valueOf(df.format(usdmin))));
        txt21.setText(String.valueOf(Double.valueOf(df.format(eur))));
        txt22.setText(String.valueOf(Double.valueOf(df.format((eur-eur24)*100/eur))));
        txt23.setText(String.valueOf(Double.valueOf(df.format(eurmax))));
        txt24.setText(String.valueOf(Double.valueOf(df.format(eurmin))));
        txt31.setText(String.valueOf(Double.valueOf(df.format(toman))));
        txt32.setText(String.valueOf(Double.valueOf(df.format((toman-toman24)*100/toman))));
        txt33.setText(String.valueOf(Double.valueOf(df.format(tomanmax))));
        txt34.setText(String.valueOf(Double.valueOf(df.format(tomanmin))));
        txt41.setText(String.valueOf(Double.valueOf(df.format(yen))));
        txt42.setText(String.valueOf(Double.valueOf(df.format((yen-yen24)*100/yen))));
        txt43.setText(String.valueOf(Double.valueOf(df.format(yenmax))));
        txt44.setText(String.valueOf(Double.valueOf(df.format(yenmin))));
        txt51.setText(String.valueOf(Double.valueOf(df.format(gbp))));
        txt52.setText(String.valueOf(Double.valueOf(df.format((gbp-gbp24)*100/gbp))));
        txt53.setText(String.valueOf(Double.valueOf(df.format(gbpmax))));
        txt54.setText(String.valueOf(Double.valueOf(df.format(gbpmin))));





        if(sorted[0]==0){
            txt11.setText(String.valueOf(Double.valueOf(df.format(usd))));
            txt12.setText(String.valueOf((Double.valueOf(df.format((usd-usd24)*100/usd)))));
            txt13.setText(String.valueOf(Double.valueOf(df.format(usdmax))));
            txt14.setText(String.valueOf(Double.valueOf(df.format(usdmin))));
            btnrippel.setLayoutY(145);
        }else  if(sorted[1]==0){
            txt21.setText(String.valueOf(Double.valueOf(df.format(usd))));
            txt22.setText(String.valueOf((Double.valueOf(df.format((usd-usd24)*100/usd)))));
            txt23.setText(String.valueOf(Double.valueOf(df.format(usdmax))));
            txt24.setText(String.valueOf(Double.valueOf(df.format(usdmin))));
            btnrippel.setLayoutY(217);
        }else if(sorted[2]==0){
            txt31.setText(String.valueOf(Double.valueOf(df.format(usd))));
            txt32.setText(String.valueOf((Double.valueOf(df.format((usd-usd24)*100/usd)))));
            txt33.setText(String.valueOf(Double.valueOf(df.format(usdmax))));
            txt34.setText(String.valueOf(Double.valueOf(df.format(usdmin))));
            btnrippel.setLayoutY(283);
        }else if(sorted[3]==0){
            txt41.setText(String.valueOf(Double.valueOf(df.format(usd))));
            txt42.setText(String.valueOf((Double.valueOf(df.format((usd-usd24)*100/usd)))));
            txt43.setText(String.valueOf(Double.valueOf(df.format(usdmax))));
            txt44.setText(String.valueOf(Double.valueOf(df.format(usdmin))));
            btnrippel.setLayoutY(352);
        }else if(sorted[4]==0){
            txt51.setText(String.valueOf(Double.valueOf(df.format(usd))));
            txt52.setText(String.valueOf((Double.valueOf(df.format((usd-usd24)*100/usd)))));
            txt53.setText(String.valueOf(Double.valueOf(df.format(usdmax))));
            txt54.setText(String.valueOf(Double.valueOf(df.format(usdmin))));
            btnrippel.setLayoutY(423);
        }


        if(sorted[0]==1){
            txt11.setText(String.valueOf(Double.valueOf(df.format(eur))));
            txt12.setText(String.valueOf(Double.valueOf(df.format((eur-eur24)*100/eur))));
            txt13.setText(String.valueOf(Double.valueOf(df.format(eurmax))));
            txt14.setText(String.valueOf(Double.valueOf(df.format(eurmin))));
            btnavalanche.setLayoutY(145);
        }else  if(sorted[1]==1){
            txt21.setText(String.valueOf(Double.valueOf(df.format(eur))));
            txt22.setText(String.valueOf(Double.valueOf(df.format((eur-eur24)*100/eur))));
            txt23.setText(String.valueOf(Double.valueOf(df.format(eurmax))));
            txt24.setText(String.valueOf(Double.valueOf(df.format(eurmin))));
            btnavalanche.setLayoutY(217);
        }else if(sorted[2]==1){
            txt31.setText(String.valueOf(Double.valueOf(df.format(eur))));
            txt32.setText(String.valueOf(Double.valueOf(df.format((eur-eur24)*100/eur))));
            txt33.setText(String.valueOf(Double.valueOf(df.format(eurmax))));
            txt34.setText(String.valueOf(Double.valueOf(df.format(eurmin))));
            btnavalanche.setLayoutY(283);
        }else if(sorted[3]==1){
            txt41.setText(String.valueOf(Double.valueOf(df.format(eur))));
            txt42.setText(String.valueOf(Double.valueOf(df.format((eur-eur24)*100/eur))));
            txt43.setText(String.valueOf(Double.valueOf(df.format(eurmax))));
            txt44.setText(String.valueOf(Double.valueOf(df.format(eurmin))));
            btnavalanche.setLayoutY(352);

        }else if(sorted[4]==1){
            txt51.setText(String.valueOf(Double.valueOf(df.format(eur))));
            txt52.setText(String.valueOf(Double.valueOf(df.format((eur-eur24)*100/eur))));
            txt53.setText(String.valueOf(Double.valueOf(df.format(eurmax))));
            txt54.setText(String.valueOf(Double.valueOf(df.format(eurmin))));
            btnavalanche.setLayoutY(423);

        }


        if(sorted[0]==2){
            txt11.setText(String.valueOf(Double.valueOf(df.format(toman))));
            txt12.setText(String.valueOf(Double.valueOf(df.format((toman-toman24)*100/toman))));
            txt13.setText(String.valueOf(Double.valueOf(df.format(tomanmax))));
            txt14.setText(String.valueOf(Double.valueOf(df.format(tomanmin))));
            btnlightcoin.setLayoutY(145);
        }else  if(sorted[1]==2){
            txt21.setText(String.valueOf(Double.valueOf(df.format(toman))));
            txt22.setText(String.valueOf(Double.valueOf(df.format((toman-toman24)*100/toman))));
            txt23.setText(String.valueOf(Double.valueOf(df.format(tomanmax))));
            txt24.setText(String.valueOf(Double.valueOf(df.format(tomanmin))));
            btnlightcoin.setLayoutY(217);
        }else if(sorted[2]==2){
            txt31.setText(String.valueOf(Double.valueOf(df.format(toman))));
            txt32.setText(String.valueOf(Double.valueOf(df.format((toman-toman24)*100/toman))));
            txt33.setText(String.valueOf(Double.valueOf(df.format(tomanmax))));
            txt34.setText(String.valueOf(Double.valueOf(df.format(tomanmin))));
            btnlightcoin.setLayoutY(283);
        }else if(sorted[3]==2){
            txt41.setText(String.valueOf(Double.valueOf(df.format(toman))));
            txt42.setText(String.valueOf(Double.valueOf(df.format((toman-toman24)*100/toman))));
            txt43.setText(String.valueOf(Double.valueOf(df.format(tomanmax))));
            txt44.setText(String.valueOf(Double.valueOf(df.format(tomanmin))));
            btnlightcoin.setLayoutY(352);
        }else if(sorted[4]==2){
            txt51.setText(String.valueOf(Double.valueOf(df.format(toman))));
            txt52.setText(String.valueOf(Double.valueOf(df.format((toman-toman24)*100/toman))));
            txt53.setText(String.valueOf(Double.valueOf(df.format(tomanmax))));
            txt54.setText(String.valueOf(Double.valueOf(df.format(tomanmin))));
            btnlightcoin.setLayoutY(423);
        }

        if(sorted[0]==3){
            txt11.setText(String.valueOf(Double.valueOf(df.format(yen))));
            txt12.setText(String.valueOf(Double.valueOf(df.format((yen-yen24)*100/yen))));
            txt13.setText(String.valueOf(Double.valueOf(df.format(yenmax))));
            txt14.setText(String.valueOf(Double.valueOf(df.format(yenmin))));
            btnday.setLayoutY(145);
        }else  if(sorted[1]==3){
            txt21.setText(String.valueOf(Double.valueOf(df.format(yen))));
            txt22.setText(String.valueOf(Double.valueOf(df.format((yen-yen24)*100/yen))));
            txt23.setText(String.valueOf(Double.valueOf(df.format(yenmax))));
            txt24.setText(String.valueOf(Double.valueOf(df.format(yenmin))));
            btnday.setLayoutY(217);
        }else if(sorted[2]==3){
            txt31.setText(String.valueOf(Double.valueOf(df.format(yen))));
            txt32.setText(String.valueOf(Double.valueOf(df.format((yen-yen24)*100/yen))));
            txt33.setText(String.valueOf(Double.valueOf(df.format(yenmax))));
            txt34.setText(String.valueOf(Double.valueOf(df.format(yenmin))));
            btnday.setLayoutY(283);
        }else if(sorted[3]==3){
            txt41.setText(String.valueOf(Double.valueOf(df.format(yen))));
            txt42.setText(String.valueOf(Double.valueOf(df.format((yen-yen24)*100/yen))));
            txt43.setText(String.valueOf(Double.valueOf(df.format(yenmax))));
            txt44.setText(String.valueOf(Double.valueOf(df.format(yenmin))));
            btnday.setLayoutY(352);
        }else if(sorted[4]==3){
            txt51.setText(String.valueOf(Double.valueOf(df.format(yen))));
            txt52.setText(String.valueOf(Double.valueOf(df.format((yen-yen24)*100/yen))));
            txt53.setText(String.valueOf(Double.valueOf(df.format(yenmax))));
            txt54.setText(String.valueOf(Double.valueOf(df.format(yenmin))));
            btnday.setLayoutY(423);
        }


        if(sorted[0]==4){
            txt11.setText(String.valueOf(Double.valueOf(df.format(gbp))));
            txt12.setText(String.valueOf(Double.valueOf(df.format((gbp-gbp24)*100/gbp))));
            txt13.setText(String.valueOf(Double.valueOf(df.format(gbpmax))));
            txt14.setText(String.valueOf(Double.valueOf(df.format(gbpmin))));
            btnstellar.setLayoutY(145);

        }else  if(sorted[1]==4){
            txt21.setText(String.valueOf(Double.valueOf(df.format(gbp))));
            txt22.setText(String.valueOf(Double.valueOf(df.format((gbp-gbp24)*100/gbp))));
            txt23.setText(String.valueOf(Double.valueOf(df.format(gbpmax))));
            txt24.setText(String.valueOf(Double.valueOf(df.format(gbpmin))));
            btnstellar.setLayoutY(217);
        }else if(sorted[2]==4){
            txt31.setText(String.valueOf(Double.valueOf(df.format(gbp))));
            txt32.setText(String.valueOf(Double.valueOf(df.format((gbp-gbp24)*100/gbp))));
            txt33.setText(String.valueOf(Double.valueOf(df.format(gbpmax))));
            txt34.setText(String.valueOf(Double.valueOf(df.format(gbpmin))));
            btnstellar.setLayoutY(283);
        }else if(sorted[3]==4){
            txt41.setText(String.valueOf(Double.valueOf(df.format(gbp))));
            txt42.setText(String.valueOf(Double.valueOf(df.format((gbp-gbp24)*100/gbp))));
            txt43.setText(String.valueOf(Double.valueOf(df.format(gbpmax))));
            txt44.setText(String.valueOf(Double.valueOf(df.format(gbpmin))));
            btnstellar.setLayoutY(352);
        }else if(sorted[4]==4){
            txt51.setText(String.valueOf(Double.valueOf(df.format(gbp))));
            txt52.setText(String.valueOf(Double.valueOf(df.format((gbp-gbp24)*100/gbp))));
            txt53.setText(String.valueOf(Double.valueOf(df.format(gbpmax))));
            txt54.setText(String.valueOf(Double.valueOf(df.format(gbpmin))));
            btnstellar.setLayoutY(423);
        }




    }
}
