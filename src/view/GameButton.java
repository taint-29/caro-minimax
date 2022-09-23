package view;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {
    private int width;
    private int high;
    private int x;
    private int y;

    public GameButton() {
        this.setFocusPainted(false);
        this.setFont(new Font("",Font.BOLD,20));
        this.setBackground(new Color(125,190,255));
        this.setForeground(Color.white);
    }

    public GameButton(int x, int y, int width, int high){
//        this.setBounds(this.x, this.y,this.width, this.high);
        this.setFocusPainted(false);
        this.setFont(new Font("",Font.BOLD,20));
        this.setBackground(new Color(125,190,255));
        this.setForeground(Color.white);
    }

}
