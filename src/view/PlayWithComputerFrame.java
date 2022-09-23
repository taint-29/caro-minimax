package view;

import minimax.NormalMinimax;
import model.CaroButton;
import model.CaroTable;
import model.PlayerOTable;
import model.PlayerXTable;
import model.Point;
import service.MinimaxEven;
import service.PlayEven;
import utils.DBUtils;
import utils.InfoConfig;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class PlayWithComputerFrame {

    public PlayWithComputerFrame() throws HeadlessException {
        try {
            NormalMinimax window = new NormalMinimax();
            window.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
