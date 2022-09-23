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
                // Chơi thử với move hiện tại
                int[][] dummyBoard = playNextMove(board, move, false);

                Object[] tempMove = minimaxSearchAB(depth - 1, dummyBoard, !max, alpha, beta);

                // Cập nhật alpha
                if ((Double) (tempMove[0]) > alpha) {
                    alpha = (Double) (tempMove[0]);
                }
                // Cắt tỉa beta
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

                // Cập nhật beta
                if (((Double) tempMove[0]) < beta) {
                    beta = (Double) (tempMove[0]);
                }
                // Cắt tỉa alpha
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


        // Tìm những tất cả những ô trống nhưng có đánh XO liền kề
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


    // Đánh giá bàn cờ dựa trên tổng số điểm hàng ngan, hàng dọc, và 2 đường chéo
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
                    //2. Đếm...
                    consecutive++;
                }
                // gặp ô trống
                else if (boardMatrix[i][j] == 0) {
                    if (consecutive > 0) {
                        // Ra: Ô trống ở cuối sau khi đếm. Giảm block rồi bắt đầu tính điểm sau đó reset lại ban đầu
                        blocks--;
                        score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                        consecutive = 0;
                        blocks = 1;
                    } else {
                        // 1. Vào reset lại blocks = 1 rồi bắt đầu đếm
                        blocks = 1;
                    }
                }
                //gặp quân địch
                else if (consecutive > 0) {
                    // 2.Ra:  Ô bị chặn sau khi đếm. Tính điểm sau đó reset lại.
                    score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);
                    consecutive = 0;
                    blocks = 2;
                } else {
                    //1. Vào: reset lại blocks = 2 rồi bắt đầu đếm
                    blocks = 2;
                }
            }

            // 3. Ra: nhưng lúc này đang ở cuối. Nếu liên tục thì vẫn tính cho đến hết dòng
            if (consecutive > 0) {
                score += getConsecutiveSetScore(consecutive, blocks, forX == playersTurn);

            }
            // reset lại để tiếp tục chạy cho dòng tiếp theo
            consecutive = 0;
            blocks = 2;

        }
        return score;
    }

    // hàm tính toán đường dọc tương tự như đường ngan
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

    // Hàm tính toán 2 đường chéo tương tự như hàng ngan
    public static int evaluateDiagonal(int[][] boardMatrix, boolean forX, boolean playersTurn) {

        int consecutive = 0;
        int blocks = 2;
        int score = 0;
        // Đường chéo /
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
        // Đường chéo \
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
            // Ăn 5 -> Cho điểm cao nhất
            case 5: {
                return winScore;
            }
            case 4: {
                // Đang 4 -> Tuỳ theo lược và bị chặn: winGuarantee, winGuarantee/4, 200
                if (currentTurn) return winGuarantee;
                else {
                    if (blocks == 0) return winGuarantee / 4;
                    else return 200;
                }
            }
            case 3: {
                // Đang 3: Block = 0
                if (blocks == 0) {
                    // Nếu lược của currentTurn thì ăn 3 + 1 = 4 (không bị block) -> 50000 -> Khả năng thắng cao.
                    // Ngược lại không phải lược của currentTurn thì khả năng bị blocks cao
                    if (currentTurn) return 50000;
                    else return 200;
                } else {
                    // Block == 1 hoặc Blocks == 2
                    if (currentTurn) return 10;
                    else return 5;
                }
            }
            case 2: {
                // Tương tự với 2
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


