package model;

public class Player {
    private String name;
    private int win;
    private int lose;
    private int different;

    public Player() {
    }

    public Player(String name, int win, int lose, int different) {
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.different = different;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getDifferent() {
        return different;
    }

    public void setDifferent(int different) {
        this.different = different;
    }
}
