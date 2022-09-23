package view;

import javax.swing.*;
import java.awt.*;

public class SaveButton extends GameButton {

    public SaveButton(int x, int y, int width, int high) {
        super();
        this.setBounds(x,y,width,high);
        this.setText("Save Game");
        this.setVisible(false);
    }
}
