package view;

import javax.swing.*;
import java.awt.*;

public class PlayButton extends GameButton {
    public PlayButton(int x, int y, int width, int high) {
        super();
        this.setBounds(x, y, width, high);
        this.setText("Play Game");
        this.setBackground(new Color(255, 213, 205));

    }
}
