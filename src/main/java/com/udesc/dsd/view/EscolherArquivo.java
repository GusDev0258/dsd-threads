package com.udesc.dsd.view;

import com.udesc.dsd.controller.MalhaController;

import javax.swing.*;
import java.io.File;

public class EscolherArquivo extends JFileChooser {

    private MalhaController malhaController = new MalhaController();

    public EscolherArquivo() {
        try {
            File diretorio = new File(System.getProperty("user.dir"));

            super.setCurrentDirectory(diretorio);

            int retorno = super.showOpenDialog(null);

            if (retorno == JFileChooser.APPROVE_OPTION) {
                File arquivoSelecionado = super.getSelectedFile();
                malhaController.setNomeCaminho(arquivoSelecionado);
            }
        } catch(Error err) {
            System.out.println("Algo deu errado ao tentar importar o arquivo:" + err);
        }
    }
}
