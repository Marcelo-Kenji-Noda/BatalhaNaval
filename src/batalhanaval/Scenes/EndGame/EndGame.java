/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Scenes.EndGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
/**
 *
 * @author kenji
 */
public class EndGame implements Runnable{
    JFrame endgameFrame = new JFrame("Fim de Jogo");
    int vencedor;
    public EndGame(int vencedor){
        this.vencedor = vencedor;
    }
    @Override
    public void run() {
        endgameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endgameFrame.setSize(new Dimension(500,500));
        endgameFrame.setResizable(false);
        endgameFrame.setVisible(true);
        endgameFrame.setLayout(new BorderLayout());        
        JLabel title = new JLabel("Jogador "+vencedor+" ganhou!");
        JButton exit = new JButton("Sair");
        
        title.setHorizontalAlignment(JLabel.CENTER);
        endgameFrame.add(title, BorderLayout.CENTER);
        endgameFrame.add(exit, BorderLayout.NORTH);
        
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }  
        });
    }
    
}
