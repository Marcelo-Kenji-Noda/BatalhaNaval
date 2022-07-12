/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

/**
 *
 * @author kenji
 */
public class Navio {
    private int tamanho;
    private Casa casaInicial;
    private Jogador jogador;
    private Orientacao orientacao;

    public Navio(int tamanho, Casa casaInicial, Jogador jogador, Orientacao orientacao) {
        this.tamanho = tamanho;
        this.casaInicial = casaInicial;
        this.jogador = jogador;
        this.orientacao = orientacao;
    }
    
    public Navio(int tamanho){
        this.tamanho = tamanho;
    }
    
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setCasaInicial(Casa casaInicial) {
        this.casaInicial = casaInicial;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    
    public boolean posValida(){
        return true;
    }
}
