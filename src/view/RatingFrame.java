package view;

import model.RatingTable;
import service.RatingEvent;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RatingFrame extends JFrame {

    private int width = 800;
    private int high = 720;
    private Title title = new Title();
    private BackButton backButton = new BackButton(10,620,100,40);
    private RatingTable ratingTable = new RatingTable();
    private RatingEvent ratingEvent;

    private ImageIcon icon = new ImageIcon("src/image/bieutuonggame.png");

    public RatingFrame() throws SQLException {

        // edit frame
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setTitle("GAME CARO");
        this.setSize(width,high);
        this.setIconImage(this.icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // add to frame
        this.add(title);
//        announPlayerTurn.setText("aaaa");
//        this.add(announPlayerTurn);
        this.add(backButton);
        this.add(ratingTable);

        // set even
        ratingEvent = new RatingEvent(this);
        backButton.addActionListener(ratingEvent);
    }


    public void setTitle(Title title) {
        this.title = title;
    }

    public BackButton getBackButton() {
        return backButton;
    }

    public void setBackButton(BackButton backButton) {
        this.backButton = backButton;
    }

    public void display() throws SQLException {
        // print to table
        this.ratingTable.printToTable();
        this.setVisible(true);
    }

}
