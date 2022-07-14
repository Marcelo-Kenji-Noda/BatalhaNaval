/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Scenes.Game;

import batalhanaval.EnumerateClasses.TipoDeCasa;
import batalhanaval.GameObjects.Casa;
import batalhanaval.GameObjects.Jogador;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.Border;
/**
 *
 * @author kenji
 */
public class Game implements Runnable{
    JFrame gameMainFrame = new JFrame("Batalha Naval");
    Jogador jogador1;
    Jogador jogador2;
    JPanel meuPanel; 
    Border jogador1TabuleiroLabel, jogador2TabuleiroLabel;
    
    int currentPlayerTurn;
    boolean gameIsActive;
    public Game(Jogador jogador1, Jogador jogador2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.meuPanel = new JPanel(new BorderLayout());
        this.gameIsActive = true;
        this.currentPlayerTurn = 1;
    }

    private boolean isValidAtaque(Casa casa,Jogador jogadorAtacado){
        int x = casa.getXPos();
        int y = casa.getYPos();
        
        return !(jogadorAtacado.getTabuleiro().getTabuleiroCasas()[x][y].getTipoDeCasa() != TipoDeCasa.MAR && jogadorAtacado.getTabuleiro().getTabuleiroCasas()[x][y].getTipoDeCasa() != TipoDeCasa.NAVIO);        
    }
    
    private TipoDeCasa getResultOfAtaque(Casa casaAtacada, Jogador jogadorAtacado){
        if (jogadorAtacado.getTabuleiro().getTabuleiroCasas()[casaAtacada.getXPos()][casaAtacada.getYPos()].getTipoDeCasa() == TipoDeCasa.MAR){
            return TipoDeCasa.ERRO;
        }else{
            return TipoDeCasa.ACERTO;
        }
        
    }
    /*Jogador 1 ataca o jogador 2*/
    private void realizaOAtaque(Jogador jogador1, Jogador jogador2, int x, int y) {
        //Seleciona a casa
        boolean stop = false;
        while(!stop){
            Casa casa = jogador1.getTabuleiroAdversarioView().getCasaSelecionada();
            if(isValidAtaque(casa,jogador2)){
            //Realiza o ataque
                TipoDeCasa resultado = getResultOfAtaque(casa, jogador2);
                jogador1.getTabuleiroAdversarioView().getTabuleiroCasas()[casa.getXPos()][casa.getYPos()].setTipoDeCasa(resultado);
                //Atualiza
                jogador1.getTabuleiroAdversarioView().update();
                stop = true;
            }
        }        
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private class ButtonPanel extends JPanel{
        private JButton atacarButton; 
        private JLabel newLabel;
        
        public ButtonPanel(){
            this.setLayout(new GridLayout(2,1));
            atacarButton = new JButton("Atacar!");
            newLabel = new JLabel("Selecione uma posição do campo adversário e clique em atacar");
            
            this.add(newLabel);
            this.add(atacarButton);
        }
    }
    public boolean isVitoria(Jogador jogador){
        return jogador.getTabuleiro().getTotalAcerto() == 44;
    }
    @Override
    public void run() {
        gameMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameMainFrame.setSize(new Dimension(1500,700));
        gameMainFrame.setResizable(false);
        gameMainFrame.setVisible(true);
        gameMainFrame.setLayout(new BorderLayout());
        jogador1TabuleiroLabel = BorderFactory.createTitledBorder("Seu campo");
        jogador1.getTabuleiro().setBorder(jogador1TabuleiroLabel);
        
        jogador1.getTabuleiro().setCurrentDisplayMode(1);
        
        jogador2TabuleiroLabel = BorderFactory.createTitledBorder("Campo adversário");
        jogador2.getTabuleiro().setBorder(jogador2TabuleiroLabel);

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jogador1.getTabuleiro(), jogador1.getTabuleiroAdversarioView());
        splitPanel.setResizeWeight(0.5);
        JSplitPane splitPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new ButtonPanel(), splitPanel);

        splitPanel2.setDividerLocation(60);
        gameMainFrame.add(splitPanel2,BorderLayout.CENTER);
        
        /*Enquanto o jogo estiver ligado*/
//        while(gameIsActive){
//            if(currentPlayerTurn == 1){
//                Casa casaSelecionada = jogador1.getTabuleiroAdversarioView().getCasaSelecionada();
//                realizaOAtaque(jogador1, jogador2,  casaSelecionada.getXPos(),  casaSelecionada.getY());
//                
//                /*Jogo desativa se avaliar vitória*/
//                gameIsActive = !isVitoria(jogador2);
//                currentPlayerTurn = 2;
//            }else{
////                Casa casaSelecionada = jogador2.getTabuleiroAdversarioView().getCasaSelecionada();
////                realizaOAtaque(jogador2, jogador1,  casaSelecionada.getXPos(),  casaSelecionada.getY());
////                
////                /*Jogo desativa se avaliar vitória*/
////                gameIsActive = !isVitoria(jogador2);
//                currentPlayerTurn = 1;
//            }
//        }
        //gameMainFrame.add(new ButtonPanel(), BorderLayout.NORTH);
    }
    
}
