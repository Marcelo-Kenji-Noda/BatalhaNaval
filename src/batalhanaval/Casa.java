/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JComponent;

/**
 *
 * @author kenji
 */

public class Casa extends JComponent{
    private Graphics2D g;
    private int xPos;
    private int yPos;
    private Tabuleiro tabuleiro;
    private TipoDeCasa tipoCasa;
    
    int tamanhoCasa = 36;
    
    private String conversorCharToNumber(int i) {
    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    };

    public Casa(int x, int y, TipoDeCasa tipoCasa) {
        this.xPos = x;
        this.yPos = y;
        this.tipoCasa = tipoCasa;

    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g.setColor(new Color(144, 143, 208));
        
        // Draw the red rectangle
        /*
        Stroke stroke1 = new BasicStroke(6f);
 
        g2d.setColor(Color.BLACK);
        g2d.setStroke(stroke1);
        */
        
        g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        System.out.println(this.getX()+" "+this.getY());
        /*
        g2.drawRect(xPos*tamanhoCasa, yPos*tamanhoCasa, tamanhoCasa, tamanhoCasa);
        */
        
        //g2.setColor(new Color(204, 203, 244));
        //(204, 203, 244)
        //g2.fillRect(xPos*tamanhoCasa, yPos*tamanhoCasa, tamanhoCasa, tamanhoCasa);
        //g2.setColor(Color.BLUE);
        // Draw the blue rectangle
        //g2.draw(new Rectangle2D.Double(150, 50, 200, 250));
    }
}
