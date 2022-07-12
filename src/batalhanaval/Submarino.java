/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

/**
 *
 * @author kenji
 */
public class Submarino extends Navio{
    /*2 Quadrados*/
    public Submarino(int tamanho, Casa casaInicial, Jogador jogador, Orientacao orientacao){
        super(2 ,casaInicial, jogador, orientacao);
    }
    public Submarino(){
        super(2);
    }
    public Submarino(Navio navio){
        super(2 ,navio.getCasaInicial(), navio.getJogador(), navio.getOrientacao());
    }
    
    @Override
    public String toString(){
        return "Submarino";
    }
}
