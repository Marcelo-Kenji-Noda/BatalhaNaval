/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.GameObjects;

import batalhanaval.GameObjects.Jogador;
import batalhanaval.GameObjects.Casa;
import batalhanaval.EnumerateClasses.Orientacao;
import batalhanaval.EnumerateClasses.TipoDeCasa;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private Casa casaAtual;
    private Casa casaSelecionada;
    private boolean isCasaSelecionada;
    private Orientacao orientacaoAtual;
    public Tabuleiro(Jogador jogador){
        this.jogador = jogador;
        this.totalMar = 100;        
        this.totalNavio = 0;
        this.totalAcerto=0;
        this.totalErro = 0;
        this.casaAtual = new Casa(0,0,TipoDeCasa.MAR, this);
        this.casaSelecionada = new Casa(0,0,TipoDeCasa.MAR, this);
        this.isCasaSelecionada = false;
        this.orientacaoAtual = Orientacao.HORIZONTAL;
        
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        tabuleiro = new Casa[10][10];
        this.setLayout(new GridLayout(10, 10));
        this.setSize(500, 500);
        for(int col=0; col<10; col++){
            for(int row=0; row<10; row++){
                tabuleiro[row][col] = new Casa(row, col, TipoDeCasa.MAR, this);
                this.add(tabuleiro[row][col]);
            }
        }
    }
    
    public Casa[][] getTabuleiroCasas(){
        return this.tabuleiro;
    }
    
    public void setCasaAtual(Casa casa){
        this.casaAtual = casa;        
    }
    
    public void setisCasaSelecionada(boolean value){
        this.isCasaSelecionada = value;
    }
    
    public void resetCasaSelecionada(){
        this.casaSelecionada = new Casa(0, 0);
    }
    public Casa getCasaSelecionada(){
        return this.casaSelecionada;
    }
    public boolean getIsCasaSelecionada(){
        return this.isCasaSelecionada;
    }
    public void setCasaSelecionada(Casa casa){
        this.casaSelecionada = casa;
    }
    
    public Orientacao getOrientacaoAtual(){
        return this.orientacaoAtual;
    }
    
    public void setOrientacaoAtual(Orientacao orientacao){
        this.orientacaoAtual = orientacao;
    }
    public void clearTabuleiro(){
        this.totalMar = 100;        
        this.totalNavio = 0;
        this.totalAcerto=0;
        this.totalErro = 0;
        
        for(int col=0; col<10; col++){
            for(int row=0; row<10; row++){
                this.tabuleiro[row][col].setTipoDeCasa(TipoDeCasa.MAR);
            }
        }
    }
    
    public boolean validatePosicao(int tam, Orientacao orientacao, int row, int col){
        //System.out.println("Tipo de Casa: " + this.tabuleiro[row][col].getTipoDeCasa());
        boolean casaDisponivel = this.tabuleiro[row][col].getTipoDeCasa() == TipoDeCasa.MAR;
        boolean tamanhoValido;
        //System.out.println("Casa Disponível: "+casaDisponivel);
        if(!casaDisponivel) return false;
        
        if(orientacao == Orientacao.HORIZONTAL){
            tamanhoValido = (row + tam) < 10 && col < 10;
            //System.out.println("Tamanho da Ultima casa: " + (row + tam));
            if(!tamanhoValido){
                //System.out.println("Tamanho inválido Horizontal: " + (tam + row));
                return false;} 
            for(int i=0;i<tam;i++){
                if(this.tabuleiro[row+i][col].getTipoDeCasa() != TipoDeCasa.MAR){
                    return false;
                }
            }
        }else{
            tamanhoValido = (col + tam) < 10 && row < 10;
            //System.out.println("Tamanho da Ultima casa: " + (col + tam));
            if(!tamanhoValido){
                //System.out.println("Tamanho inválido: " + (tam + col));
                return false;
            } 
            for (int i = 0; i < tam; i++) {
                if (this.tabuleiro[row][col+i].getTipoDeCasa() != TipoDeCasa.MAR) {
                    //System.out.println("Tamanho inválido Vertical: " + (tam + col));
                    return false;
                }
            }
        }
        
        return true;
    }
    public void placeNavio(int tam, Orientacao orientacao, int x, int y){        
        try {
            if(validatePosicao(tam,orientacao, x, y)){
                totalMar -= tam;
                totalNavio += tam;
                int x0 = x;
                int y0 = y;
                if (orientacao == Orientacao.HORIZONTAL) {
                    for (int i = 0; i < tam; i++) {
                        tabuleiro[x0 + i][y0].setTipoDeCasa(TipoDeCasa.NAVIO);
                    }
                } else {
                    for (int i = 0; i < tam; i++) {
                        tabuleiro[x0][y0 + i].setTipoDeCasa(TipoDeCasa.NAVIO);
                    }
                }
                update();
            }          
        } catch (Exception e) {
            System.out.println("Erro ao posicionar navio");
        }        
    }
    
    public void update(){
        try {
            //System.out.println("Entrou em update");
            this.paintComponent(this.getGraphics());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não conseguiu realizar update");
        }
        
    };
    
    @Override
    public void paintComponent(Graphics g){
        for(int col=0;col<10;col++){
            for(int row=0; row<10; row++){
                tabuleiro[row][col].paintComponent(g);
            }
        }
    }
}
