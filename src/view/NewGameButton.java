package view;

import javax.swing.*;

public class NewGameButton extends GameButton {
    public NewGameButton(int x, int y, int width, int high) {
        super();
        this.setBounds(x, y, width, high);
        this.setText("New Game");
        this.setVisible(false);
    }
}
