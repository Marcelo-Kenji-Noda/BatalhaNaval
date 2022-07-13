/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Scenes;

import batalhanaval.Scenes.TelaInicial;
import batalhanaval.GameObjects.Jogador;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kenji
 */
public class Game implements Runnable{
    public JFrame gameWindow;
    private Jogador currentPlayerTurn;
    private JPanel currentDisplayedPanel;
    
    private TelaInicial telaInicial;
    private FlowLayout layout;
    
    public Game(){
        layout = new FlowLayout();
        //telaInicial = new TelaInicial();
        gameWindow = new JFrame();
                //JFrame Configs 
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(new Dimension(800,800));
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
        //gameWindow.pack();
        
        //layout.setAlignment(FlowLayout.CENTER);
    }
    
    @Override
    public void run(){
        TelaInicial telainicial = new TelaInicial();
        telainicial.run();
    }
}
