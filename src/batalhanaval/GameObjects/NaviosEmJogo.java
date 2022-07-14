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
    private int total;
    private boolean vazio;
    
    private List<PortaAviao> listaPortaAvioes;
    private List<ContraTorpedeiro> listaContraTorpedeiro;
    private List<NavioTanque> listaNaviosTanque;   
    private List<Submarino> listaSubmarino;    

    //Construtor
    public NaviosEmJogo(){
        this.total = 0;
        this.vazio = true;
        listaPortaAvioes = new ArrayList<>();
        listaContraTorpedeiro = new ArrayList<>();
        listaNaviosTanque = new ArrayList<>();
        listaSubmarino = new ArrayList<>();
    }
    
    //Enche a bag com todos os navios
    public void fillNaviosEmJogo(){
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
                 
        this.total = 14;
        this.vazio = false;
    }
      
    public boolean checkVazio(){
        return total == 0;
    }
    
    //Retorna um navio contido na bag do tipo id, caso exista
    public Navio getNavio(int id){
        switch(id){
            case 0:
                if(!listaPortaAvioes.isEmpty()){
                    return listaPortaAvioes.get(listaPortaAvioes.size()-1);
                }else{
                   // System.out.println("Não há Porta-Aviões disponíveis");
                }
            case 1:
                if(!listaNaviosTanque.isEmpty()){
                    return listaNaviosTanque.get(listaNaviosTanque.size()-1);
                }else{
                    //System.out.println("Não há Navios-Tanques disponíveis");
                }
            case 2:
                if(!listaContraTorpedeiro.isEmpty()){
                    return listaContraTorpedeiro.get(listaContraTorpedeiro.size()-1);
                }else{
                    //System.out.println("Não há Contra-Torpedeiros disponíveis");
                }
            case 3:
                if(!listaSubmarino.isEmpty()){
                    return listaSubmarino.get(listaSubmarino.size()-1);
                }else{
                    //System.out.println("Não há Submarinos disponíveis");
                }
            default:
                throw new AssertionError();
        }
    }
    
    //Retorna a quantidade de navios do tipo id
    public int getNumberOfNavios(int id){
        switch(id){
            case 0:
                return listaPortaAvioes.size();
            case 1:
                return listaContraTorpedeiro.size();
            case 2:
                return listaNaviosTanque.size();
            default:
                return listaSubmarino.size();
        }
    }
        
   //Adiciona um navio do tipo id a bag
    public void addNavio(int id, Navio navio){
        switch(id){
            case 0:
                if(listaPortaAvioes.size() < 2){
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
    
    //Remove um navio do tipo id da bag
    public void removeNavio(int id){
        switch (id) {
            case 0:
                if (!listaPortaAvioes.isEmpty()){
                    listaPortaAvioes.remove(listaPortaAvioes.size()-1);
                    total -= 1;
                }else{
                    System.out.println("Não há Porta-aviões para remover");
                }
                break;
            case 1:
                if (!listaContraTorpedeiro.isEmpty()){
                    listaContraTorpedeiro.remove(listaContraTorpedeiro.size()-1);
                    total -= 1;
                }else{
                    System.out.println("Não há Contra-Torpedeiros para remover");
                }
                break;
            case 2:
                if (!listaNaviosTanque.isEmpty()){
                    listaNaviosTanque.remove(listaNaviosTanque.size()-1);
                    total -= 1;
                }else{
                    System.out.println("Não há Navios-Tanques para remover");
                }
                break;
            case 3:
                if (!listaSubmarino.isEmpty()){
                    listaSubmarino.remove(listaSubmarino.size()-1);
                    total -= 1;
                }else{
                    System.out.println("Não há Submarinos para remover");
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
