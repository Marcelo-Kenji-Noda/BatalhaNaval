/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Utils;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author kenji
 */
public class SharedJLabels {
    private JLabel nPortaAviaoLabel, nNavioTanqueLabel, nContraTorpedeiroLabel, nSubmarinoLabel, selectedNavioLabel, piecesLeftTotalLabel, orientacaoLabel;
    
    public SharedJLabels(){
                /*Set Navio Label*/
            selectedNavioLabel = new JLabel("Navio Selecionado: Porta-Aviões");
            selectedNavioLabel.setFont(new Font("Verdana", Font.BOLD, 14));
            piecesLeftTotalLabel = new JLabel("Total de Navios disponíveis: 10 ");
            nPortaAviaoLabel = new JLabel("Número de Porta aviões: 2");
            nNavioTanqueLabel = new JLabel("Número de Navios Tanque: 3");
            nContraTorpedeiroLabel = new JLabel("Número de Contra-torpedeiros: 4");
            nSubmarinoLabel = new JLabel("Número de Submarinos: 5");
            orientacaoLabel = new JLabel("Orientação atual: Horizontal");
    }

    public JLabel getorientacaoLabel() {
        return orientacaoLabel;
    }

    public JLabel getnPortaAviaoLabel() {
        return nPortaAviaoLabel;
    }

    public JLabel getnNavioTanqueLabel() {
        return nNavioTanqueLabel;
    }

    public JLabel getnContraTorpedeiroLabel() {
        return nContraTorpedeiroLabel;
    }

    public JLabel getnSubmarinoLabel() {
        return nSubmarinoLabel;
    }

    public JLabel getSelectedNavioLabel() {
        return selectedNavioLabel;
    }

    public JLabel getPiecesLeftTotalLabel() {
        return piecesLeftTotalLabel;
    }

    public void setnPortaAviaoLabelText(String text) {
        this.nPortaAviaoLabel.setText(text);
    }

    public void setnNavioTanqueLabelText(String text) {
        this.nNavioTanqueLabel.setText(text);
    }

    public void setnContraTorpedeiroLabelText(String text) {
        this.nContraTorpedeiroLabel.setText(text);
    }

    public void setnSubmarinoLabelText(String text) {
        this.nSubmarinoLabel.setText(text);
    }

    public void setSelectedNavioLabelText(String text) {
        this.selectedNavioLabel.setText(text);
    }

    public void setPiecesLeftTotalLabelText(String text) {
        this.piecesLeftTotalLabel.setText(text);
    }
    public void setOrientacaoLabel(String text) {
        this.orientacaoLabel.setText(text);
    }
    
}
