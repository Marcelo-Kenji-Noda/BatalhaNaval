/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package batalhanaval.EnumerateClasses;

import java.awt.Color;

/**
 *
 * @author kenji
 */
public enum CustomColorPallete {
    MAR(new Color(204, 203, 244)),
    NAVIO(new Color(248,198,72)),
    ACERTO(new Color(69, 179, 57)),
    ERRO(new Color(129,129,129)),
    HOVER(new Color(150, 149, 194)),
    SELECTED(new Color(213, 128, 224)),
    ARROW(new Color(149, 55, 166));
    
    public final Color color;
    
    private CustomColorPallete(Color color){
        this.color = color;
    }
}
