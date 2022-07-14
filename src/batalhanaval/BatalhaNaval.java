/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package batalhanaval;

import batalhanaval.Scenes.TelaInicial.TelaInicial;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author kenji
 */
public class BatalhaNaval implements Runnable{
    /**
     */
    @Override
    public void run() {
        SwingUtilities.invokeLater(new TelaInicial());
    };
    
    public static void main(String[] args) {
        //Game game = new Game();
        SwingUtilities.invokeLater(new BatalhaNaval());
    }    
}
