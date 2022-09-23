package view;

import model.CaroButton;
import model.CaroTable;
import model.PlayerOTable;
import model.PlayerXTable;
import service.PlayEven;
import utils.DBUtils;
import utils.InfoConfig;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class PlayFrame extends JFrame {

    private String playerX;
    private String playerO;
    private int width = 1010;
    private int high = 750;
    private int scoreX = 0;
    private int scoreO = 0;
    private int countHitButton = 0;
    private PlayEven playEven;
    private boolean xFirst = InfoConfig.X_First;
    private CaroTable caroTable = new CaroTable();
    private PlayerXTable playerXTable = new PlayerXTable();
    private PlayerOTable playerOTable = new PlayerOTable();
    private AnnounPlayerTurn announPlayerTurn = new AnnounPlayerTurn();
    private DBUtils dbUtils = new DBUtils();
    private BackButton backButton = new BackButton(10, 620, 100, 40);
    private NewGameButton newGameButton = new NewGameButton(590, 640, 200, 40);
    private SaveButton saveButton = new SaveButton(208,640,200,40);
    private Stack<CaroButton> stack = new Stack<CaroButton>();
    private ImageIcon imageIcon = new ImageIcon("src/image/bieutuonggame.png");


    public PlayFrame(String playerX, String playerO) {
        // set name 2 player
        this.playerX = playerX;
        this.playerO = playerO;

        // edit frame
        this.setTitle("Caro ^^");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(width, high);
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null); // set to center of screen

        // add to frame
        this.add(announPlayerTurn);
        this.add(caroTable);
        this.add(playerXTable);
        this.add(playerOTable);
        this.add(saveButton);
        this.add(backButton);
        this.add(newGameButton);


        // set event
        playEven = new PlayEven(this);
        backButton.addActionListener(this.playEven);
        newGameButton.addActionListener(this.playEven);
        saveButton.addActionListener(this.playEven);
        for (int i = 0; i < InfoConfig.tableSize; i++) {
            for (int j = 0; j < InfoConfig.tableSize; j++) {
                this.caroTable.getButton(i,j).addActionListener(this.playEven);
            }
        }


    }

    public void announcementTurn() {
        if (xFirst == true) {
            announPlayerTurn.setText(playerX + " turn: X");
        } else {
            announPlayerTurn.setText(playerO + " turn: O");
        }
    }

    public void DrawAction() {
        // set text announcement
        announPlayerTurn.setText("Draw ^^");

        // enable all button
        for (int i = 0; i < InfoConfig.tableSize; i++) {
            for (int j = 0; j < InfoConfig.tableSize; j++) {
                caroTable.getButton(i, j).setVisible(false);
            }
        }

        // display new game and save button
        this.newGameButton.setVisible(true);
        this.saveButton.setVisible(true);

        //hide back button
        backButton.setVisible(false);

    }

    public void WinAction(String player) {
        // announcement win player
        announPlayerTurn.setText(player + " win");

        // change color to win button
        for (int i = 0; i < InfoConfig.numberWin; i++) {
            CaroButton caroButton = stack.peek();
            caroButton.setWinButton();
            stack.pop();
        }

        //enable all button
        for (int i = 0; i < InfoConfig.tableSize; i++) {
            for (int j = 0; j < InfoConfig.tableSize; j++) {
                caroTable.getButton(i, j).setEnabled(false);
            }
        }

        // display new game and save button
        newGameButton.setVisible(true);
        saveButton.setVisible(true);

        //hide back button
        backButton.setVisible(false);

    }

    public void newGameAction(){
        // set color back
        for (int i = 0; i < InfoConfig.tableSize; i++){
            for (int j = 0; j < InfoConfig.tableSize; j++){
                CaroButton caroButton = this.getCaroTable().getButton(i,j);
                caroButton.setVisible(true);
                caroButton.setEnabled(true);
                caroButton.setDefault();
            }
        }

        // clear stack
        this.clearStack();

        //set hit button to 0
        this.setCountHitButton(0);

        // hide save button and new game button
        this.getSaveButton().setVisible(false);
        this.getNewGameButton().setVisible(false);

        // show back button
        this.getBackButton().setVisible(true);

        // show announcement turn
        this.announcementTurn();
    }

    public void setVisible() {
        this.announcementTurn();
//        this.saveButton.setVisible(true);
//        this.newGameAction();
        this.playerXTable.updateScore(playerX, scoreX);
        this.playerOTable.updateScore(playerO, scoreO);
        this.setVisible(true);
    }

    public void addToStack(CaroButton caroButton) {
        this.stack.push(caroButton);
    }

    public void clearStack() {
        while (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public void countPoint(char point) {
        if (point == 'X') {
            scoreX++;
        } else {
            scoreO++;
        }
    }

    public void increaseHitButton() {
        countHitButton++;
    }


    public String getPlayerX() {
        return playerX;
    }

    public void setPlayerX(String playerX) {
        this.playerX = playerX;
    }

    public String getPlayerO() {
        return playerO;
    }

    public void setPlayerO(String playerO) {
        this.playerO = playerO;
    }

    public int getScoreX() {
        return scoreX;
    }

    public void setScoreX(int scoreX) {
        this.scoreX = scoreX;
    }

    public int getScoreO() {
        return scoreO;
    }

    public void setScoreO(int scoreO) {
        this.scoreO = scoreO;
    }

    public int getCountHitButton() {
        return countHitButton;
    }

    public void setCountHitButton(int countHitButton) {
        this.countHitButton = countHitButton;
    }

    public boolean isxFirst() {
        return xFirst;
    }

    public void setxFirst(boolean xFirst) {
        this.xFirst = xFirst;
    }

    public CaroTable getCaroTable() {
        return caroTable;
    }

    public void setCaroTable(CaroTable caroTable) {
        this.caroTable = caroTable;
    }

    public PlayerXTable getPlayerXTable() {
        return playerXTable;
    }

    public void setPlayerXTable(PlayerXTable playerXTable) {
        this.playerXTable = playerXTable;
    }

    public PlayerOTable getPlayerOTable() {
        return playerOTable;
    }

    public void setPlayerOTable(PlayerOTable playerOTable) {
        this.playerOTable = playerOTable;
    }

    public BackButton getBackButton() {
        return backButton;
    }

    public void setBackButton(BackButton backButton) {
        this.backButton = backButton;
    }

    public NewGameButton getNewGameButton() {
        return newGameButton;
    }

    public void setNewGameButton(NewGameButton newGameButton) {
        this.newGameButton = newGameButton;
    }

    public SaveButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(SaveButton saveButton) {
        this.saveButton = saveButton;
    }
}
