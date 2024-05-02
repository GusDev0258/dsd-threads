package com.udesc.dsd.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView {
    private JPanel panel1;
    private JButton buttonSelecionarMalha;
    private JButton buttonIniciar;
    private JFormattedTextField numeroCarros;
    private JRadioButton radioButtonSemaforo;
    private JRadioButton radioButtonMonitor;

    public HomePageView() {
        JFrame frame = new JFrame("dsd-threads");
        frame.setSize(190, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);

        frame.setVisible(true);

        buttonSelecionarMalha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EscolherArquivo();
            }
        });
    }
}
