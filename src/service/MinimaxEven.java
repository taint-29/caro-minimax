package service;

import minimax.NormalMinimax;
import model.CaroButton;
import model.Point;
import utils.InfoConfig;
import view.MainFrame;
import view.PlayWithComputerFrame;

import javax.sound.sampled.Line;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinimaxEven implements ActionListener {

    private NormalMinimax normalMinimax;

    public MinimaxEven(NormalMinimax playWithComputerFrame) {
        this.normalMinimax = playWithComputerFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == normalMinimax.getBackButton()){
            new MainFrame().setVisible(true);
            this.normalMinimax.dispose();
        }
        if (e.getSource() == normalMinimax.getNewGameButton()){
            this.normalMinimax.newGameAction();
        }
    }

}
