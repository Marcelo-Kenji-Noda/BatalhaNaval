/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Scenes.Game;

import batalhanaval.EnumerateClasses.Orientacao;
import batalhanaval.EnumerateClasses.TipoDeCasa;
import batalhanaval.GameObjects.Casa;
import batalhanaval.GameObjects.Jogador;
import batalhanaval.Scenes.EndGame.EndGame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Border jogador1TabuleiroLabel, jogadorAdversarioTabuleiroLabel;
    
    int currentPlayerTurn;
    boolean gameIsActive;
    
    //Constructor
    public Game(Jogador jogador1, Jogador jogador2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.meuPanel = new JPanel(new BorderLayout());
        this.gameIsActive = true;
        this.currentPlayerTurn = 1;
    }
    
    //ButtonPanel
    private class ButtonPanel extends JPanel{
            private JButton atacarButton; 
            private JLabel newLabel;

            public ButtonPanel(){
                this.setLayout(new GridLayout(2,1));
                atacarButton = new JButton("Atacar!");
                newLabel = new JLabel("Selecione uma posição do campo adversário e clique em atacar");

                this.add(newLabel);
                this.add(atacarButton);
                
                atacarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    realizaOAtaque(jogador1, jogador2, currentPlayerTurn);
                }
            });
            }
        }
    
    private boolean isValidAtaque(Casa casa,Jogador jogadorAtacando){
        int x = casa.getXPos();
        int y = casa.getYPos();        
        return (jogadorAtacando.getTabuleiroAdversarioView().getTabuleiroCasas()[x][y].getTipoDeCasa() == TipoDeCasa.MAR);        
    }
    
    private TipoDeCasa getResultOfAtaque(Casa casaAtacada, Jogador jogadorAtacado){
        if (jogadorAtacado.getTabuleiro().getTabuleiroCasas()[casaAtacada.getXPos()][casaAtacada.getYPos()].getTipoDeCasa() == TipoDeCasa.MAR){
            return TipoDeCasa.ERRO;
        }else{
            return TipoDeCasa.ACERTO;
        }
        
    }
    /*Jogador 1 ataca o jogador 2*/
    private int realizaOAtaque(Jogador jogador1, Jogador jogador2, int id_atacante) {
        //Seleciona a casa
        Casa casa = jogador1.getTabuleiroAdversarioView().getCasaSelecionada();
        if(isValidAtaque(casa,jogador1)){
        //Realiza o ataque
            TipoDeCasa resultado = getResultOfAtaque(casa, jogador2);
            jogador1.getTabuleiroAdversarioView().getTabuleiroCasas()[casa.getXPos()][casa.getYPos()].setTipoDeCasa(resultado);
            //Atualiza
            jogador1.getTabuleiroAdversarioView().updatePaint();
            if(jogador1.getTabuleiroAdversarioView().getTotalAcerto() == 40){
                EndGame endgamePanel = new EndGame(1);                
                gameMainFrame.dispose();
                endgamePanel.run();
                return 0;
            }
            botRandomPlay(jogador2, jogador1,id_atacante);         
            return id_atacante;
        }else{
            System.out.println("Casa Invalida");
            this.currentPlayerTurn = 1;
            return 0;
        }      
    }
    
    public void botRandomPlay(Jogador jogador_atacando, Jogador jogador_atacado,int id_atacante){       
        Random gerador = new Random();
        int xaux, yaux;
        this.currentPlayerTurn = 2;
        while(true){                        
            xaux= gerador.nextInt(10);
            yaux= gerador.nextInt(10);
            Casa casa = new Casa(xaux, yaux);
            
            if (isValidAtaque(casa, jogador_atacando)) {
            //Realiza o ataque
            TipoDeCasa resultado = getResultOfAtaque(casa, jogador_atacado);
            System.out.println("Casa atacada: ("+xaux+", "+yaux+")");
            System.out.println("Resultado: "+resultado+"");
            jogador_atacando.getTabuleiroAdversarioView().getTabuleiroCasas()[casa.getXPos()][casa.getYPos()].setTipoDeCasa(resultado);
                        
            jogador_atacado.getTabuleiro().getTabuleiroCasas()[casa.getXPos()][casa.getYPos()].setTipoDeCasa(resultado);
            jogador_atacado.getTabuleiro().updatePaint();
            if(jogador_atacando.getTabuleiroAdversarioView().getTotalAcerto() == 40){
                EndGame endgamePanel = new EndGame(2);
                gameMainFrame.dispose();
                endgamePanel.run();
            }
            this.currentPlayerTurn = 1;
            break;
            }
        }
        
    };
    public boolean isVitoria(Jogador jogador){
        return jogador.getTabuleiroAdversarioView().getTotalAcerto() == 40;
    }
    
    @Override
    public void run() {
        //Frame
        gameMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameMainFrame.setSize(new Dimension(1500,700));
        gameMainFrame.setResizable(false);
        gameMainFrame.setVisible(true);
        gameMainFrame.setLayout(new BorderLayout());
        
        //Cria título para o tabuleiro
        jogador1TabuleiroLabel = BorderFactory.createTitledBorder("Seu Tabuleiro");
        jogador1.getTabuleiro().setBorder(jogador1TabuleiroLabel);        
        
        jogadorAdversarioTabuleiroLabel  = BorderFactory.createTitledBorder("Tabuleiro Adversário");
        jogador1.getTabuleiroAdversarioView().setBorder(jogadorAdversarioTabuleiroLabel);
        
        jogador1.getTabuleiro().setCurrentDisplayMode(2, true);

        //Divide os paineis
        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jogador1.getTabuleiro(), jogador1.getTabuleiroAdversarioView());
        splitPanel.setResizeWeight(0.5);
        JSplitPane splitPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new ButtonPanel(), splitPanel);
        splitPanel2.setDividerLocation(60);
        
        //Adiciona os paineis aos frames
        gameMainFrame.add(splitPanel2,BorderLayout.CENTER);                
    }   
}
