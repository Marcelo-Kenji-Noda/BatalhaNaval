/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.GameObjects;

import batalhanaval.EnumerateClasses.TipoDeCasa;
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
        this.setSize(20,20);
        this.setVisible(true);
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
    
    public Color getColor(){
        Color current_color;
        
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
}
