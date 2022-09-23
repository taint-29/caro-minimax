package view;

import utils.InfoConfig;

import javax.swing.*;
import java.awt.*;

public class Title extends JLabel {

    private int width = 600;
    private int high = 50;
    private int x = 100;
    private int y = 0;

    public Title() {
        this.setBounds(x, y, width, high);
        this.setText("TOP " + InfoConfig.topHighScore + " HIỆU SỐ CAO NHẤT");
        this.setFont(new Font("", Font.BOLD, 20));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setForeground(new Color(26, 26, 255));
    }
}
