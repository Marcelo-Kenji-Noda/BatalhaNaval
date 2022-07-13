/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
        JButton orientationHButton, orientationVButton, returnNavioButton, randomPlacementButton, clearBoardButton, posicionarButton;
        JTextField XcoordSpinner, YcoordSpinner;
        JLabel XcoordSpinnerLabel, YcoordSpinnerLabel;
        public ButtonPanel(SharedJLabels sharedLabels){
            this.setLayout(new GridLayout(2,6));
            orientationHButton = new JButton("Horizontal");
            orientationVButton = new JButton("Vertical");
            returnNavioButton = new JButton("Retornar Navio");
            randomPlacementButton = new JButton("Posicionar aleatoriamente");
            clearBoardButton = new JButton("Limpar tabuleiro");
            XcoordSpinner = new JTextField("0");
            YcoordSpinner = new JTextField("0");
            
            XcoordSpinnerLabel = new JLabel("Coordenada X");
            XcoordSpinnerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            YcoordSpinnerLabel = new JLabel("Coordenada Y");
            YcoordSpinnerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            posicionarButton = new JButton("Posicionar Navio");

            this.add(orientationHButton);
            this.add(orientationVButton);
            
            this.add(posicionarButton);
            this.add(returnNavioButton);
            this.add(randomPlacementButton);
            this.add(clearBoardButton);
            
            this.add(XcoordSpinnerLabel);                        
            this.add(XcoordSpinner);
            this.add(YcoordSpinnerLabel);
            this.add(YcoordSpinner);
            
            orientationHButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    orientacaoSelecionada = Orientacao.HORIZONTAL;
                    sharedLabels.setOrientacaoLabel("Orientação atual: Horizontal");
                }  
            });
            
            orientationVButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    orientacaoSelecionada = Orientacao.VERTICAL;
                    sharedLabels.setOrientacaoLabel("Orientação atual: Vertical");
                }  
            });
            
            
            XcoordSpinner.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (((c < '0') || (c > '9')) && ((c != KeyEvent.VK_BACK_SPACE))) {
                        e.consume();
                    }
                    try{
                    xCoord = Integer.parseInt(XcoordSpinner.getText());
                    if(xCoord > 10){
                        xCoord = 0;
                        XcoordSpinner.setText("0");
                    }
                   }catch(Exception er){
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
                    yCoord = Integer.parseInt(YcoordSpinner.getText());
                    if(yCoord > 10){
                        yCoord = 0;
                        YcoordSpinner.setText("0");
                    }
                   }catch(Exception er){
                    yCoord = 0; 
                   }                                        
                }
            });
            
            posicionarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedNavioid = selectedNavio.getId();
                    //Verifica se existem navios disponíveis
                    if(naviosDisponiveis.getNumberOfNavios(selectedNavio.getId()) > 0){
                        if (!jogador.getTabuleiro().validatePosicao(selectedNavio.getTamanho(), orientacaoSelecionada, xCoord, yCoord)){
                            System.out.println("Posicao Invalida");
                            return;
                        }
                        System.out.println("Clicou");
                        jogador.getTabuleiro().placeNavio(selectedNavio.getTamanho(),orientacaoSelecionada,new Casa(xCoord, yCoord));
                        jogador.getNaviosEmJogo().addNavio(selectedNavioid, selectedNavio);
                        naviosDisponiveis.removeNavio(selectedNavioid);
                        jogador.getTabuleiro().update();
                    }else{
                        System.out.println("Nem entrou");
                    }
                }  
            });
            this.setSize(300,200);
            this.setVisible(true);
        }

    }
    
    private class NaviosPanel extends JPanel{
        private JButton portaAviaoButton, contraTorpedeiroButton, navioTanqueButton, submarinoButton;
        private JLabel[] labelList;
        
        public NaviosPanel(SharedJLabels sharedJLabels){            
            this.setLayout(new GridLayout(2,3));
            portaAviaoButton = new JButton("Selecionar Porta-Avião");
            contraTorpedeiroButton = new JButton("Selecionar Contra-torpedeiro");
            navioTanqueButton = new JButton("Selecionar Navio Tanque");
            submarinoButton = new JButton("Selecionar submarino");
            
            portaAviaoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    selectedNavio = naviosDisponiveis.getNavio(0);
                    sharedJLabels.setSelectedNavioLabelText("Avião Selecionado: " + selectedNavio.toString());
                }  
            });
            navioTanqueButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    selectedNavio = naviosDisponiveis.getNavio(1);
                    sharedJLabels.setSelectedNavioLabelText("Avião Selecionado: " + selectedNavio.toString());
                }  
            });
                        
            contraTorpedeiroButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    selectedNavio = naviosDisponiveis.getNavio(2);
                    sharedJLabels.setSelectedNavioLabelText("Avião Selecionado: " + selectedNavio.toString());
                }  
            });
                                    
            submarinoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    selectedNavio = naviosDisponiveis.getNavio(3);
                    sharedJLabels.setSelectedNavioLabelText("Avião Selecionado: " + selectedNavio.toString());
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
