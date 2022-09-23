package view;

import java.awt.*;

public class RatingButton extends GameButton{
    public RatingButton(int x, int y, int width, int high) {
        super();
        this.setBounds(x, y, width, high);
        this.setText("Ranking");
        this.setBackground(new Color(134, 117, 169));
    }
}
