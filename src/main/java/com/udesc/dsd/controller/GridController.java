package com.udesc.dsd.controller;

import com.udesc.dsd.model.Grid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridController {

    private File file = null;

    private Grid grid;
    private int[][] gridMap;

    public GridController() {
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() throws FileNotFoundException {
        if (!this.file.exists()) {
            throw new FileNotFoundException("Arquivo não definido");
        }
        return this.file;
    }

   public void loadGrid() throws FileNotFoundException{
       Scanner scanner = new Scanner(this.file);
       int linhas = scanner.nextInt();
       int colunas = scanner.nextInt();

       gridMap = new int[linhas][colunas];

       for (int i = 0; i < linhas; i++) {
           for (int j = 0; j < colunas; j++) {
               if(scanner.hasNextInt()) {
                   gridMap[i][j] = scanner.nextInt();
               }
           }
       }
       scanner.close();
   }

   public int[][] getGrid() {
        if(this.grid != null) {
            return this.gridMap;
        }
        return null;
   }
}