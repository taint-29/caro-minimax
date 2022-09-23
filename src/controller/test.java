package controller;

import model.RatingTable;
import utils.DBUtils;
import utils.InfoConfig;
import view.MainFrame;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
//        InfoConfig infoConfig = new InfoConfig();
//        infoConfig.readFileConfig();
//        DBUtils dbUtils = new DBUtils();
//        dbUtils.getTopPlayer();
//        RatingTable ratingTable = new RatingTable();
//        ratingTable.printToTable();
        InfoConfig infoConfig = new InfoConfig();
        infoConfig.readFileConfig();
        new MainFrame().setVisible();

    }
}
