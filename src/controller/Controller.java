package controller;


import utils.InfoConfig;

import view.MainFrame;


public class Controller {
    public static void main(String[] args) {
        InfoConfig infoConfig = new InfoConfig();
        infoConfig.readFileConfig();
        new MainFrame().setVisible();
    }

}
