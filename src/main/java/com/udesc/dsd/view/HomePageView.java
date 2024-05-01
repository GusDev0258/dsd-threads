package com.udesc.dsd.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView {
    private JPanel panel1;
    private JButton testeButton;

    public HomePageView() {
        JFrame frame = new JFrame("dsd-threads");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);
        frame.setVisible(true);

        testeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botão pressionado!");
            }
        });
    }
}
