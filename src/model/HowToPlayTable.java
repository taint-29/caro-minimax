package model;

import javax.swing.*;
import java.awt.*;

public class HowToPlayTable extends JPanel {

    private int width = 800;
    private int high = 450;
    private int x = 5;
    private int y = 80;
    private HowToPlayContent content = new HowToPlayContent();

    public HowToPlayTable() {

        //edit panel
        this.setLayout(null);
        this.setBounds(x, y, width, high);
        this.setBackground(new Color(184, 223, 216));

        //Add content into panel

        this.add(content);
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
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public HowToPlayContent getContent() {
        return content;
    }

    public void setContent(HowToPlayContent content) {
        this.content = content;
    }
}
