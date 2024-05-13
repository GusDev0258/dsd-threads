package com.udesc.dsd.view;

import com.udesc.dsd.controller.GridController;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class EscolherArquivo extends JFileChooser {
    public EscolherArquivo(GridController gridController) {
        try {
            File diretorio = new File("src/main/resources");

            super.setCurrentDirectory(diretorio);

            int retorno = super.showOpenDialog(null);

            try {
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = super.getSelectedFile();
                    gridController.setFile(arquivoSelecionado);
                }
            }catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "Arquivo de malha não encontrado" + exception.getMessage(),
                        "Erro no arquivo", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Error error) {
            System.out.println("Algo deu errado ao tentar importar o arquivo:" + error);
        }
    }
}
