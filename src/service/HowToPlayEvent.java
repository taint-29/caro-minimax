package service;

import view.HowToPlayFrame;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPlayEvent implements ActionListener {

    private HowToPlayFrame howToPlayFrame;

    public HowToPlayEvent(HowToPlayFrame howToPlayFrame) {
        this.howToPlayFrame = howToPlayFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == howToPlayFrame.getBackButton()){
            new MainFrame().setVisible();
            this.howToPlayFrame.dispose();
        }

    }
}
