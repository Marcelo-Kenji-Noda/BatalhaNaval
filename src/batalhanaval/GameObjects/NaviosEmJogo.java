/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.GameObjects;
/**
 *
 * @author kenji
 */
import batalhanaval.GameObjects.ContraTorpedeiro;
import batalhanaval.GameObjects.PortaAviao;
import batalhanaval.GameObjects.Navio;
import batalhanaval.GameObjects.NavioTanque;
import batalhanaval.GameObjects.Submarino;
import batalhanaval.Utils.SharedJLabels;
import java.util.ArrayList;
import java.util.List;

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
    
    private List<PortaAviao> listaPortaAvioes;
    private List<ContraTorpedeiro> listaContraTorpedeiro;
    private List<NavioTanque> listaNaviosTanque;   
    private List<Submarino> listaSubmarino;    

    public NaviosEmJogo(){
        this.naviosEmJogo = new int[]{0,0,0,0};
        this.total = 0;
        this.vazio = true;
        listaPortaAvioes = new ArrayList<>();
        listaContraTorpedeiro = new ArrayList<>();
        listaNaviosTanque = new ArrayList<>();
        listaSubmarino = new ArrayList<>();
    }
    
    
    public void fillNavios(){
        /*Cria os navios*/
        for(int i=0;i<2;i++){
            listaPortaAvioes.add(new PortaAviao());
        }       
        for(int i=0;i<3;i++){
            listaContraTorpedeiro.add(new ContraTorpedeiro()); 
        }
        for(int i=0;i<4;i++){
            listaNaviosTanque.add(new NavioTanque());
        }
        for(int i=0;i<5;i++){
            listaSubmarino.add(new Submarino());
        }
                
        for(int i=0;i<4;i++){
            this.naviosEmJogo[i] = i+2;
        }        
        this.total = 10;
        this.vazio = false;
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
    
    public Navio getNavio(int id){
        switch(id){
            case 0:
                if(!listaPortaAvioes.isEmpty()){
                    return listaPortaAvioes.get(listaPortaAvioes.size()-1);
                }else{
                    System.out.println("");
                }
            case 1:
                if(!listaNaviosTanque.isEmpty()){
                    return listaNaviosTanque.get(listaNaviosTanque.size()-1);
                }else{
                    System.out.println("");
                }
            case 2:
                if(!listaContraTorpedeiro.isEmpty()){
                    return listaContraTorpedeiro.get(listaContraTorpedeiro.size()-1);
                }else{
                    System.out.println("");
                }
            case 3:
                if(!listaSubmarino.isEmpty()){
                    return listaSubmarino.get(listaSubmarino.size()-1);
                }else{
                    System.out.println("");
                }
            default:
                throw new AssertionError();
        }
    }
    
    public int getNumberOfNavios(int id){
        switch(id){
            case 0:
                return listaPortaAvioes.size();
            case 1:
                return listaContraTorpedeiro.size();
            case 2:
                return listaNaviosTanque.size();
            case 3:
                return listaSubmarino.size();
            default:
                throw new AssertionError();
        }
    }
        
    public void addNavio(int id, Navio navio){
        switch(id){
            case 0:
                if(listaPortaAvioes.size() < 2){
                    System.out.println("Adicionado navio na bag atual");
                    listaPortaAvioes.add(new PortaAviao(navio));
                    total += 1;
                }else{
                    System.out.println("Já está cheio!");
                }
                break;
            case 1:
                if(listaContraTorpedeiro.size() < 3){
                    listaContraTorpedeiro.add(new ContraTorpedeiro(navio));
                    total += 1;
                }else{
                    System.out.println("Já está cheio!");
                }
                break;
            case 2:
                if(listaNaviosTanque.size() < 4){
                    listaNaviosTanque.add(new NavioTanque(navio));
                    total += 1;
                }else{
                    System.out.println("Já está cheio!");
                }
                break;
            default:
                if(listaSubmarino.size() < 5){
                    listaSubmarino.add(new Submarino(navio));
                    total += 1;
                }else{
                    System.out.println("Já está cheio!");
                }
                break;
        }
    }
    public void removeNavio(int id){
        switch (id) {
            case 0:
                if (!listaPortaAvioes.isEmpty()){
                    listaPortaAvioes.remove(listaPortaAvioes.size()-1);
                    total -= 1;
                }else{
                    System.out.println("Lista está vazia");
                }
                break;
            case 1:
                if (!listaContraTorpedeiro.isEmpty()){
                    listaContraTorpedeiro.remove(listaContraTorpedeiro.size()-1);
                    total -= 1;
                }else{
                    System.out.println("Lista está vazia");
                }
                break;
            case 2:
                if (!listaNaviosTanque.isEmpty()){
                    listaNaviosTanque.remove(listaNaviosTanque.size()-1);
                    total -= 1;
                }else{
                    System.out.println("Lista está vazia");
                }
                break;
            case 3:
                if (!listaSubmarino.isEmpty()){
                    listaSubmarino.remove(listaSubmarino.size()-1);
                    total -= 1;
                }else{
                    System.out.println("Lista está vazia");
                }
                break;
            default:
               System.out.println("Id encaminhado: "+id);
               System.out.println("Erro ao remover");
               break;
        }
    }
    

    public int getTotal(){
        return this.total;
    }
}
