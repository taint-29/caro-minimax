package model;

import utils.InfoConfig;

import javax.swing.*;
import java.awt.*;

public class CaroTable extends JLabel {
    private int width = 600;
    private int high = 600;
    private int x = 200;
    private int y = 50;
    private CaroButton[][] caroTable = new CaroButton[InfoConfig.tableSize][InfoConfig.tableSize];

    public CaroTable() {
        //edit table

        this.setBackground(Color.green);
        this.setBounds(x,y,width,high);
        this.setLayout(new GridLayout(InfoConfig.tableSize,InfoConfig.tableSize ,-1,-1));

        // add button
        for(int i = 0; i < InfoConfig.tableSize; i++){
            for (int j = 0; j < InfoConfig.tableSize; j++){
                    caroTable[i][j] = new CaroButton();
                    this.add(caroTable[i][j]);
            }
        }



    }

    public CaroButton getButton(int i , int j){
        return caroTable[i][j];
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
}
