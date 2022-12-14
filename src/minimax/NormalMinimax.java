package minimax;

import model.*;
import model.Point;
import service.MinimaxEven;
import utils.InfoConfig;
import view.AnnounPlayerTurn;
import view.BackButton;
import view.NewGameButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class NormalMinimax extends JFrame {

    private int row = 19;
    private int col = 19;
    private String playerX;
    private String playerO;
    public CaroButton[][] Buttons = new CaroButton[col][row];
    private ArrayList<Point> availablesPoint = new ArrayList<Point>();
    private static final int winScore = 100000000;
    private BackButton backButton = new BackButton(10, 650, 100, 40);
    private NewGameButton newGameButton = new NewGameButton(600, 640, 200, 40);
    private PeopleTable playerXTable = new PeopleTable();
    private AITable playerOTable = new AITable();
    private AnnounPlayerTurn announPlayerTurn = new AnnounPlayerTurn();
    private MinimaxEven minimaxEven;
    private int scoreX = 0;
    private int scoreO = 0;

    JPanel p = new JPanel();


    /**
     * Create the application.
     */
    public NormalMinimax() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

//        frame = new JFrame();
        this.playerX = "you<3";
        this.playerO = "AI";
        this.playerXTable.updateScore(playerX, scoreX);
        this.playerOTable.updateScore(playerO, scoreO);

        this.setLayout(null);
        this.setSize(1010, 750);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Caro ^^");
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        p.setLayout(new GridLayout(col, row));
        for (int i = 0; i < Buttons.length; i++) {
            for (int j = 0; j < Buttons.length; j++) {
                Point point = new Point(i, j);
                Buttons[i][j] = new CaroButton(i, j);
                MouseListener mouseEvent = new MouseListener() {

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub
                        handleClickButton(point);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }
                };
                Buttons[i][j].addMouseListener(mouseEvent);

                p.add(Buttons[i][j]);
                availablesPoint.add(point);
            }
        }

        p.setBounds(200, 40, 600, 600);

        this.add(backButton);
        this.add(newGameButton);
        this.add(p);
        this.add(playerXTable);
        this.add(playerOTable);
        this.add(announPlayerTurn);
        this.setVisible(true);

        // add even
        minimaxEven = new MinimaxEven(this);
        backButton.addActionListener(minimaxEven);
        newGameButton.addActionListener(minimaxEven);
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

    public void newGameAction() {
        // clear all vailables point
        availablesPoint.clear();
        for (int i = 0; i < Buttons.length; i++) {
            for (int j = 0; j < Buttons.length; j++) {
                p.remove(Buttons[i][j]);
                Point point = new Point(i, j);
                Buttons[i][j] = new CaroButton(i, j);
                MouseListener mouseEvent = new MouseListener() {

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub
                        handleClickButton(point);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }
                };
                Buttons[i][j].addMouseListener(mouseEvent);
                p.add(Buttons[i][j]);
                availablesPoint.add(point);
            }
        }

