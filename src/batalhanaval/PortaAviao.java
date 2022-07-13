/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

/**
 *
 * @author kenji
 */
public class PortaAviao extends Navio{
    /*5 Quadrados*/
    public PortaAviao(int tamanho, Casa casaInicial, Jogador jogador, Orientacao orientacao){
        super(5 ,casaInicial, jogador, orientacao);
    }
    public PortaAviao(){
        super(5);
    }
    public PortaAviao(Navio navio){
        super(5 ,navio.getCasaInicial(), navio.getJogador(), navio.getOrientacao());
    }
    
    @Override
    public String toString(){
        return "Porta Avião";
    }
    
    @Override
    public int getId(){
        return 0;
    }
}
