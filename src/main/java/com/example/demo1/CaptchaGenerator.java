package com.example.demo1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CaptchaGenerator {
    public static void captcha(String captchaString) throws IOException {
        int width = 90;
        int height = 40;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.pink);
        graphics2D.fillRect(0,0,width,height);
        Font font = new Font("Arial",Font.BOLD,20);
        graphics2D.setColor(Color.darkGray);
        graphics2D.setFont(font);
        graphics2D.drawString(captchaString,10,25);
        graphics2D.dispose();

        ImageIO.write(bufferedImage,"jpg",new File("C://Users//mania//Desktop//New folder (3)//Agiotage//src//main//resources//com//example//demo1//d3e2686ead31b9f31970f8466f5a1ae0.jpg"));
    }

    public static String GenerateCaptcha(){
        Random rand = new Random();
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        for(int i=0;i<length;i++){
            captcha.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return captcha.toString();
    }
}
