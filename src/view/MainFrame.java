package view;

import model.Background;
import service.MainEvent;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private int width = 690;
    private int high = 720;
    private Background background = new Background();
    private GameButton playButton = new PlayButton(200,420,300,50);
    private GameButton playWithComputerButton = new PlayWithComputerButton(200,480,300,50);
    private GameButton howToPlayButton = new HowToPlayButton(200,540,300,50);
    private GameButton ratingButton = new RatingButton(200,600,300,50);
    private ImageIcon imageIcon = new  ImageIcon("src/image/bieutuonggame.png");
    private MainEvent mainEvent;

    public MainFrame(){

        // edit frame
        this.setTitle("Caro ^^");
        this.setSize(this.width,this.high);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // set frame in main screen
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(Color.white);
//        this.setVisible(true);

        // add background to frame
        this.add(background);

        //add button into frame
        this.add(playButton);
        this.add(playWithComputerButton);
        this.add(howToPlayButton);
        this.add(ratingButton);
//        this.add(backButton);

        //add action listen for button
        this.mainEvent = new MainEvent(this);
        this.playButton.addActionListener(this.mainEvent);
        this.howToPlayButton.addActionListener(this.mainEvent);
        this.playWithComputerButton.addActionListener(this.mainEvent);
        this.ratingButton.addActionListener(this.mainEvent);


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



    public void setBackground(Background background) {
        this.background = background;
    }

    public GameButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(GameButton playButton) {
        this.playButton = playButton;
    }

    public GameButton getPlayWithComputerButton() {
        return playWithComputerButton;
    }

    public void setPlayWithComputerButton(GameButton playWithComputerButton) {
        this.playWithComputerButton = playWithComputerButton;
    }

    public GameButton getHowToPlayButton() {
        return howToPlayButton;
    }

    public void setHowToPlayButton(GameButton howToPlayButton) {
        this.howToPlayButton = howToPlayButton;
    }

    public GameButton getRatingButton() {
        return ratingButton;
    }

    public void setRatingButton(GameButton ratingButton) {
        this.ratingButton = ratingButton;
    }
}
