package com.udesc.dsd.controller;

import java.io.File;

public class MalhaController {

    private File arquivo = null;

    public MalhaController() {
    }

    public void setNomeCaminho(File arquivo) {
        this.arquivo = arquivo;
    }
}
