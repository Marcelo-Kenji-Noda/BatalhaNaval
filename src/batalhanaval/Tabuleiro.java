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
        for(int col=0; col<10; col++){
            for(int row=0; row<10; row++){
                tabuleiro[row][col] = new Casa(row, col, TipoDeCasa.MAR);
                this.add(tabuleiro[row][col]);
            }
        }
    }
    
    public Casa[][] getTabuleiroCasas(){
        return this.tabuleiro;
    }
    public boolean validatePosicao(int tam, Orientacao orientacao, int row, int col){
        boolean casaDisponivel = this.tabuleiro[row][col].getTipoDeCasa() == TipoDeCasa.MAR;
        boolean tamanhoValido;
        if(!casaDisponivel) return false;
        
        if(orientacao == Orientacao.HORIZONTAL){
            tamanhoValido = (col + tam) <= 10;
            if(!tamanhoValido) return false;
            for(int i=0;i<10;i++){
                if(this.tabuleiro[row][col+i].getTipoDeCasa() != TipoDeCasa.MAR){
                    return false;
                }
            }
        }else{
            for (int i = 0; i < 10; i++) {
                if (this.tabuleiro[row+i][col].getTipoDeCasa() != TipoDeCasa.MAR) {
                    return false;
                }
            }
            tamanhoValido = (row + tam) <= 10;
            if(!tamanhoValido) return false;
        }
        
        return true;
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
        paintComponent(this.getGraphics());
    }
    
    public void update(){
        this.paintComponent(this.getGraphics());
    };
    
    @Override
    public void paintComponent(Graphics g){        
        for(int col=0;col<10;col++){
            for(int row=0; row<10; row++){
                Casa casaAtual = tabuleiro[row][col];
                g.setColor(casaAtual.getColor());
                g.fillRect(row * 50, col * 50, 50,
                            50);
                g.setColor(Color.BLACK);
                g.drawRect(row * 50, col * 50, 50,
                            50);
            }
        }
    }
}