//        System.out.println("after change ");
//        displayInConsole();

        // hide save button and new game button
        this.getNewGameButton().setVisible(false);

        // show back button
        this.getBackButton().setVisible(true);

    }

    private void handleClickButton(Point point) {

        // TODO: CALC LOGIC HERE


        if (Buttons[point.getX()][point.getY()].value == 0) {
            Buttons[point.getX()][point.getY()].setState(true);
            if (getScore(getMatrixBoard(), true, false) >= winScore) {

                scoreX++;
                finishGameAction();
                JOptionPane.showMessageDialog(null, "You win", "Announcement win player", JOptionPane.INFORMATION_MESSAGE);
                this.newGameButton.setVisible(true);
                return;
            }
            int nextMoveX = 0, nextMoveY = 0;
            int[] bestMove = calcNextMove(3);

            if (bestMove != null) {
                nextMoveX = bestMove[0];
                nextMoveY = bestMove[1];
            }

            if (Buttons[nextMoveX][nextMoveY].value == 0) {
                Buttons[nextMoveX][nextMoveY].setState(false);
//                displayInConsole();

                if (getScore(getMatrixBoard(), false, true) >= winScore) {
                    scoreO++;
                    finishGameAction();
                    JOptionPane.showMessageDialog(null, "AI Win", "Announcement win player", JOptionPane.INFORMATION_MESSAGE);
                    this.newGameButton.setVisible(true);
                    return;

                }
            }
        }

    }

    public void finishGameAction() {

        // update score
        this.playerXTable.updateScore(playerX, scoreX);
        this.playerOTable.updateScore(playerO, scoreO);

        // enable caro frame
        for (int i = 0; i < Buttons.length; i++) {
            for (int j = 0; j < Buttons.length; j++) {
                Buttons[i][j].setEnabled(false);

            }
        }
    }

    private void displayInConsole() {
        for (int i = 0; i < Buttons.length; i++) {
            for (int j = 0; j < Buttons.length; j++) {
                CaroButton button = Buttons[i][j];
                System.out.print(button.value + " ");
            }
            System.out.println();
        }
    }

    private void printBoard(int[][] board) {
        int lenght = board.length;
        System.out.println();
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght; j++) {
                System.out.print(String.format("%d ", board[i][j]));
            }
            System.out.println();
        }

    }

    private void printMoves(ArrayList<int[]> moves) {
        System.out.println();
        for (int[] move : moves) {
            System.out.println("( " + move[0] + ", " + move[1] + ")");
        }
        System.out.println();
    }

    public int[] calcNextMove(int depth) {
        int[][] board = getMatrixBoard();
        Object[] bestMove = searchWinningMove(board);
        Object[] badMove = searchLoseMove(board);


        int[] move = new int[2];

        if (badMove[1] != null && badMove[2] != null) {

            move[0] = (Integer) (badMove[1]);
            move[1] = (Integer) (badMove[2]);
            return move;
        }

        if (bestMove[1] != null && bestMove[2] != null) {

            move[0] = (Integer) (bestMove[1]);
            move[1] = (Integer) (bestMove[2]);

        } else {

            bestMove = minimaxSearchAB(depth, board, true, -1.0, winScore);
            if (bestMove[1] == null) {
                move = null;
            } else {
                move[0] = (Integer) (bestMove[1]);
                move[1] = (Integer) (bestMove[2]);
            }
        }
        return move;
    }

    public int[][] playNextMove(int[][] board, int[] move, boolean isUserTurn) {
        int i = move[0], j = move[1];
        int[][] newBoard = new int[row][col];
        for (int h = 0; h < row; h++) {
            for (int k = 0; k < col; k++) {
                newBoard[h][k] = board[h][k];
            }
        }
        newBoard[i][j] = isUserTurn ? 2 : 1;
        return newBoard;
    }

    private Object[] searchWinningMove(int[][] matrix) {
        ArrayList<int[]> allPossibleMoves = generateMoves(matrix);
        System.out.println(allPossibleMoves.size());

        Object[] winningMove = new Object[3];

        for (int[] move : allPossibleMoves) {
            int[][] dummyBoard = playNextMove(matrix, move, false);

            // If the white player has a winning score in that temporary board, return the move.
            if (getScore(dummyBoard, false, false) >= winScore) {
                winningMove[1] = move[0];
                winningMove[2] = move[1];
                return winningMove;
            }
        }

        return winningMove;
    }

    private Object[] searchLoseMove(int[][] matrix) {
        ArrayList<int[]> allPossibleMoves = generateMoves(matrix);
        System.out.println(allPossibleMoves.size());

        Object[] losingMove = new Object[3];

        for (int[] move : allPossibleMoves) {
            int[][] dummyBoard = playNextMove(matrix, move, true);

            // If the white player has a winning score in that temporary board, return the move.
            if (getScore(dummyBoard, true, false) >= winScore) {
                losingMove[1] = move[0];
                losingMove[2] = move[1];
                return losingMove;
            }
        }

        return losingMove;
    }


    public Object[] minimaxSearchAB(int depth, int[][] board, boolean max, double alpha, double beta) {
        if (depth == 0) {
            Object[] x = {evaluateBoardForWhite(board, !max), null, null};
            return x;
        }


        ArrayList<int[]> allPossibleMoves = generateMoves(board);

        if (allPossibleMoves.size() == 0) {

            Object[] x = {evaluateBoardForWhite(board, !max), null, null};

            return x;
        }

        Object[] bestMove = new Object[3];


        if (max) {
            bestMove[0] = -1.0;

            for (int[] move : allPossibleMoves) {
                // Ch??i th??? v???i move hi???n t???i
                int[][] dummyBoard = playNextMove(board, move, false);

                Object[] tempMove = minimaxSearchAB(depth - 1, dummyBoard, !max, alpha, beta);

                // C???p nh???t alpha
                if ((Double) (tempMove[0]) > alpha) {
                    alpha = (Double) (tempMove[0]);
                }
                // C???t t???a beta
                if ((Double) (tempMove[0]) >= beta) {
                    return tempMove;
                }
                if ((Double) tempMove[0] > (Double) bestMove[0]) {
                    bestMove = tempMove;
                    bestMove[1] = move[0];
                    bestMove[2] = move[1];
                }
            }

        } else {
            bestMove[0] = 100000000.0;
            bestMove[1] = allPossibleMoves.get(0)[0];
            bestMove[2] = allPossibleMoves.get(0)[1];
            for (int[] move : allPossibleMoves) {
                int[][] dummyBoard = playNextMove(board, move, true);

                Object[] tempMove = minimaxSearchAB(depth - 1, dummyBoard, !max, alpha, beta);

                // C???p nh???t beta
                if (((Double) tempMove[0]) < beta) {
                    beta = (Double) (tempMove[0]);
                }
                // C???t t???a alpha
                if ((Double) (tempMove[0]) <= alpha) {
                    return tempMove;
                }
                if ((Double) tempMove[0] < (Double) bestMove[0]) {
                    bestMove = tempMove;
                    bestMove[1] = move[0];
                    bestMove[2] = move[1];
                }
            }
        }
        return bestMove;
    }


    public double evaluateBoardForWhite(int[][] board, boolean userTurn) {

        double blackScore = getScore(board, true, userTurn);
        double whiteScore = getScore(board, false, userTurn);

        if (blackScore == 0) blackScore = 1.0;

        return whiteScore / blackScore;

    }


    public ArrayList<int[]> generateMoves(int[][] boardMatrix) {
        ArrayList<int[]> moveList = new ArrayList<int[]>();

        int boardSize = boardMatrix.length;


        // T??m nh???ng t???t c??? nh???ng ?? tr???ng nh??ng c?? ????nh XO li???n k???
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {

                if (boardMatrix[i][j] > 0) continue;

                if (i > 0) {
                    if (j > 0) {
                        if (boardMatrix[i - 1][j - 1] > 0 ||
                                boardMatrix[i][j - 1] > 0) {
                            int[] move = {i, j};
                            moveList.add(move);
                            continue;
                        }
                    }
                    if (j < boardSize - 1) {
                        if (boardMatrix[i - 1][j + 1] > 0 ||
                                boardMatrix[i][j + 1] > 0) {
                            int[] move = {i, j};
                            moveList.add(move);
                            continue;
                        }
                    }
                    if (boardMatrix[i - 1][j] > 0) {
                        int[] move = {i, j};
                        moveList.add(move);
                        continue;
                    }
                }
                if (i < boardSize - 1) {
                    if (j > 0) {
                        if (boardMatrix[i + 1][j - 1] > 0 ||
                                boardMatrix[i][j - 1] > 0) {
                            int[] move = {i, j};
                            moveList.add(move);
                            continue;
                        }
                    }
                    if (j < boardSize - 1) {
                        if (boardMatrix[i + 1][j + 1] > 0 ||
                                boardMatrix[i][j + 1] > 0) {
                            int[] move = {i, j};
                            moveList.add(move);
                            continue;
                        }
                    }
                    if (boardMatrix[i + 1][j] > 0) {
                        int[] move = {i, j};
                        moveList.add(move);
                        continue;
                    }
                }

            }
        }

        return moveList;

    }


    // ????nh gi?? b??n c??? d???a tr??n t???ng s??? ??i???m h??ng ngan, h??ng d???c, v?? 2 ???????ng ch??o
    public int getScore(int[][] board, boolean forX, boolean blacksTurn) {

        return evaluateHorizontal(board, forX, blacksTurn) +
                evaluateVertical(board, forX, blacksTurn) +
                evaluateDiagonal(board, forX, blacksTurn);
    }

    public static int evaluateHorizontal(int[][] boardMatrix, boolean forX, boolean playersTurn) {

        int consecutive = 0;
        int blocks = 2;
        int score = 0;

        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[0].length; j++) {

                if (boardMatrix[i][j] == (forX ? 2 : 1)) {
                    //2. ?????m...
                    consecutive++;
                }
                // g???p ?? tr???ng
                else if (boardMatrix[i][j] == 0) {
                    if (consecutive > 0) {
                        // Ra: ?? tr???ng ??? cu???i sau khi ?????m. Gi???m block r???i b???t ?????u t??nh ??i???m sau ???? reset l???i ban ?????u
                        blocks--;
                        score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                        consecutive = 0;
                        blocks = 1;
                    } else {
                        // 1. V??o reset l???i blocks = 1 r???i b???t ?????u ?????m
                        blocks = 1;
                    }
                }
                //g???p qu??n ?????ch
                else if (consecutive > 0) {
                    // 2.Ra:  ?? b??? ch???n sau khi ?????m. T??nh ??i???m sau ???? reset l???i.
                    score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                    consecutive = 0;
                    blocks = 2;
                } else {
                    //1. V??o: reset l???i blocks = 2 r???i b???t ?????u ?????m
                    blocks = 2;
                }
            }

            // 3. Ra: nh??ng l??c n??y ??ang ??? cu???i. N???u li??n t???c th?? v???n t??nh cho ?????n h???t d??ng
            if (consecutive > 0) {
                score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);

            }
            // reset l???i ????? ti???p t???c ch???y cho d??ng ti???p theo
            consecutive = 0;
            blocks = 2;

        }
        return score;
    }

    // h??m t??nh to??n ???????ng d???c t????ng t??? nh?? ???????ng ngan
    public static int evaluateVertical(int[][] boardMatrix, boolean forX, boolean playersTurn) {

        int consecutive = 0;
        int blocks = 2;
        int score = 0;

        for (int j = 0; j < boardMatrix[0].length; j++) {
            for (int i = 0; i < boardMatrix.length; i++) {
                if (boardMatrix[i][j] == (forX ? 2 : 1)) {
                    consecutive++;
                } else if (boardMatrix[i][j] == 0) {
                    if (consecutive > 0) {
                        blocks--;
                        score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                        consecutive = 0;
                        blocks = 1;
                    } else {
                        blocks = 1;
                    }
                } else if (consecutive > 0) {
                    score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                    consecutive = 0;
                    blocks = 2;
                } else {
                    blocks = 2;
                }
            }
            if (consecutive > 0) {
                score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);

            }
            consecutive = 0;
            blocks = 2;

        }
        return score;
    }

    // H??m t??nh to??n 2 ???????ng ch??o t????ng t??? nh?? h??ng ngan
    public static int evaluateDiagonal(int[][] boardMatrix, boolean forX, boolean playersTurn) {

        int consecutive = 0;
        int blocks = 2;
        int score = 0;
        // ???????ng ch??o /
        for (int k = 0; k <= 2 * (boardMatrix.length - 1); k++) {
            int iStart = Math.max(0, k - boardMatrix.length + 1);
            int iEnd = Math.min(boardMatrix.length - 1, k);
            for (int i = iStart; i <= iEnd; ++i) {
                int j = k - i;

                if (boardMatrix[i][j] == (forX ? 2 : 1)) {
                    consecutive++;
                } else if (boardMatrix[i][j] == 0) {
                    if (consecutive > 0) {
                        blocks--;
                        score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                        consecutive = 0;
                        blocks = 1;
                    } else {
                        blocks = 1;
                    }
                } else if (consecutive > 0) {
                    score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                    consecutive = 0;
                    blocks = 2;
                } else {
                    blocks = 2;
                }

            }
            if (consecutive > 0) {
                score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);

            }
            consecutive = 0;
            blocks = 2;
        }
        // ???????ng ch??o \
        for (int k = 1 - boardMatrix.length; k < boardMatrix.length; k++) {
            int iStart = Math.max(0, k);
            int iEnd = Math.min(boardMatrix.length + k - 1, boardMatrix.length - 1);
            for (int i = iStart; i <= iEnd; ++i) {
                int j = i - k;

                if (boardMatrix[i][j] == (forX ? 2 : 1)) {
                    consecutive++;
                } else if (boardMatrix[i][j] == 0) {
                    if (consecutive > 0) {
                        blocks--;
                        score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                        consecutive = 0;
                        blocks = 1;
                    } else {
                        blocks = 1;
                    }
                } else if (consecutive > 0) {
                    score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                    consecutive = 0;
                    blocks = 2;
                } else {
                    blocks = 2;
                }

            }
            if (consecutive > 0) {
                score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);

            }
            consecutive = 0;
            blocks = 2;
        }
        return score;
    }

    public static int getConsecutiveSetScore(int count, int blocks, boolean currentTurn) {
        final int winGuarantee = 1000000;
        if (blocks == 2 && count <= 5) return 0;
        switch (count) {
            // ??n 5 -> Cho ??i???m cao nh???t
            case 5: {
                return winScore;
            }
            case 4: {
                // ??ang 4 -> Tu??? theo l?????c v?? b??? ch???n: winGuarantee, winGuarantee/4, 200
                if (currentTurn) return winGuarantee;
                else {
                    if (blocks == 0) return winGuarantee / 4;
                    else return 200;
                }
            }
            case 3: {
                // ??ang 3: Block = 0
                if (blocks == 0) {
                    // N???u l?????c c???a currentTurn th?? ??n 3 + 1 = 4 (kh??ng b??? block) -> 50000 -> Kh??? n??ng th???ng cao.
                    // Ng?????c l???i kh??ng ph???i l?????c c???a currentTurn th?? kh??? n??ng b??? blocks cao
                    if (currentTurn) return 50000;
                    else return 200;
                } else {
                    // Block == 1 ho???c Blocks == 2
                    if (currentTurn) return 10;
                    else return 5;
                }
            }
            case 2: {
                // T????ng t??? v???i 2
                if (blocks == 0) {
                    if (currentTurn) return 7;
                    else return 5;
                } else {
                    return 3;
                }
            }
            case 1: {
                return 1;
            }
        }
        return winScore * 2;
    }

    public int[][] getMatrixBoard() {
        int matrix[][] = new int[row][col];
        for (int i = 0; i < Buttons.length; i++) {
            for (int j = 0; j < Buttons.length; j++) {
                int value = Buttons[i][j].value;
                matrix[i][j] = value;
            }
        }
        return matrix;
    }


}


