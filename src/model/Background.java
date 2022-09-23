package model;

import javax.swing.*;
import java.awt.*;

public class Background extends JLabel {

    private int width = 660;
    private int high = 450;
    private int x = 0;
    private int y = 0;
    private ImageIcon imageIcon = new ImageIcon("src/image/hinhnen.png");

    public Background() {

        this.setBounds(x, y, width, high);
        this.setLayout(null);
        this.setIcon(imageIcon);
//        this.setText("Caro");
        this.setBackground(Color.white);
        this.setFont(new Font("",Font.BOLD,50));
//        this.setForeground(Color.blue);
        this.setIconTextGap(-100);
        this.setVerticalTextPosition(JLabel.TOP);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setOpaque(true); // to set color

    }

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
