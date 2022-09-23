package view;

import javax.swing.*;
import java.awt.*;

public class BackButton extends GameButton {
    public BackButton(int x, int y, int width, int high) {
        super();
        this.setBounds(x, y, width, high);
        this.setText("Back");
        this.setBackground(new Color(255, 225, 148));
    }
}
