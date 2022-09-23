package view;

import javax.swing.*;
import java.awt.*;

public class PlayWithComputerButton extends GameButton {
    public PlayWithComputerButton(int x, int y, int width, int high) {
        super();
        this.setBounds(x, y, width, high);
        this.setText("Play with computer");
        this.setBackground(new Color(239, 187, 207));
    }
}
