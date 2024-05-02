package com.udesc.dsd.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MalhaController {

    private File arquivo = null;

    private int[][] malha;

    public MalhaController() {
    }

    public void setNomeCaminho(File arquivo) {
        this.arquivo = arquivo;
    }

    public File getArquivo() throws FileNotFoundException {
        if (!this.arquivo.exists()) {
            throw new FileNotFoundException("Arquivo não definido");
        }
        return this.arquivo;
    }

   public void loadMalha() throws FileNotFoundException{
       Scanner scanner = new Scanner(this.arquivo);
       int linhas = scanner.nextInt();
       int colunas = scanner.nextInt();

       malha = new int[linhas][colunas];

       for (int i = 0; i < linhas; i++) {
           for (int j = 0; j < colunas; j++) {
               if(scanner.hasNextInt()) {
                   malha[i][j] = scanner.nextInt();
               }
           }
       }
       scanner.close();
   }

   public int[][] getMalha() {
        if(this.malha != null) {
            return this.malha;
        }
        return null;
   }
}