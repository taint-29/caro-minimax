package model;

import javax.swing.*;

public class PlayerXTable extends PlayerTable{
    private ImageIcon imageIcon = new ImageIcon("src/image/nguoichoix.png");

    public PlayerXTable() {
        super(10);
        this.setIcon(imageIcon);
    }
}
