/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package batalhanaval.EnumerateClasses;

/**
 *
 * @author kenji
 */
public enum Orientacao {
    HORIZONTAL(1), VERTICAL(2);
    
    private final int valor;
    
    private Orientacao(int valorOrientacao){
        valor = valorOrientacao;
    }
    
    public int getValor(){
        return valor;
    }
}
