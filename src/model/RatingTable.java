package model;

import utils.DBUtils;
import utils.InfoConfig;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingTable extends JLabel {
    private int width = 775;
    private int high = 560;
    private int x = 5;
    private int y = 50;
    private DBUtils dbUtils = new DBUtils();
    private JLabel[][] table = new JLabel[InfoConfig.topHighScore + 1][5];
    List<Player> playerList = new ArrayList<Player>();

    public RatingTable() throws SQLException {
        // edit lable
        this.setBounds(x, y, width, high);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(InfoConfig.topHighScore + 1, 5, 0, 0));

    }

    public void printToTable() throws SQLException {
        playerList = dbUtils.getTopPlayer();
        System.out.println(InfoConfig.topHighScore);


        // set nd add label to table

        for (int i = 0; i < InfoConfig.tableSize + 1; i++) {
            for (int j = 0; j < 5; j++) {
                this.table[i][j] = new JLabel();
                this.table[i][j].setHorizontalAlignment(JLabel.CENTER);
                this.table[i][j].setVerticalAlignment(JLabel.CENTER);
                this.table[i][j].setFont(new Font("", Font.BOLD, 15));
                this.table[i][j].setBackground(new Color(230, 77, 0));
                this.table[i][j].setOpaque(true);
                this.table[i][j].setForeground(Color.black);
                this.table[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 1));
                this.add(this.table[i][j]);
            }
        }

        // add information player to table
        int i = 1;
        for (Player player : playerList){
            this.table[i][0].setText(String.valueOf(i));
            this.table[i][1].setText(player.getName());
            this.table[i][2].setText(String.valueOf(player.getWin()));
            this.table[i][3].setText(String.valueOf(player.getLose()));
            this.table[i][4].setText(String.valueOf(player.getDifferent()));
            i++;
//            System.out.println(player.getName() + "\t" + player.getWin() + "\t" + player.getLose() + "\t" + player.getDifferent());
        }

        // set title of each column
        this.table[0][0].setText("Id");
        this.table[0][0].setForeground(Color.white);
        this.table[0][1].setText("Player name");
        this.table[0][1].setForeground(Color.white);
        this.table[0][2].setText("Win");
        this.table[0][2].setForeground(Color.white);
        this.table[0][3].setText("Lose");
        this.table[0][3].setForeground(Color.white);
        this.table[0][4].setText("Different");
        this.table[0][4].setForeground(Color.white);

    }
}
