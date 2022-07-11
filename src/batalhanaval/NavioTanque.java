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
    int TAMANHO = 4;
    
    public NavioTanque(int tamanho, Casa casaInicial, Jogador jogador, Orientacao orientacao){
        super(4 ,casaInicial, jogador, orientacao);
    }
}
