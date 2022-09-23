package service;

import model.CaroButton;
import utils.DBUtils;
import utils.InfoConfig;
import view.MainFrame;
import view.PlayFrame;

import javax.sound.midi.MidiDevice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayEven implements ActionListener {

    PlayFrame playFrame;
    DBUtils dbUtils = new DBUtils();

    public PlayEven(PlayFrame playFrame) {
        this.playFrame = playFrame;
    }

    // check row. text is mean x or o
    public boolean checkRow(int row, String text) {

        int cnt = 0;

        for (int i = 0; i < InfoConfig.tableSize; i++) {
            CaroButton caroButton = this.playFrame.getCaroTable().getButton(row, i);
            String textButton = caroButton.getText();
            if (textButton.equals(text)) {
                cnt++;
                this.playFrame.addToStack(caroButton);
            } else {
                cnt = 0;
            }

            if (cnt == InfoConfig.numberWin) {
                return true;
            }
        }
        return false;
    }

    // check col. text is mean x or o
    public boolean checkCol(int col, String text) {

        int cnt = 0;

        for (int i = 0; i < InfoConfig.tableSize; i++) {
            CaroButton caroButton = this.playFrame.getCaroTable().getButton(i, col);
            String textButton = caroButton.getText();
            if (textButton.equals(text)) {
                cnt++;
                this.playFrame.addToStack(caroButton);
            } else {
                cnt = 0;
            }

            if (cnt == InfoConfig.numberWin) {

                return true;
            }
        }
        return false;
    }

    // check main cross
    public boolean checkMainCross(int row, int col, String text) {

        int cnt = 0;
        int t = row + col;

        for (int i = 0; i < InfoConfig.tableSize; i++) {
            if (t - i >= 0 && t - i < InfoConfig.tableSize) {
                CaroButton caroButton = this.playFrame.getCaroTable().getButton(i, t - i);
                String textButton = caroButton.getText();
                if (textButton.equals(text)) {
                    cnt++;
                    this.playFrame.addToStack(caroButton);
                } else {
                    cnt = 0;
                }

                if (cnt == InfoConfig.numberWin) {
                    return true;
                }
            }
        }
        return false;
    }

    // check sub cross
    public boolean checkSubCross(int row, int col, String text) {

        int cnt = 0;
        int t = row - col;

        for (int i = 0; i < InfoConfig.tableSize; i++) {
            if (i - t >= 0 && i - t < InfoConfig.tableSize) {
                CaroButton caroButton = this.playFrame.getCaroTable().getButton(i, i - t);
                String textButton = caroButton.getText();
                if (textButton.equals(text)) {
                    cnt++;
                    this.playFrame.addToStack(caroButton);
                } else {
                    cnt = 0;
                }

                if (cnt == InfoConfig.numberWin) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.playFrame.getNewGameButton()) {
            playFrame.newGameAction();

        } else if (e.getSource() == this.playFrame.getBackButton()) {

            new MainFrame().setVisible(true);
            this.playFrame.dispose();

        } else if (e.getSource() == this.playFrame.getSaveButton()) {

            try {
                dbUtils.saveToDB(this.playFrame.getPlayerX(), this.playFrame.getScoreX(), this.playFrame.getScoreO());
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            new MainFrame().setVisible(true);
            this.playFrame.dispose();

        } else {
            for (int i = 0; i < InfoConfig.tableSize; i++) {
                for (int j = 0; j < InfoConfig.tableSize; j++) {
                    CaroButton caroButton = this.playFrame.getCaroTable().getButton(i, j);
                    if (e.getSource() == caroButton) {
                        if (caroButton.getText() == "") {
                            if (this.playFrame.isxFirst()) {
                                caroButton.setX();
                                this.playFrame.setxFirst(false);
                                this.playFrame.increaseHitButton();

                                if (checkCol(j, "X") == true || checkRow(i, "X") == true
                                        || checkMainCross(i, j, "X") == true || checkSubCross(i, j, "X")) {

                                    this.playFrame.WinAction(this.playFrame.getPlayerX());
                                    this.playFrame.countPoint('X');
                                    this.playFrame.getPlayerXTable().updateScore(this.playFrame.getPlayerX(), this.playFrame.getScoreX());

                                } else if (this.playFrame.getCountHitButton() == InfoConfig.tableSize * InfoConfig.tableSize) {
                                    this.playFrame.DrawAction();
                                } else {
                                    this.playFrame.announcementTurn();
                                }

                            } else {

                                this.playFrame.setxFirst(true);
                                caroButton.setO();
                                this.playFrame.increaseHitButton();
                                if (checkCol(j, "O") == true || checkRow(i, "O") == true
                                        || checkMainCross(i, j, "O") == true || checkSubCross(i, j, "O")) {

                                    this.playFrame.WinAction(playFrame.getPlayerO());
                                    this.playFrame.countPoint('O');
                                    this.playFrame.getPlayerOTable().updateScore(playFrame.getPlayerO(), this.playFrame.getScoreO());

                                } else if (this.playFrame.getCountHitButton() == InfoConfig.tableSize * InfoConfig.tableSize) {
                                    this.playFrame.DrawAction();
                                } else {
                                    this.playFrame.announcementTurn();
                                }

                            }
                        }
                    }
                }
            }

        }

    }
}
