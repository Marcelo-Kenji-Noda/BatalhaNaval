/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author kenji
 */
public class NaviosEmJogo {
    /*
        0 - Porta Aviao
        1 - Navio Tanque
        2 - Contratorpedeiro
        3 - Submarino
    */
    private int[] naviosEmJogo;
    private int total;
    private boolean vazio;
    
    private List<PortaAviao> PortaAviao listaPortaAvioes;
    private NavioTanque listaNaviosTanque;
    private Submarino listaSubmarino;
    private ContraTorpedeiro listaContraTorpedeiro;
    
    public NaviosEmJogo(){
        this.naviosEmJogo = new int[]{0,0,0,0};
        this.total = 0;
        this.vazio = true;
    }
    
    public void derrubouNavio(int id){
        if (naviosEmJogo[id] > 0){
            naviosEmJogo[id] -= 1;
            total -=  1;
        }
        else{
            System.out.println("Não é possível derrubar o navio");
        } 
    }
    
    public boolean checkVazio(){
        return total == 0;
    }
}
