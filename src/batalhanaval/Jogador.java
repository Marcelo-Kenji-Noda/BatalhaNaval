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
        this.tabuleiro = new Tabuleiro(this);
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
    
    private boolean _validPosition(int id, Orientacao orientacao, Casa casaInicial){
        int tam = naviosEmJogo.getNavio(id).getTamanho();
        int x0 = casaInicial.getXPos();
        int y0 = casaInicial.getYPos();
        
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
    
    public int placeNavio(int id, Orientacao orientacao, Casa casaInicial){
        if(_validPosition(id, orientacao, casaInicial)){
            tabuleiro.placeNavio(id, orientacao, casaInicial);
            return 1;
        }else{
            return 0;
        }
    }
}
