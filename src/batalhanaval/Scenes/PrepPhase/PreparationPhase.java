/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Scenes.PrepPhase;

import batalhanaval.EnumerateClasses.Orientacao;
import batalhanaval.Utils.SharedJLabels;
import batalhanaval.GameObjects.Jogador;
import batalhanaval.GameObjects.Casa;
import batalhanaval.GameObjects.Navio;
import batalhanaval.GameObjects.NaviosEmJogo;
import batalhanaval.GameObjects.Tabuleiro;
import batalhanaval.Scenes.Game.Game;
import batalhanaval.Utils.Utils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author kenji
 */
public class PreparationPhase implements Runnable{
    JFrame prepPhaseJframe = new JFrame();
    //JPanel mainPrepPhasePanel = new JPanel();
    //JPanel tabuleiroPanel = new JPanel();
    
    private int mode;
    private Jogador jogador;
    private NaviosEmJogo naviosDisponiveis;
    private Navio selectedNavio;
    
    private Orientacao orientacaoSelecionada;
    private int xCoord, yCoord;
    private Casa selectedCasa;
    
    public PreparationPhase(int mode, int id_jogador) {
        this.mode = mode;
        this.jogador = new Jogador(id_jogador);
        this.naviosDisponiveis = new NaviosEmJogo();
        this.orientacaoSelecionada = Orientacao.HORIZONTAL;
        this.xCoord = 0;
        this.yCoord = 0;
        this.selectedCasa = new Casa(xCoord,yCoord);
        
        //tabuleiroPanel.setLayout(new BoxLayout(tabuleiroPanel, BoxLayout.PAGE_AXIS));
        //prepPhaseJframe.add(mainPrepPhasePanel);
        this.naviosDisponiveis.fillNavios();
        selectedNavio = naviosDisponiveis.getNavio(0);
    }
    
    public void setNavio(int navio_type, Casa casa, Orientacao orientacao){
        Navio navio_aux = naviosDisponiveis.getNavio(navio_type);
        jogador.getNaviosEmJogo().addNavio(navio_type, navio_aux);
        naviosDisponiveis.removeNavio(navio_type);
    }
    
    /*Painel com os textos*/
    private class TextPanel extends JPanel{
        private JLabel title = new JLabel("Fase de preparação");
        private JLabel selectedNavioLabel, piecesLeftTotalLabel;
        private int npiecesLeft;
        
        private int nPortaAviao, nNavioTanque, nContraTorpedeiro, nSubmarino;
        private JLabel nPortaAviaoLabel, nNavioTanqueLabel, nContraTorpedeiroLabel, nSubmarinoLabel;
        
        public JLabel getLabels(int id){
            switch (id) {
                case 0:
                    return nPortaAviaoLabel;
                case 1:
                    return nNavioTanqueLabel;
                case 2:
                    return nContraTorpedeiroLabel;
                default:
                    return nSubmarinoLabel;
            } 
        };
        
        public JLabel getCurrentJLabel(){
            return selectedNavioLabel;
        }
        
        public TextPanel(SharedJLabels sharedLabels){
            npiecesLeft = naviosDisponiveis.getTotal();
            
            nPortaAviao = naviosDisponiveis.getNumberOfNavios(0);
            nNavioTanque = naviosDisponiveis.getNumberOfNavios(1);
            nContraTorpedeiro = naviosDisponiveis.getNumberOfNavios(2);
            nSubmarino = naviosDisponiveis.getNumberOfNavios(3);
            
            /*Set Navio Label*/
            /*
            selectedNavioLabel = new JLabel("Avião Selecionado: " + selectedNavio.toString());
            selectedNavioLabel.setFont(new Font("Verdana", Font.BOLD, 14));
            piecesLeftTotalLabel = new JLabel("Total de Navios disponíveis: "+npiecesLeft);
            nPortaAviaoLabel = new JLabel("Número de Porta aviões: "+ nPortaAviao);
            nNavioTanqueLabel = new JLabel("Número de Navios Tanque: "+nNavioTanque);
            nContraTorpedeiroLabel = new JLabel("Número de Contra-torpedeiros: " + nContraTorpedeiro);
            nSubmarinoLabel = new JLabel("Número de Submarinos: "+ nSubmarino);
            */
            
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            

            //this.add(title);
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            this.add(sharedLabels.getSelectedNavioLabel(), c);
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 1;
            this.add(sharedLabels.getPiecesLeftTotalLabel(),c);
            c.gridx = 1;
            c.gridy = 1;
            c.gridwidth = 1;
            this.add(sharedLabels.getorientacaoLabel(), c);
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 1;
            this.add(sharedLabels.getnPortaAviaoLabel(),c);
            c.gridx = 1;
            c.gridy = 2;
            this.add(sharedLabels.getnNavioTanqueLabel(),c);
            c.gridx = 0;
            c.gridy = 3;
            this.add(sharedLabels.getnContraTorpedeiroLabel(),c);
            c.gridx = 1;
            c.gridy = 3;
            this.add(sharedLabels.getnSubmarinoLabel(),c);

            this.setVisible(true);
            //this.setPreferredSize(new Dimension(100,40));
        }
    };
    
