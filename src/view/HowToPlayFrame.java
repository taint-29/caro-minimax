package view;

import model.HowToPlayTable;
import service.HowToPlayEvent;

import javax.swing.*;
import java.awt.*;

public class HowToPlayFrame extends JFrame {

    private int width = 800;
    private int high = 720;
    private ImageIcon imageIcon = new ImageIcon("src/image/bieutuonggame.png");
    private BackButton backButton = new BackButton(10,620,100,40);
    private HowToPlayTable howToPlayTable = new HowToPlayTable();
    private HowToPlayEvent howToPlayEvent;
    JLabel top = new JLabel();
    JLabel bottom = new JLabel();
    private ImageIcon icon = new ImageIcon("src/image/iconhuongdan.png");

    public HowToPlayFrame(){

        //edit frame
        this.setTitle("Caro ^^");
        this.setSize(this.width,this.high);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // set frame in main screen
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(Color.white);
        top.setBounds(-100, 25, 1000, 50);
        top.setHorizontalAlignment(JLabel.CENTER);
        top.setVerticalAlignment(JLabel.TOP);
        top.setIcon(icon);
        bottom.setBounds(0, 40, 800, 550);
        bottom.setHorizontalAlignment(JLabel.CENTER);
        bottom.setVerticalAlignment(JLabel.BOTTOM);
        bottom.setIcon(icon);
//        this.setVisible(true);

        // add content into frame
        this.add(top);
        this.add(bottom);
        this.add(howToPlayTable);

        // add back button
        this.add(backButton);

        // set even
        howToPlayEvent = new HowToPlayEvent(this);
        backButton.addActionListener(howToPlayEvent);

    }

    public void setVisible(){
        this.setVisible(true);
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

    public BackButton getBackButton() {
        return backButton;
    }

    public void setBackButton(BackButton backButton) {
        this.backButton = backButton;
    }
}
