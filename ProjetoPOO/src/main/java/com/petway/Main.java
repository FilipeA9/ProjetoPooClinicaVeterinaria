package com.petway;

import com.petway.grafico.InterfaceGrafica;
import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceGrafica();
            }
        });
    }

}

