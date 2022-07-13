/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

/**
 *
 * @author kenji
 */
public class NavioTanque extends Navio{
    /*4 Quadrados*/ 
    public NavioTanque(int tamanho, Casa casaInicial, Jogador jogador, Orientacao orientacao){
        super(tamanho ,casaInicial, jogador, orientacao);
    }
    
    public NavioTanque(){
        super(4);
    }
    
    public NavioTanque(Navio navio){
        super(4 ,navio.getCasaInicial(), navio.getJogador(), navio.getOrientacao());
    }
    
    @Override
    public String toString(){
        return "Navio Tanque";
    }
    
    @Override
    public int getId(){
        return 1;
    }
}
