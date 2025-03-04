/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.GameObjects;

import batalhanaval.GameObjects.Casa;
import batalhanaval.EnumerateClasses.Orientacao;
import batalhanaval.EnumerateClasses.TipoDeCasa;

/**
 *
 * @author kenji
 */
public class Jogador {
    private int id;
    private Tabuleiro tabuleiro;
    private Tabuleiro tabuleiroAdversarioView;
    private NaviosEmJogo naviosEmJogo;
    private int tipoJogador;
    
    public Jogador(int id){
        //tabuleiro = new Tabuleiro();
        //Tipo de Jogador refere-se se é player ou computador
        this.id = id;
        naviosEmJogo = new NaviosEmJogo();
        tipoJogador = 1;
        this.tabuleiro = new Tabuleiro(this);
        
        this.tabuleiroAdversarioView = new Tabuleiro(this);        
        this.tabuleiroAdversarioView.setCurrentDisplayMode(1, true);
    }

    public Tabuleiro getTabuleiroAdversarioView() {
        return tabuleiroAdversarioView;
    }
    
    public Jogador(){
        this.id = 0;
        naviosEmJogo = new NaviosEmJogo();
        tipoJogador = 0;
        this.tabuleiro = new Tabuleiro(this);
        
        this.tabuleiroAdversarioView = new Tabuleiro(this);        
        this.tabuleiroAdversarioView.setCurrentDisplayMode(1, false);
    }
    
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public NaviosEmJogo getNaviosEmJogo() {
        return naviosEmJogo;
    }

    public int getTipoJogador() {
        return tipoJogador;
    }
    
    public void clearNaviosEmJogo(){
        naviosEmJogo = new NaviosEmJogo();
    }
    
    public void setTabuleiro(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
    }
    private boolean _validPosition(int id, Orientacao orientacao, int x, int y){
        int tam = naviosEmJogo.getNavio(id).getTamanho();
        int x0 = x;
        int y0 = y;
        
        if(orientacao == Orientacao.HORIZONTAL){
            if(x0 + tam > 10){
                return false;
            }
            for(int i=0;i<tam;i++){
                if(tabuleiro.getTabuleiroCasas()[x0 + i][y0].getTipoDeCasa() != TipoDeCasa.MAR){
                    return false;
                }
            }            
        }else{
            if(y0 + tam > 10){
                return false;
            }
            for(int i=0; i<tam;i++){
                if(tabuleiro.getTabuleiroCasas()[x0][y0+i].getTipoDeCasa() != TipoDeCasa.MAR){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void addNavioToPlayer(int navio_type, Orientacao orientacao, NaviosEmJogo naviosDisponiveis){
        //Move navio da bag de disponíveis para a bag do jogador
        Navio navio_aux = naviosDisponiveis.getNavio(navio_type);
        this.getNaviosEmJogo().addNavio(navio_type, navio_aux);
        naviosDisponiveis.removeNavio(navio_type);
    }
    
    public int placeNavio(int id, Orientacao orientacao, int x, int y){
        if(_validPosition(id, orientacao, x, y)){
            tabuleiro.placeNavio(id, orientacao, x, y);
            return 1;
        }else{
            return 0;
        }
    }
}
