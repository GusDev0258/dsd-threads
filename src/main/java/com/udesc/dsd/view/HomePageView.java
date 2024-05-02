package com.udesc.dsd.view;

import com.udesc.dsd.controller.MalhaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class HomePageView {
    private JPanel panel1;
    private JButton buttonSelecionarMalha;
    private JButton buttonIniciar;
    private JFormattedTextField numeroCarros;
    private JRadioButton radioButtonSemaforo;
    private JRadioButton radioButtonMonitor;
    private JLabel nomeArquivo;

    private final MalhaController malhaController = new MalhaController();
    private File arquivo = null;
    private String msgErro;

    public HomePageView() {
        JFrame frame = new JFrame("dsd-threads");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);

        frame.setVisible(true);

        buttonSelecionarMalha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EscolherArquivo(malhaController);
                try {
                    arquivo = malhaController.getArquivo();
                    nomeArquivo.setText("Arquivo selecionado: " + arquivo.getName());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*----validacoes----*/

                msgErro = "";

                // verifica se o campo numeroCarros está vazio
                if (numeroCarros.getText().isEmpty()) {
                    msgErro += "Por favor, insira um valor para o número de carros.\n";
                }else {
                    // verifica se o valor digitado no campo numeroCarros é um número inteiro maior que zero
                    try {
                        int valor = Integer.parseInt(numeroCarros.getText());
                        if (valor <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        msgErro += "Por favor, insira um número inteiro maior que zero no campo número de carros.\n";
                    }
                }

                // verifica se pelo menos um dos JRadioButtons está selecionado
                if (!radioButtonSemaforo.isSelected() && !radioButtonMonitor.isSelected()) {
                    msgErro += "Por favor, selecione pelo menos uma opção (Semáforo ou Monitor).\n";
                }

                // verifica se um arquivo foi selecionado
                if (arquivo == null) {
                    msgErro += "Por favor, selecione um arquivo antes de iniciar.\n";
                }

                if (!msgErro.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, msgErro, "Erro(s) de validação",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                /*----fim validacoes----*/

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
