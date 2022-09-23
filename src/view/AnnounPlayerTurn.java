package view;

import javax.swing.*;
import java.awt.*;

public class AnnounPlayerTurn extends JLabel{

    private int width = 600;
    private int high = 50;
    private int x = 200;
    private int y = 0;

    public AnnounPlayerTurn() {

        this.setBounds(x, y, width, high);
        this.setForeground(Color.BLUE);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);
        this.setFont(new Font("",Font.BOLD,30));

    }
}
