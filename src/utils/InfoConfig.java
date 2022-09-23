package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class InfoConfig {
    public static String DB_DRIVER;
    public static String URL;
    public static String USER;
    public static String PASS;

    public static int tableSize;
    public static int numberWin;
    public static int topHighScore;
    public static boolean X_First;

    public void readFileConfig(){
        Properties p = new Properties();
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
//            f = new File("resources/config.properties");
            fr = new FileReader("config.ini");
            br = new BufferedReader(fr);
            p.load(br);
            br.close();
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        DB_DRIVER = p.getProperty("DB_DRIVER");
        URL = p.getProperty("URL");
        USER = p.getProperty("USER");
        PASS = p.getProperty("PASS");
        tableSize = Integer.parseInt(p.getProperty("tableSize"));
        numberWin = Integer.parseInt(p.getProperty("numberWin"));
        X_First = Boolean.parseBoolean(p.getProperty("X_First"));
        topHighScore = Integer.parseInt(p.getProperty("topHighScore"));


//        System.out.println(p.getProperty("DB_DRIVER"));
    }
}
