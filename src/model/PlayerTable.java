package model;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PlayerTable extends JLabel{
    private int width = 200;
    private int high = 550;
//    private int x = 800;
    private int y = 0;

    public PlayerTable(int x) {
        this.setBounds(x, y, width, high);
        this.setFont(new Font("",Font.BOLD,25));
        this.setVerticalTextPosition(JLabel.TOP);
        this.setHorizontalTextPosition(JLabel.CENTER);

    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }


    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void updateScore(String player, int score){
        this.setText(player + ": "+score);
    }
}
