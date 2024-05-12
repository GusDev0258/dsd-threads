package com.udesc.dsd.view;

import com.udesc.dsd.controller.GridController;
import com.udesc.dsd.model.SimulationSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class HomePageView {
    private final GridController gridController = new GridController();
    private JPanel panel1;
    private JButton buttonSelecionarMalha;
    private JButton buttonIniciar;
    private JFormattedTextField numeroCarros;
    private JRadioButton radioButtonSemaforo;
    private JRadioButton radioButtonMonitor;
    private JLabel nomeArquivo;
    private JButton finishSimulationButton;
    private JButton forceFinishSimulationButton;
    private JTextField intervalField;
    private File arquivo = null;
    private String msgErro;

    private SimulationSettings settings = SimulationSettings.getInstance();
    private GridView malhaView;

    public HomePageView() {
        JFrame frame = new JFrame("dsd-threads");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);

        frame.setVisible(true);
        finishSimulationButton.addActionListener(e -> {
            if (settings.isSimulationRunning()) {
                settings.stopSimulation();
            }
        });
        forceFinishSimulationButton.addActionListener(e -> {
                gridController.shutDownSimulation();
        });
        buttonSelecionarMalha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EscolherArquivo(gridController);
                try {
                    arquivo = gridController.getFile();
                    nomeArquivo.setText("Arquivo selecionado: " + arquivo.getName());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldsValidation()) startController();
            }
        });
    }


    private void startController() {
        settings.carInsertionDelay(Long.parseLong(intervalField.getText()));
        gridController.setCarInsertDelay(settings.getCarsPerSecond());
        if (gridController.getGrid().getGridMap() != null) {
            settings.startSimulation();
            this.malhaView = new GridView(gridController);
            this.malhaView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível carregar a malha", "Erro na malha",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean fieldsValidation() {
        /*----validacoes----*/

        msgErro = "";

        // verifica se o campo numeroCarros está vazio
        if (numeroCarros.getText().isEmpty()) {
            msgErro += "Por favor, insira um valor para o número de carros.\n";
            return false;
        } else {
            // verifica se o valor digitado no campo numeroCarros é um número inteiro maior que zero
            try {
                int valor = Integer.parseInt(numeroCarros.getText());
                if (valor <= 0) {
                    throw new NumberFormatException();
                }
                settings.setSimulationCarQuantity(valor);
                return true;
            } catch (NumberFormatException ex) {
                msgErro += "Por favor, insira um número inteiro maior que zero no campo número de carros.\n";
            }
        }

        // verifica se pelo menos um dos JRadioButtons está selecionado
        if (!radioButtonSemaforo.isSelected() && !radioButtonMonitor.isSelected()) {
            msgErro += "Por favor, selecione pelo menos uma opção (Semáforo ou Monitor).\n";
            return false;
        }
        if (radioButtonSemaforo.isSelected()) {
            settings.setSimulationMode(SimulationSettings.SIMULATION_MODE_SEMAPHORE);
        } else {
            settings.setSimulationMode(SimulationSettings.SIMULATION_MODE_MONITOR);
        }

        // verifica se um arquivo foi selecionado
        if (arquivo == null) {
            msgErro += "Por favor, selecione um arquivo antes de iniciar.\n";
            return false;
        }

        if (!msgErro.isEmpty()) {
            JOptionPane.showMessageDialog(null, msgErro, "Erro(s) de validação",
                    JOptionPane.WARNING_MESSAGE);
            return true;
        }

        /*----fim validacoes----*/
        return true;
    }
}
