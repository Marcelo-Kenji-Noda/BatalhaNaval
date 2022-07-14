/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.GameObjects;

import batalhanaval.EnumerateClasses.Orientacao;
import batalhanaval.EnumerateClasses.TipoDeCasa;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JComponent;

/**
 *
 * @author kenji
 */

public class Casa extends JComponent implements MouseListener, MouseMotionListener{
    private Graphics2D g;
    private int xPos;
    private int yPos;
    private Tabuleiro tabuleiro;
    private TipoDeCasa tipoCasa;
    private boolean currentSelected;
    private boolean mouseClicked = false;
    int tamanhoCasa = 36;
    private Color currentColor;
    private int currentDisplayMode;
    
    private String conversorCharToNumber(int i) {
    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    };

    public Casa(int x, int y, TipoDeCasa tipoCasa, Tabuleiro tabuleiro) {
        //this.setPreferredSize(new Dimension(50,50));
        this.xPos = x;
        this.yPos = y;
        this.setBounds(x, y, 50, 50);
        this.tipoCasa = tipoCasa;
        //this.setSize(20,20);
        //this.setVisible(true);
        this.currentSelected = false;
        this.currentColor = new Color(204, 203, 244);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.tabuleiro = tabuleiro;
        this.currentDisplayMode = 0;
    }

    public void setCurrentDisplayMode(int currentDisplayMode) {
        this.currentDisplayMode = currentDisplayMode;
    }
    
    public Casa(int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.tipoCasa = TipoDeCasa.MAR;
    }
    public void setTipoDeCasa(TipoDeCasa tipodeCasa){
        this.tipoCasa = tipodeCasa;
        if (this.tipoCasa == TipoDeCasa.ACERTO){
           currentColor = new Color(228,83,83);          
        }else if(this.tipoCasa == TipoDeCasa.ERRO){
           currentColor = new Color(129,129,129);            
        }else if(this.tipoCasa == TipoDeCasa.NAVIO){
           currentColor = new Color(248,198,72);            
        }else{
            currentColor = new Color(204, 203, 244);     
        }
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

    public void update_casaColor(Color c){
        this.currentColor = c;
        this.repaint();
    }
    @Override
    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        g.setColor(this.getColor());
//        g.fillRect(xPos * 50, yPos * 50, this.getWidth(),
//                    this.getHeight());
//        g.setColor(Color.BLACK);
    if(currentDisplayMode == 0){
        g.setColor(currentColor);

        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(this.getX(), this.getY(), this.getWidth(),
                this.getHeight());
        if (tabuleiro.getCasaSelecionada() == this) {
            int[] triangleX = {this.getX() + 5, this.getX() + 5, this.getX() + 48};
            int[] triangleY = {this.getY() + 4, this.getY() + 46, this.getY() + 25};
            if (this.tabuleiro.getOrientacaoAtual() == Orientacao.VERTICAL) {
                triangleX[0] = this.getX() + 4;
                triangleX[1] = this.getX() + 46;
                triangleX[2] = this.getX() + 25;

                triangleY[0] = this.getY() + 5;
                triangleY[1] = this.getY() + 5;
                triangleY[2] = this.getY() + 48;
            }

            g.setColor(new Color(149, 55, 166));
            g.fillPolygon(triangleX, triangleY, 3);
    }else{
            g.setColor(currentColor);
            g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            g.setColor(Color.BLACK);
            g.drawRect(this.getX(), this.getY(), this.getWidth(),
            this.getHeight());
    }
    }
    //System.out.println("X: "+this.getX() + " Y: " + this.getY() + " Width: "+this.getWidth() + " Height: "+ this.getHeight());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(tabuleiro.getCurrentDisplayMode() == 0){
        if(this.tipoCasa == TipoDeCasa.MAR){
            if (tabuleiro.getCasaSelecionada() == this) {
                tabuleiro.resetCasaSelecionada();
                this.update_casaColor(new Color(204, 203, 244));
                System.out.println("Casa desselecionada");
            } else {
                tabuleiro.setCasaSelecionada(this);
                tabuleiro.setisCasaSelecionada(true);
                this.update_casaColor(new Color(213, 128, 224));
                System.out.println("Casa Selecionada: (" + this.xPos + "," + this.yPos + ")");
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }}

    @Override
    public void mousePressed(MouseEvent e) {      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(tabuleiro.getCasaSelecionada() != this && this.tipoCasa == TipoDeCasa.MAR){
           this.update_casaColor(new Color(150, 149, 194));
        }
        
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tabuleiro.setCasaAtual(this);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(tabuleiro.getCasaSelecionada() != this && this.tipoCasa == TipoDeCasa.MAR){
           this.update_casaColor(new Color(204, 203, 244)); 
        }       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
