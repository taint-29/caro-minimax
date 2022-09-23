package model;

import javax.swing.*;

public class PeopleTable extends PlayerTable{
    private ImageIcon imageIcon = new ImageIcon("src/image/nguoi.png");
    public PeopleTable() {
        super(0);
        this.setIcon(imageIcon);
    }
}
