/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhanaval.Utils;

import batalhanaval.GameObjects.NaviosEmJogo;
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
    
    public JLabel getnNaviosLabelById(int id){
        switch(id){
            case 0:
                return nPortaAviaoLabel;
            case 1:
                return nNavioTanqueLabel;
            case 2:
                return nContraTorpedeiroLabel;
            default:
                return nSubmarinoLabel;
        }
    }
    
    public void setnnNaviosByID(int id, int numberOfNavios){
        switch(id){
            case 0:
                this.nPortaAviaoLabel.setText("Número de Porta aviões: "+numberOfNavios);
                break;
            case 1:
                this.nNavioTanqueLabel.setText("Número de Navios Tanque: "+numberOfNavios);
                break;
            case 2:
                this.nContraTorpedeiroLabel.setText("Número de Contra-torpedeiros: "+numberOfNavios);
                break;
            default:
                this.nSubmarinoLabel.setText("Número de Submarinos: "+numberOfNavios);
                break;
        }
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
    public void updateLabel(NaviosEmJogo naviosEmJogo){
        setnPortaAviaoLabelText("Número de Porta aviões: "+naviosEmJogo.getNumberOfNavios(0));
        setnNavioTanqueLabelText("Número de Navio Tanque: "+naviosEmJogo.getNumberOfNavios(1));
        setnContraTorpedeiroLabelText("Número de Contra-Torpedeiro: "+naviosEmJogo.getNumberOfNavios(2));
        setnSubmarinoLabelText("Número de Submarinos: "+naviosEmJogo.getNumberOfNavios(3));
        setPiecesLeftTotalLabelText("Total de Navios disponíveis: " + naviosEmJogo.getTotal());
    }
}
