/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Scenes.Game;

import batalhanaval.GameObjects.Jogador;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 *
 * @author kenji
 */
public class Game implements Runnable{
    JFrame gameMainFrame = new JFrame();
    Jogador jogador1;
    Jogador jogador2;
    
    public Game(Jogador jogador1, Jogador jogador2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }
    @Override
    public void run() {
        gameMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameMainFrame.setSize(new Dimension(1500,700));
        gameMainFrame.setResizable(false);
        gameMainFrame.setVisible(true);
        //gameMainFrame.setLayout();     
        
        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jogador1.getTabuleiro(), jogador2.getTabuleiro());
        splitPanel.setDividerLocation(750);
        gameMainFrame.add(splitPanel);
    }
    
}
