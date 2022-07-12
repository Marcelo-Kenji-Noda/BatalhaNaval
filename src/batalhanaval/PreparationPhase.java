/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author kenji
 */
public class PreparationPhase implements Runnable{
    JFrame prepPhaseJframe = new JFrame();
        
    JPanel tabuleiroPanel, fillTabuleiroPanel, naviosPanel, rotatePanel;
    JRadioButton rotateLeftButton, rotateRightButton, returnNavioButton, randomPosicaoButton, autoFillButton, clearAllButton, readyButton;
    
    private int mode;
    private Jogador jogador;
    private Tabuleiro tabuleiro;
    
    public PreparationPhase(int mode, int id_jogador) {
        this.mode = mode;
        this.jogador = new Jogador(id_jogador);
        this.tabuleiro = new Tabuleiro();
    }
    
    public void initializeNavios(){
        
    }
    @Override
    public void run(){  
        prepPhaseJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prepPhaseJframe.setSize(new Dimension(500,500));
        prepPhaseJframe.setResizable(false);
        prepPhaseJframe.setVisible(true);
    }    
}
