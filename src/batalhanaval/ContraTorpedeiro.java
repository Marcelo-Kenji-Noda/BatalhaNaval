/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

/**
 *
 * @author kenji
 */
public class ContraTorpedeiro extends Navio{
    /*3 Quadrados*/
    public ContraTorpedeiro(int tamanho, Casa casaInicial, Jogador jogador, Orientacao orientacao){
        super(3 ,casaInicial, jogador, orientacao);
    }
    
    public ContraTorpedeiro(){
        super(3);
    }
    
    public ContraTorpedeiro(Navio navio){
        super(3 ,navio.getCasaInicial(), navio.getJogador(), navio.getOrientacao());
    }
    
    @Override
    public String toString(){
        return "Contra Torpedeiro";
    }
    
        
    @Override
    public int getId(){
        return 2;
    }
}
