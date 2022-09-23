package model;

import javax.swing.*;
import java.awt.*;

public class CaroButton extends JButton {

    public Point point;
    public static boolean isXMove = true;
    public int value = 0;

    public CaroButton() {
        this.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        this.setFont(new Font("MV Boli",Font.BOLD,35));
        this.setFocusable(false);
        this.setBackground(new Color(69, 98, 104));

    }

    public CaroButton(int x, int y) {

        this.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        this.setFont(new Font("MV Boli",Font.BOLD,20));
        this.setFocusable(false);
        this.setBackground(new Color(125,190,255));
        this.point = new Point(x,y);
    }

    public void setState(Boolean isXMove) {

        if (isXMove) {
//			setIcon(X);
            this.setForeground(new Color(233, 59, 129));
            this.setText("X");
            value = 2;
            this.isXMove = false;
        } else {
//			setIcon(O);
            value = 1;
            this.setForeground(new Color(0, 255, 0));
            this.setText("O");
            this.isXMove = true;
        }

    }



    public void setX(){
        this.setForeground(new Color(233, 59, 129));
        this.setText("X");
    }

    public void setO(){
        this.setForeground(new Color(222, 238, 234));
        this.setText("O");
    }

    public void setDefault(){
        this.setBackground(new Color(69, 98, 104));
        this.setText("");
    }

    public void setWinButton(){

        this.setBackground(Color.red);
        this.setFont(new Font("MV Boli", Font.BOLD, 38));

    }



}
