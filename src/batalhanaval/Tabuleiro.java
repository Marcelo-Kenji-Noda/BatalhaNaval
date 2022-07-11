/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kenji
 */
public class Tabuleiro extends JPanel{
    private Casa[][] tabuleiro;
    
    public Tabuleiro(){
        tabuleiro = new Casa[10][10];
        setLayout(new GridLayout(10, 10, 0, 0));
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                tabuleiro[i][j] = new Casa(i, j, TipoDeCasa.MAR);
                this.add(tabuleiro[i][j]);
            }
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        for(int i=0;i<10;i++){
            for(int j=0; j<10; j++){
                Casa casa = tabuleiro[i][j];
                casa.paintComponent(g);
            }
        }
    }
}
