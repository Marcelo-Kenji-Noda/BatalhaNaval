/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

/**
 *
 * @author kenji
 */
public class Jogador {
    private int id;
    private Tabuleiro tabuleiro;
    private NaviosEmJogo naviosEmJogo;
    private int tipoJogador;
    
    public Jogador(int id){
        //tabuleiro = new Tabuleiro();
        //Tipo de Jogador refere-se se é player ou computador
        this.id = id;
        naviosEmJogo = new NaviosEmJogo();
        tipoJogador = 1;
    }
}
