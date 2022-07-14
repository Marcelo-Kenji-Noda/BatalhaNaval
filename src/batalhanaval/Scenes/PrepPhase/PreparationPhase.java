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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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
    private Jogador bot;
    private NaviosEmJogo naviosDisponiveis;
    
    //Itens selecionados;
    private Navio selectedNavio;    
    private Orientacao orientacaoSelecionada;
    private int xSelected, ySelected;
    private Casa selectedCasa;
    
    //Construtor
    public PreparationPhase(int mode, int id_jogador) {
        this.mode = mode;
        this.jogador = new Jogador(id_jogador);
        this.bot = new Jogador();
        
        //Inicia uma seleção default        
        this.naviosDisponiveis = new NaviosEmJogo();
        this.orientacaoSelecionada = Orientacao.HORIZONTAL;
        this.xSelected = 0;
        this.ySelected = 0;
        this.selectedCasa = new Casa(xSelected,ySelected);
        
        //Inicia os navios disponíveis com todos os navios
        this.naviosDisponiveis.fillNaviosEmJogo(); 
        selectedNavio = naviosDisponiveis.getNavio(0);

               
    }
    
    //Paineis    
    //Painel com os Textos
    private class TextPanel extends JPanel{
        //Titulo
        private JLabel title = new JLabel("Fase de preparação");
        
        //Label para navio selecionado e total de navios disponíveis
        private JLabel selectedNavioLabel, piecesLeftTotalLabel;
        private int npiecesLeft;
        
        //Quantidade de cada Navio disponível
        private int nPortaAviao, nNavioTanque, nContraTorpedeiro, nSubmarino;
        private JLabel nPortaAviaoLabel, nNavioTanqueLabel, nContraTorpedeiroLabel, nSubmarinoLabel;
        
        //Recebe SharedLabels
        //Construtor        
        public TextPanel(SharedJLabels sharedLabels){
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            /*Painel com os textos*/
            //Variáveis que armazenam a quantidade de cada tipo de navio disponível
            npiecesLeft = naviosDisponiveis.getTotal();            
            nPortaAviao = naviosDisponiveis.getNumberOfNavios(0);
            nNavioTanque = naviosDisponiveis.getNumberOfNavios(1);
            nContraTorpedeiro = naviosDisponiveis.getNumberOfNavios(2);
            nSubmarino = naviosDisponiveis.getNumberOfNavios(3);
                        
            //SharedLabels foi utilizado como um gerenciador de estado para ser reaproveitado em outros campos
            //Layout para os Labels
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 4;            
            this.add(sharedLabels.getSelectedNavioLabel(), c);
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 2;
            this.add(sharedLabels.getPiecesLeftTotalLabel(),c);
            c.gridx = 2;
            c.gridy = 1;
            c.gridwidth = 2;
            this.add(sharedLabels.getorientacaoLabel(), c);
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 1;
            
            sharedLabels.getnPortaAviaoLabel().setBorder(BorderFactory.createEmptyBorder(0, 10,0 , 10));
            this.add(sharedLabels.getnPortaAviaoLabel(),c);
            
            c.gridx = 1;
            c.gridy = 2;
            
            sharedLabels.getnNavioTanqueLabel().setBorder(BorderFactory.createEmptyBorder(0, 10,0 , 10));
            this.add(sharedLabels.getnNavioTanqueLabel(),c);
            
            c.gridx = 2;
            c.gridy = 2;
            sharedLabels.getnContraTorpedeiroLabel().setBorder(BorderFactory.createEmptyBorder(0, 10,0 , 10));
            this.add(sharedLabels.getnContraTorpedeiroLabel(),c);
            
            c.gridx = 3;
            c.gridy = 2;
            
            sharedLabels.getnSubmarinoLabel().setBorder(BorderFactory.createEmptyBorder(0, 10,0 , 10));
            this.add(sharedLabels.getnSubmarinoLabel(),c);

            this.setVisible(true);
        }
        
        //Função auxiliar para pegar o Label do Navio do tipo id
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
        
        //Função auxiliar para pegar o Label do Navio atual
        public JLabel getCurrentJLabel(){
            return selectedNavioLabel;
        }   
    };
    
    //Painel com botões
    private class ButtonPanel extends JPanel {
        //Botões disponíveis
        JButton orientationHButton, orientationVButton, returnNavioButton, randomPlacementButton, clearBoardButton, posicionarButton, iniciarJogoButton;

        //Construtor
        public ButtonPanel(SharedJLabels sharedLabels) {
            //Layout do botões
            this.setLayout(new GridLayout(2, 3));
            
            //Inicializando os botões
            orientationHButton = new JButton("Horizontal");
            orientationVButton = new JButton("Vertical");
            randomPlacementButton = new JButton("Posicionar aleatoriamente");
            clearBoardButton = new JButton("Limpar tabuleiro");           
            posicionarButton = new JButton("Posicionar Navio");
            iniciarJogoButton = new JButton("Iniciar Jogo");
            
            //Botões para determinar Orientação
            this.add(orientationHButton);
            this.add(orientationVButton);
            
            //Botão para preencher e para limpar tabuleiro
            this.add(randomPlacementButton);
            this.add(clearBoardButton);
            
            //Botão para posicionar o navio selecionado e botão de iniciar o jogo
            this.add(posicionarButton);
            this.add(iniciarJogoButton);

            //Ao clicar, define variável de orientacao selecionada para Horizontal
            orientationHButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogador.getTabuleiro().setOrientacaoAtual(Orientacao.HORIZONTAL);
                    orientacaoSelecionada = Orientacao.HORIZONTAL;
                    sharedLabels.setOrientacaoLabel("Orientação atual: Horizontal");
                }
            });
            
            //Ao clicar, define variável de orientacao selecionada para Vertical
            orientationVButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    orientacaoSelecionada = Orientacao.VERTICAL;
                    jogador.getTabuleiro().setOrientacaoAtual(Orientacao.VERTICAL);
                    sharedLabels.setOrientacaoLabel("Orientação atual: Vertical");
                }
            });
            
            //Ao clicar inicia o jogo, caso todos os navios tenham sido posicionados
            iniciarJogoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                if(naviosDisponiveis.checkVazio()){
                    Utils.placeNaviosRandomBot(bot);
                    Game newGame = new Game(jogador, bot);
                    prepPhaseJframe.dispose();
                    newGame.run();
                }else{
                   JOptionPane.showMessageDialog(null, "Adicione todos os navios navios disponíveis!");
                }
                }
            });
            
            //Remove todos os navios do tabuleiro
            clearBoardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogador.getTabuleiro().clearTabuleiro();
                    jogador.clearNaviosEmJogo();
                    naviosDisponiveis = new NaviosEmJogo();
                    naviosDisponiveis.fillNaviosEmJogo();
                    sharedLabels.updateLabel(naviosDisponiveis);
                    jogador.getTabuleiro().updatePaint();
                }
            });

            //Posiciona os navios de forma aleatória
            randomPlacementButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Utils.placeNaviosRandom(naviosDisponiveis, jogador, sharedLabels);
                }
            });

            //Posiciona o navio selecionado na casa selecionada, na orientação selecionada
            posicionarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    int selectedNavioid = selectedNavio.getId();
                    //Verifica se existem navios disponíveis
                    if (naviosDisponiveis.getNumberOfNavios(selectedNavio.getId()) > 0) {                       
                        if (!jogador.getTabuleiro().validatePosicao(selectedNavio.getTamanho(), orientacaoSelecionada, jogador.getTabuleiro().getCasaSelecionada().getXPos(), jogador.getTabuleiro().getCasaSelecionada().getYPos())) {
                            JOptionPane.showMessageDialog(null, "Navio selecionado não cabe na posição selecionada");
                        } else {
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
                                sharedLabels.setPiecesLeftTotalLabelText("Total de Navios disponíveis: " + naviosDisponiveis.getTotal());
                            } catch (Exception exc) {
                                System.out.println("Não foi possível remover o navio selecionado");
                            }

                            jogador.getTabuleiro().updatePaint();
                        }                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Número de navios do tipo selecionado é menor ou igual a 0");
                    }
                }
            });
            
            //Layout
            this.setSize(300, 200);
            this.setVisible(true);
        }
    }
    
    //Painel com os botões para definir o tipo de navio selecionado
    private class NaviosPanel extends JPanel {

        //Botões e Labels
        private JButton portaAviaoButton, contraTorpedeiroButton, navioTanqueButton, submarinoButton;
        private JLabel[] labelList;

        //Construtor
        public NaviosPanel(SharedJLabels sharedJLabels) {
            //Layout
            this.setLayout(new GridLayout(2, 3));

            //Inicializa os botões
            portaAviaoButton = new JButton("Selecionar Porta-Avião");
            contraTorpedeiroButton = new JButton("Selecionar Contra-torpedeiro");
            navioTanqueButton = new JButton("Selecionar Navio-Tanque");
            submarinoButton = new JButton("Selecionar submarino");

            //Define navio atual como Porta-Aviões, se ainda tiver disponível
            portaAviaoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (naviosDisponiveis.getNumberOfNavios(0) > 0) {
                        selectedNavio = naviosDisponiveis.getNavio(0);
                    }
                    sharedJLabels.setSelectedNavioLabelText("Navio Selecionado: " + selectedNavio.toString());
                    sharedJLabels.setnPortaAviaoLabelText("Número de Porta-Aviões: " + naviosDisponiveis.getNumberOfNavios(0));
                }
            });

            //Define navio atual como Navio-Tanque, se ainda tiver disponível
            navioTanqueButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (naviosDisponiveis.getNumberOfNavios(1) > 0) {
                        selectedNavio = naviosDisponiveis.getNavio(1);
                    }
                    sharedJLabels.setSelectedNavioLabelText("Navio Selecionado: " + selectedNavio.toString());
                    sharedJLabels.setnNavioTanqueLabelText("Número de Navios Tanques: " + naviosDisponiveis.getNumberOfNavios(1));
                }
            });

            //Define navio atual como Contra-Torpedeiro, se ainda tiver disponível
            contraTorpedeiroButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (naviosDisponiveis.getNumberOfNavios(2) > 0) {
                        selectedNavio = naviosDisponiveis.getNavio(2);
                    }
                    sharedJLabels.setSelectedNavioLabelText("Navio Selecionado: " + selectedNavio.toString());
                    sharedJLabels.setnContraTorpedeiroLabelText("Número de Contra Torpedeiros: " + naviosDisponiveis.getNumberOfNavios(2));
                }
            });

            //Define navio atual como Submarino, se ainda tiver disponível
            submarinoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (naviosDisponiveis.getNumberOfNavios(3) > 0) {
                        selectedNavio = naviosDisponiveis.getNavio(3);
                    }
                    sharedJLabels.setSelectedNavioLabelText("Navio Selecionado: " + selectedNavio.toString());
                    sharedJLabels.setnSubmarinoLabelText("Número de Submarinos: " + naviosDisponiveis.getNumberOfNavios(3));
                }
            });

            //Adiciona os botões
            this.add(portaAviaoButton);
            this.add(contraTorpedeiroButton);
            this.add(navioTanqueButton);
            this.add(submarinoButton);

            //Cria uma margem a esquerda para o painel de botões
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        }

    }
    
    public void run(){
        //Inicializa uma variável do tipo SharedLabels para gerenciar os labels
        SharedJLabels sharedLabels = new SharedJLabels();
        
        //Configurações iniciais do JFrame
        prepPhaseJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prepPhaseJframe.setSize(new Dimension(1000,700));
        prepPhaseJframe.setResizable(false);
        prepPhaseJframe.setVisible(true);
        prepPhaseJframe.setLayout(new BorderLayout());
                        
        //Adicioa os Paineis (Border Layout)
        prepPhaseJframe.add(new TextPanel(sharedLabels), BorderLayout.NORTH);
        prepPhaseJframe.add(jogador.getTabuleiro(), BorderLayout.CENTER);
        prepPhaseJframe.add(new ButtonPanel(sharedLabels),BorderLayout.SOUTH);
        prepPhaseJframe.add(new NaviosPanel(sharedLabels), BorderLayout.LINE_END);
        
    }    
}
