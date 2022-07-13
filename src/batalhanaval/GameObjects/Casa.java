/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.GameObjects;

import batalhanaval.EnumerateClasses.TipoDeCasa;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JComponent;

/**
 *
 * @author kenji
 */

public class Casa extends JComponent implements MouseListener{
    private Graphics2D g;
    private int xPos;
    private int yPos;
    private Tabuleiro tabuleiro;
    private TipoDeCasa tipoCasa;
    private boolean currentSelected;
    int tamanhoCasa = 36;
    
    private String conversorCharToNumber(int i) {
    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    };

    public Casa(int x, int y, TipoDeCasa tipoCasa) {
        //this.setPreferredSize(new Dimension(50,50));
        this.xPos = x;
        this.yPos = y;
        this.setBounds(x, y, 50, 50);
        this.tipoCasa = tipoCasa;
        //this.setSize(20,20);
        //this.setVisible(true);
        this.currentSelected = false;
        this.addMouseListener(this);
    }
    
    public Casa(int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.tipoCasa = TipoDeCasa.MAR;
    }
    public void setTipoDeCasa(TipoDeCasa tipodeCasa){
        this.tipoCasa = tipodeCasa;
    }
    
    public void setCasa(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }
    
    public int getXPos(){
        return this.xPos;
    };
    
    public int getYPos(){
        return this.yPos;
    };
    
    public TipoDeCasa getTipoDeCasa(){
        return this.tipoCasa;
    }
    
    public void setCurrentSelected(boolean value){
        this.currentSelected = value;
    }

    public Color getColor(){
        Color current_color;
        if(this.currentSelected){
            current_color = new Color(22, 86, 138);
            return current_color;
        }
        if (this.tipoCasa == TipoDeCasa.ACERTO){
           current_color = new Color(228,83,83);          
        }else if(this.tipoCasa == TipoDeCasa.ERRO){
           current_color = new Color(129,129,129);            
        }else if(this.tipoCasa == TipoDeCasa.NAVIO){
           current_color = new Color(248,198,72);            
        }else{
            current_color = new Color(204, 203, 244);     
        }
        
        return current_color;
    }
    
    public void update_casa(Graphics g){
        try{
            paintComponent(g);
        }catch(Exception e){
            
        }
    }
    @Override
    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        g.setColor(this.getColor());
//        g.fillRect(xPos * 50, yPos * 50, this.getWidth(),
//                    this.getHeight());
//        g.setColor(Color.BLACK);
    g.setColor(this.getColor());
    g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    g.setColor(Color.BLACK);
    g.drawRect(this.getX(), this.getY(), this.getWidth(),
                    this.getHeight());
    //System.out.println("X: "+this.getX() + " Y: " + this.getY() + " Width: "+this.getWidth() + " Height: "+ this.getHeight());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Casa: (" +xPos+ ", "+ yPos+")");
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
