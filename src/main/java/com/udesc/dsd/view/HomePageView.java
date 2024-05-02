package com.udesc.dsd.view;

import javax.swing.*;

public class HomePageView {
    private JPanel panel1;
    private JButton buttonSelecionarMalha;
    private JButton buttonIniciar;
    private JFormattedTextField numeroCarros;
    private JRadioButton radioButtonSemaforo;
    private JRadioButton radioButtonMonitor;
    private JButton testeButton;

    public HomePageView() {
        JFrame frame = new JFrame("dsd-threads");
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);
        frame.setVisible(true);
    }
}
