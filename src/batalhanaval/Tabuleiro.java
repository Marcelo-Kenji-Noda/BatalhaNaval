/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author kenji
 */
public class Tabuleiro extends JPanel{
    private Casa[][] tabuleiro;
    private Jogador jogador;
    private int totalMar;
    private int totalNavio;
    private int totalAcerto;
    private int totalErro;

    public Tabuleiro(Jogador jogador){
        this.jogador = jogador;
        this.totalMar = 100;        
        this.totalNavio = 0;
        this.totalAcerto=0;
        this.totalErro = 0;
        //this.setSize(500,500);
        //this.pack();
        //this.setMinimumSize(new Dimension(500,500));
        
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.GREEN);
        tabuleiro = new Casa[10][10];
        this.setLayout(new GridLayout(10, 10, 0, 0));
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                tabuleiro[i][j] = new Casa(i, j, TipoDeCasa.MAR);
                this.add(tabuleiro[i][j]);
            }
        }
    }
    
    public Casa[][] getTabuleiroCasas(){
        return this.tabuleiro;
    }
    
    public void placeNavio(int tam, Orientacao orientacao, Casa casainicial){
        totalMar -= tam;
        totalNavio += tam;
        int x0 = casainicial.getXPos();
        int y0 = casainicial.getYPos();
        
        if(orientacao == Orientacao.HORIZONTAL){
            for(int i=0; i<tam; i++){
                tabuleiro[x0 + i][y0].setTipoDeCasa(TipoDeCasa.NAVIO);
            }
        }else{
            for(int i=0; i<tam; i++){
                tabuleiro[x0][y0+i].setTipoDeCasa(TipoDeCasa.NAVIO);
            }
        }

    }
    
    @Override
    public void paintComponent(Graphics g){        
        for(int i=0;i<10;i++){
            for(int j=0; j<10; j++){
                Casa casaAtual = tabuleiro[j][i];
                g.setColor(casaAtual.getColor());
                g.fillRect(i * 50, j * 50, 50,
                            50);
                g.setColor(Color.BLACK);
                g.drawRect(i * 50, j * 50, 50,
                            50);
            }
        }
    }
}
