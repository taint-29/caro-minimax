package utils;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public List<Player> getTopPlayer() throws SQLException {

        String sql = "select * from nguoichoi" +
                " order by (Thang-Thua) desc" +
                " limit " + InfoConfig.topHighScore;

        Connection conn = null;
        Statement stt = null;
        ResultSet rs = null;
        List<Player> playerList = new ArrayList<Player>();

        try {
            Class.forName(InfoConfig.DB_DRIVER);
            conn = DriverManager.getConnection(InfoConfig.URL, InfoConfig.USER, InfoConfig.PASS);
            stt = conn.createStatement();
            stt.execute(sql);
            rs = stt.getResultSet();

            while (rs.next()) {
                String name = rs.getString("Ten");
                int win = rs.getInt("Thang");
                int lose = rs.getInt("Thua");
                int different = win - lose;
                System.out.println(win + "\t" + lose + "\t" + different);
                playerList.add(new Player(name, win, lose, different));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stt.close();
            rs.close();
        }

        return playerList;
    }

    public void saveToDB(String player, int win, int lose) throws SQLException {

        String query = "select * from nguoichoi where Ten = '" + player + "'";
        Connection conn = null;
        Statement stt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(InfoConfig.URL, InfoConfig.USER, InfoConfig.PASS);
            stt = conn.createStatement();
            stt.execute(query);
            rs = stt.getResultSet();
            int getWin = -1;
            int getLose = -1;

            while (rs.next()) {
                getWin = rs.getInt("Thang");
                getLose = rs.getInt("Thua");
            }

            if (getWin == -1 && getLose == -1) {
                stt.execute("insert into nguoichoi(Ten, Thang, Thua) values ('" + player + "', " + win + ", " + lose + ")");
            } else {
                stt.execute("update nguoichoi set Thang = " + (getWin + win) + ", Thua = " + (getLose + lose) + " where Ten = '" + player + "'");

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            conn.close();
            stt.close();
            rs.close();

        }


    }
}
