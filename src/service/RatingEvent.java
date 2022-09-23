package service;

import view.MainFrame;
import view.RatingFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RatingEvent implements ActionListener {

    private RatingFrame ratingFrame;

    public RatingEvent(RatingFrame ratingFrame) {
        this.ratingFrame = ratingFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ratingFrame.getBackButton()){
            new MainFrame().setVisible();
            ratingFrame.dispose();
        }
    }
}
