package view;

import javax.swing.*;
import java.awt.*;

public class HowToPlayButton extends GameButton {

    public HowToPlayButton(int x, int y, int width, int high) {
        super();
        this.setBounds(x, y, width, high);
        this.setText("How to play");
        this.setBackground(new Color(195, 174, 214));
    }
}
