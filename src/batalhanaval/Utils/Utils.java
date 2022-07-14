/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Utils;

import batalhanaval.EnumerateClasses.Orientacao;
import batalhanaval.GameObjects.Jogador;
import batalhanaval.GameObjects.Navio;
import batalhanaval.GameObjects.NaviosEmJogo;
import batalhanaval.GameObjects.Tabuleiro;
import java.util.Random;

/**
 *
 * @author kenji
 */
public class Utils {    
    public static void placeNaviosRandom(NaviosEmJogo naviosDisponiveis, Jogador jogador, SharedJLabels sharedLabels){
        Random gerador = new Random();
        int id = 0;
        
        Navio selectedNavio;
        
        int xCoord, yCoord;        
        
        Orientacao orientacaoSelecionada;
        
        //Verifica se tem total de PortaAvioes
        while(id < 4){
            xCoord = gerador.nextInt(10);
            yCoord = gerador.nextInt(10);
            selectedNavio = naviosDisponiveis.getNavio(id);
            orientacaoSelecionada = Orientacao.getOrientacao(gerador.nextInt(2)+1);
            
            if(jogador.getTabuleiro().validatePosicao(selectedNavio.getTamanho(), orientacaoSelecionada, xCoord, yCoord)){
                try{
                    jogador.getTabuleiro().placeNavio(selectedNavio.getTamanho(), orientacaoSelecionada, xCoord, yCoord);
                    jogador.getNaviosEmJogo().addNavio(id, selectedNavio);
                    naviosDisponiveis.removeNavio(id);
                    sharedLabels.setnnNaviosByID(id, naviosDisponiveis.getNumberOfNavios(id));
                    jogador.getTabuleiro().update();
                }
                catch(Exception e){ 
                }

            }
            if (naviosDisponiveis.getNumberOfNavios(id) == 0) {
                id += 1;
            }
        }
        sharedLabels.setPiecesLeftTotalLabelText("Total de Navios disponíveis: 0");
        
        //Verifica se tem total de NavioTanque
        //Verifica se tem total de ContraTorpedo
        //Verifica se tem total de Submarino
    }
}
