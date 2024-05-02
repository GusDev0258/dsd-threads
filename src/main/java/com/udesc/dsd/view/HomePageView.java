package com.udesc.dsd.view;

import com.udesc.dsd.controller.MalhaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class HomePageView {
    private JPanel panel1;
    private JButton buttonSelecionarMalha;
    private JButton buttonIniciar;
    private JFormattedTextField numeroCarros;
    private JRadioButton radioButtonSemaforo;
    private JRadioButton radioButtonMonitor;

    private final MalhaController malhaController = new MalhaController();

    public HomePageView() {
        JFrame frame = new JFrame("dsd-threads");
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);

        frame.setVisible(true);

        buttonSelecionarMalha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EscolherArquivo(malhaController);
            }
        });

        buttonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    malhaController.loadMalha();
                    int[][] malha = malhaController.getMalha();
                    if (malha != null) {
                        MalhaView malhaView = new MalhaView(malha);
                        malhaView.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Não foi possível carregar a malha", "Erro na malha",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(frame, "Arquivo de malha não encontrado" + exception.getMessage(),
                            "Erro no arquivo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
