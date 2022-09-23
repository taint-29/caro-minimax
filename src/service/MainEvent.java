package service;

import valid.StringValid;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainEvent implements ActionListener {

    private StringValid stringValid;
    private MainFrame mainFrame;

    public MainEvent(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == mainFrame.getPlayButton()){
            String playerX, playerO;
            playerX = JOptionPane.showInputDialog(null,"Player X: ","Enter information",JOptionPane.INFORMATION_MESSAGE);
            playerO = JOptionPane.showInputDialog(null,"Player O: ","Enter information",JOptionPane.INFORMATION_MESSAGE);

//            if(playerX != null && playerX.equals("") == false && playerO != null && playerO.equals("") == false){
//                if (playerX == playerO){
//                    JOptionPane.showMessageDialog(null, "Name of 2 player must be different", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
//                }else if(!stringValid.checkLength(playerX)){
//                    JOptionPane.showMessageDialog(null, "Name of playerX must be less than 20", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
//                }else if(!stringValid.checkLength(playerO)) {
//                    JOptionPane.showMessageDialog(null, "Name of playerO must be less than 20", "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
//                }
//                else {
//                    new PlayFrame(playerX, playerO).setVisible();
//                    this.mainFrame.dispose();
//                }
//            }else{
//                JOptionPane.showMessageDialog(null, "you have to enter full information for each player", "Notification", JOptionPane.WARNING_MESSAGE);
//            }

        }else if(e.getSource() == mainFrame.getHowToPlayButton()){
            new HowToPlayFrame().setVisible(true);
            mainFrame.dispose();
        }else if(e.getSource() == mainFrame.getPlayWithComputerButton()){

            new PlayWithComputerFrame();
            mainFrame.dispose();

        }
        else if(e.getSource() == mainFrame.getRatingButton()){
            try {
                new RatingFrame().display();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            mainFrame.dispose();
        }

    }
}