    /*Painel com botões*/
    private class ButtonPanel extends JPanel{
        JButton orientationHButton, orientationVButton, returnNavioButton, randomPlacementButton, clearBoardButton, posicionarButton, iniciarJogoButton;
        JTextField XcoordSpinner, YcoordSpinner;
        JLabel XcoordSpinnerLabel, YcoordSpinnerLabel;
        public ButtonPanel(SharedJLabels sharedLabels){
            this.setLayout(new GridLayout(2,6));
            orientationHButton = new JButton("Horizontal");
            orientationVButton = new JButton("Vertical");
            
            returnNavioButton = new JButton("Retornar Navio");
            randomPlacementButton = new JButton("Posicionar aleatoriamente");
            clearBoardButton = new JButton("Limpar tabuleiro");
            XcoordSpinner = new JTextField("");
            YcoordSpinner = new JTextField("");
            iniciarJogoButton = new JButton("Iniciar Jogo");
            
            XcoordSpinnerLabel = new JLabel("Coordenada X");
            XcoordSpinnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            YcoordSpinnerLabel = new JLabel("Coordenada Y");
            YcoordSpinnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            posicionarButton = new JButton("Posicionar Navio");

            this.add(orientationHButton);
            this.add(orientationVButton);
            
            this.add(posicionarButton);
            //this.add(returnNavioButton);
            this.add(randomPlacementButton);
            this.add(clearBoardButton);
            
            this.add(XcoordSpinnerLabel);                        
            this.add(XcoordSpinner);
            this.add(YcoordSpinnerLabel);
            this.add(YcoordSpinner);
            this.add(iniciarJogoButton);
            
            orientationHButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    jogador.getTabuleiro().setOrientacaoAtual(Orientacao.HORIZONTAL);
                    orientacaoSelecionada = Orientacao.HORIZONTAL;
                    sharedLabels.setOrientacaoLabel("Orientação atual: Horizontal");
                }  
            });
            
            iniciarJogoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    Game newGame = new Game(jogador, new Jogador());
                    prepPhaseJframe.dispose();
                    newGame.run();
                }  
            });
            
            orientationVButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    orientacaoSelecionada = Orientacao.VERTICAL;
                    jogador.getTabuleiro().setOrientacaoAtual(Orientacao.VERTICAL);
                    sharedLabels.setOrientacaoLabel("Orientação atual: Vertical");
                }
            });
            clearBoardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogador.getTabuleiro().clearTabuleiro();
                    jogador.clearNaviosEmJogo();
                    naviosDisponiveis = new NaviosEmJogo();
                    naviosDisponiveis.fillNavios();
                    sharedLabels.updateLabel(naviosDisponiveis);
                    jogador.getTabuleiro().update();
                }
            });
            
            randomPlacementButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Utils.placeNaviosRandom(naviosDisponiveis, jogador, sharedLabels);
                }
            });
            
            XcoordSpinner.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (((c < '0') || (c > '9'))) {
                        e.consume();
                    }
                    try{
                    //XcoordSpinner.setText("");
                    //XcoordSpinner.setText(String.valueOf(c));
                    jogador.getTabuleiro().getTabuleiroCasas()[xCoord][yCoord].setCurrentSelected(false);
                    xCoord = Integer.parseInt(String.valueOf(c));
                    jogador.getTabuleiro().getTabuleiroCasas()[xCoord][yCoord].setCurrentSelected(true);
                    jogador.getTabuleiro().update();
                    //System.out.println("Coord X:" + xCoord);
                   }catch(Exception er){
                    //System.out.println("Erro em x");
                    xCoord = 0; 
                   }                                        
                }
            });
            
            YcoordSpinner.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (((c < '0') || (c > '9')) && ((c != KeyEvent.VK_BACK_SPACE))) {
                        e.consume();
                    }
                    try{
                    jogador.getTabuleiro().getTabuleiroCasas()[xCoord][yCoord].setCurrentSelected(false);
                    yCoord = Integer.parseInt(String.valueOf(c));
                    jogador.getTabuleiro().getTabuleiroCasas()[xCoord][yCoord].setCurrentSelected(true);
                    jogador.getTabuleiro().update();
                    //System.out.println("Coord Y:" + yCoord);
                   }catch(Exception er){
                    //System.out.println("Erro em Y");
                    yCoord = 0; 
                   }                                        
                }
            });
            
            posicionarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ID NAVIO: " +selectedNavio+"\nNAVIOS DISPONIVEIS: "+ naviosDisponiveis.getNumberOfNavios(selectedNavio.getId())
                +"\nCasa X: "+jogador.getTabuleiro().getCasaSelecionada().getXPos()+" Casa Y: "+jogador.getTabuleiro().getCasaSelecionada().getYPos()+"\nOrientação: "+orientacaoSelecionada+"\n\n");
                int selectedNavioid = selectedNavio.getId();
                //System.out.println("ID Navio: "+selectedNavioid);
                    //Verifica se existem navios disponíveis
                    if(naviosDisponiveis.getNumberOfNavios(selectedNavio.getId()) > 0){
                        
                        //System.out.println("N Navios Disponiveis = "+naviosDisponiveis.getNumberOfNavios(selectedNavio.getId()));
                        if (!jogador.getTabuleiro().validatePosicao(selectedNavio.getTamanho(), orientacaoSelecionada, jogador.getTabuleiro().getCasaSelecionada().getXPos(), jogador.getTabuleiro().getCasaSelecionada().getYPos())){
                            //System.out.println("Posicao Invalida");
                            return;
                        }else{
                            try {
                                jogador.getTabuleiro().placeNavio(selectedNavio.getTamanho(), orientacaoSelecionada, jogador.getTabuleiro().getCasaSelecionada().getXPos(), jogador.getTabuleiro().getCasaSelecionada().getYPos());
                                jogador.getTabuleiro().resetCasaSelecionada();
                                
                            } catch (Exception err) {
                                System.out.println("Erro ao posicionar no tabuleiro do jogador");
                            }

                            try {
                                jogador.getNaviosEmJogo().addNavio(selectedNavioid, selectedNavio);
                            } catch (Exception exc) {
                                System.out.println("Erro de Adicionar navios na bag do jogador");
                            }

                            try {
                                naviosDisponiveis.removeNavio(selectedNavioid);
                                sharedLabels.setnnNaviosByID(selectedNavioid, naviosDisponiveis.getNumberOfNavios(selectedNavioid));
                            } catch (Exception exc) {
                                System.out.println("Não foi possível remover o navio selecionado");
                            }

                            jogador.getTabuleiro().update();
                        }
                        //System.out.println("Clicou");
                    }else{
                        System.out.println("Número de navios do tipo selecionado é menor ou igual a 0");
                    }
                }  
            });
            this.setSize(300,200);
            this.setVisible(true);
        }

    }
    
    /*Painel com botões para selecionar navios*/
    private class NaviosPanel extends JPanel{
        private JButton portaAviaoButton, contraTorpedeiroButton, navioTanqueButton, submarinoButton;
        private JLabel[] labelList;
        
        public NaviosPanel(SharedJLabels sharedJLabels){            
            this.setLayout(new GridLayout(2,3));
            portaAviaoButton = new JButton("Selecionar Porta-Avião");
            contraTorpedeiroButton = new JButton("Selecionar Contra-torpedeiro");
            navioTanqueButton = new JButton("Selecionar Navio-Tanque");
            submarinoButton = new JButton("Selecionar submarino");
            
            portaAviaoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (naviosDisponiveis.getNumberOfNavios(0) > 0) {
                    selectedNavio = naviosDisponiveis.getNavio(0);
                }
                    sharedJLabels.setnPortaAviaoLabelText("Número de Porta-Aviões: " + naviosDisponiveis.getNumberOfNavios(0));
                }  
            });
            navioTanqueButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (naviosDisponiveis.getNumberOfNavios(1) > 0) {
                    selectedNavio = naviosDisponiveis.getNavio(1);
                }                    
                    sharedJLabels.setnNavioTanqueLabelText("Número de Navios Tanques: " +  naviosDisponiveis.getNumberOfNavios(1));
                }  
            });
                        
            contraTorpedeiroButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (naviosDisponiveis.getNumberOfNavios(2) > 0) {
                        selectedNavio = naviosDisponiveis.getNavio(2);
                    }
                    sharedJLabels.setnContraTorpedeiroLabelText("Número de Contra Torpedeiros: " + naviosDisponiveis.getNumberOfNavios(2));
                }  
            });
                                    
            submarinoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(naviosDisponiveis.getNumberOfNavios(3) > 0){
                        selectedNavio = naviosDisponiveis.getNavio(3);                        
                    }
                    
                    sharedJLabels.setnSubmarinoLabelText("Número de Submarinos: " + naviosDisponiveis.getNumberOfNavios(3));
                }  
            });
            this.add(portaAviaoButton);
            this.add(contraTorpedeiroButton);
            this.add(navioTanqueButton);
            this.add(submarinoButton);
            this.setBorder( BorderFactory.createEmptyBorder(0,0,100,0) );
            
            //this.setSize(100, 100);   
            //this.setPreferredSize(new Dimension(50,50));
        }
        
    }
    
    public void run(){
        SharedJLabels sharedLabels = new SharedJLabels();
        
        prepPhaseJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prepPhaseJframe.setSize(new Dimension(1000,700));
        prepPhaseJframe.setResizable(false);
        prepPhaseJframe.setVisible(true);
        prepPhaseJframe.setLayout(new BorderLayout());
                        
        prepPhaseJframe.add(new TextPanel(sharedLabels), BorderLayout.NORTH);
        prepPhaseJframe.add(jogador.getTabuleiro(), BorderLayout.CENTER);
        prepPhaseJframe.add(new ButtonPanel(sharedLabels),BorderLayout.SOUTH);
        prepPhaseJframe.add(new NaviosPanel(sharedLabels), BorderLayout.LINE_END);
        
    }    
}
