package com.mentoring.epam.test.lesson9.readfromfile;

//import com.mentoring.epam.test.lesson8.model.Cars;

import com.mentoring.epam.test.lesson9.readfromfile.model.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Iurii_Galias on 3/2/15.
 */
public class TxtFileReader {
    public static final String SettingsFile = "Settings.txt";

    private String fullPath;
    public static String login;
    public static String domain;
    public static String password;
    public static String topic;
    public static String messageBody;

    public static void initializeSettings(){
        TxtFileReader txtFileReader = new TxtFileReader(SettingsFile);
        try{
            Settings settings = txtFileReader.readSettingsFromFile();
            System.out.println(settings);
        } catch (NullPointerException ex){
            System.out.println("The settings file" + SettingsFile + "is absent" );
        }
    }


    public TxtFileReader(String fullPath){
        this.fullPath = fullPath;
    }


    public Settings readSettingsFromFile(){
        File f = new File(getFullPath());
        BufferedReader br = null;
        String str;
        Settings settings= null;
        try {
            br = new BufferedReader(new FileReader(f));

            while ((str = br.readLine())!= null){
                String[] info = str.split(",");
                try {
                    settings = new Settings(info[0], info[1], info[2], info[3], info[4]);
                    login = info[0];
                    domain = info[1];
                    password = info[2];
                    topic = info[3];
                    messageBody = info[4];
                }catch (Exception ex){
                    System.out.println("The current position is incorrect, please choose another position");
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (br!=null){
                    br.close();
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return settings;
    }

    public String getFullPath(){
        return fullPath;
    }

}
