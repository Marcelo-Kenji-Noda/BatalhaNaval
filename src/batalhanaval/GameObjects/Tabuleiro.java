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
    
    private Casa casaMouseOver;
    
    private Casa casaSelecionada;
    private Orientacao orientacaoAtual;
    private int currentDisplayMode; // Se 0, então estamos na fase de preparação, se 1 estamos na fase de jogo;
    
    //Construtor
    public Tabuleiro(Jogador jogador){
        this.jogador = jogador;
        this.totalMar = 100;        
        this.totalNavio = 0;
        this.totalAcerto = 0;
        this.totalErro = 0;
        this.casaMouseOver = new Casa(0,0,TipoDeCasa.MAR, this);
        this.casaSelecionada = new Casa(0,0,TipoDeCasa.MAR, this);
        this.orientacaoAtual = Orientacao.HORIZONTAL;
        this.currentDisplayMode = 0;
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        tabuleiro = new Casa[10][10];
        this.setLayout(new GridLayout(10, 10));
        this.setSize(500, 500);
        
        //Cria o tabuleiro
        for(int col=0; col<10; col++){
            for(int row=0; row<10; row++){
                tabuleiro[row][col] = new Casa(row, col, TipoDeCasa.MAR, this);
                this.add(tabuleiro[row][col]);
            }
        }
    }

    //Setters
    public void setCurrentDisplayMode(int currentDisplayMode) {
        this.currentDisplayMode = currentDisplayMode;
        
        for(int row =0 ; row<10;row++){
            for(int col=0;col<10;col++){
                tabuleiro[row][col].setCurrentDisplayMode(currentDisplayMode);
                tabuleiro[row][col].repaint();
            }
        }
    }
    public void setCasaMouseOver(Casa casa){
        this.casaMouseOver = casa;        
    }
    public void setCasaSelecionada(Casa casa) {
        this.casaSelecionada = casa;
    }
    public void setOrientacaoAtual(Orientacao orientacao) {
        this.orientacaoAtual = orientacao;
    }
    
    //Getters
    public int getTotalAcerto() {
        return totalAcerto;
    }
    public Casa[][] getTabuleiroCasas(){
        return this.tabuleiro;
    }
    public Casa getCasaSelecionada(){
        return this.casaSelecionada;
    }
    public Orientacao getOrientacaoAtual(){
        return this.orientacaoAtual;
    }
    public int getCurrentDisplayMode() {
        return currentDisplayMode;
    }
    public void resetCasaSelecionada(){
        this.casaSelecionada = new Casa(0, 0);
    }
    
    //Limpa o tabuleiro
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
    
    //Verifica se o navio de tamanho tam, orientacao horizontal na posicao [row, col] pode ser posicionado
    public boolean validatePosicao(int tam, Orientacao orientacao, int row, int col){
        //System.out.println("Tipo de Casa: " + this.tabuleiro[row][col].getTipoDeCasa());
        boolean casaDisponivel = this.tabuleiro[row][col].getTipoDeCasa() == TipoDeCasa.MAR;
        boolean tamanhoValido;
        //System.out.println("Casa Disponível: "+casaDisponivel);
        if(!casaDisponivel) return false;
        
        if(orientacao == Orientacao.HORIZONTAL){
            tamanhoValido = (row + tam-1) < 10 && col < 10;
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
            tamanhoValido = (col + tam-1) < 10 && row < 10;
            //System.out.println("Tamanho da Ultima casa: " + (col + tam));
            if(!tamanhoValido){                
                return false;
            } 
            for (int i = 0; i < tam; i++) {
                if (this.tabuleiro[row][col+i].getTipoDeCasa() != TipoDeCasa.MAR) {                    
                    return false;
                }
            }
        }
        
        return true;
    }
    
    //Posiciona o navio de tamanho tam, orientacao e casa inicial [x,y]
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
                updatePaint();
            }          
        } catch (Exception e) {
            System.out.println("Erro ao posicionar navio");
        }        
    }
        //Posiciona o navio de tamanho tam, orientacao e casa inicial [x,y]
    public void placeNavioBot(int tam, Orientacao orientacao, int x, int y){        
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
            }          
        } catch (Exception e) {
            System.out.println("Erro ao posicionar navio");
        }        
    }    
    public void updatePaint(){
        try {
            //System.out.println("Entrou em updatePaint");
            this.paintComponent(this.getGraphics());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não conseguiu realizar update");
        }
        
    };
    @Override
    public void paintComponent(Graphics g){
        //Pinta cada casa individualmente
        for(int col=0;col<10;col++){
            for(int row=0; row<10; row++){
                tabuleiro[row][col].paintComponent(g);
            }
        }
    }
}
