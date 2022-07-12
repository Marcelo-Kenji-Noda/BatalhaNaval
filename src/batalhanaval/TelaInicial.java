/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author kenji
 */
public class TelaInicial implements Runnable{
    @Override
    public void run(){
        //JButtons
        JButton singlePlayerButton, multiPlayerButton, quitButton;
        
        //JFrame Iniciar
        final JFrame telaInicial = new JFrame("Batalha Naval Iniciar"); 
        
        //JPanel Iniciar
        JPanel buttonPanel = new JPanel();
        
        //Buttons Configs
        singlePlayerButton = new JButton("Single Player");
        multiPlayerButton = new JButton("Multi player");
        quitButton = new JButton("Sair");
               
        //Button Actions
        singlePlayerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparationPhase prepPhase = new PreparationPhase(1, 0);
                telaInicial.dispose();
                prepPhase.run();
            }  
        });
        multiPlayerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Indisponível ainda");
                System.exit(0);
            }  
        });
        quitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }  
        });
        //JPanel Buttons Configs
        buttonPanel.add(singlePlayerButton);
        buttonPanel.add(multiPlayerButton);
        buttonPanel.add(quitButton);
        
        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.setBorder(new EmptyBorder(100,100,100,100));
        
        //JFrame Configs
        telaInicial.add(buttonPanel);
        telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaInicial.setSize(new Dimension(500,500));
        telaInicial.setResizable(false);
        telaInicial.setVisible(true);
        //telaInicial.pack();
        
    }
    
}
