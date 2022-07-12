/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;

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
    public void initializeNavios(){
        
    }
    
    private class TextPanel extends JPanel{
        private JLabel title = new JLabel("Fase de preparação");
        private JLabel selectedNavioLabel, piecesLeftTotalLabel;
        private int npiecesLeft;
        
        public TextPanel(){
            npiecesLeft = naviosDisponiveis.getTotal();
            
            /*Set Navio Label*/
            selectedNavioLabel = new JLabel(selectedNavio.toString());
            piecesLeftTotalLabel = new JLabel(""+npiecesLeft);
            
            this.add(title);
            this.add(selectedNavioLabel);
            this.add(piecesLeftTotalLabel);
            this.setVisible(true);
            this.setSize(300,100);
        }
    };
    
    private class ButtonPanel extends JPanel{
        JButton orientationHButton, orientationVButton, returnNavioButton, randomPlacementButton, clearBoardButton;
        JSpinner XcoordSpinner, YcoordSpinner;
        
        public ButtonPanel(){
            
            orientationHButton = new JButton("Horizontal");
            orientationVButton = new JButton("Vertical");
            returnNavioButton = new JButton("Retornar Navio");
            randomPlacementButton = new JButton("Posicionar aleatoriamente");
            clearBoardButton = new JButton("Limpar tabuleiro");
            XcoordSpinner = new JSpinner();
            YcoordSpinner = new JSpinner();
            
            this.add(orientationHButton);
            this.add(orientationVButton);
            this.add(returnNavioButton);
            this.add(randomPlacementButton);
            this.add(clearBoardButton);
            this.add(XcoordSpinner);
            this.add(YcoordSpinner);
            
            this.setSize(300,100);
            this.setVisible(true);
        }

    }
    
    public void run(){
        prepPhaseJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prepPhaseJframe.setSize(new Dimension(1000,700));
        prepPhaseJframe.setResizable(false);
        prepPhaseJframe.setVisible(true);
        prepPhaseJframe.setLayout(new BorderLayout());
        
        prepPhaseJframe.add(new TextPanel(), BorderLayout.NORTH);
        prepPhaseJframe.add(jogador.getTabuleiro(), BorderLayout.CENTER);
        prepPhaseJframe.add(new ButtonPanel(),BorderLayout.SOUTH);
        
        //prepPhaseJframe.pack();

    }    
}
