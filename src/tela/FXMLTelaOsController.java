/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaOsController implements Initializable {

    @FXML
    private Button btGerar;
    @FXML
    private Button btMotificar;
    @FXML
    private Button btFinalizar;
    @FXML
    private AnchorPane apTelaEquipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionBtGerar(ActionEvent event) {
        
        clikbotaoMenuPrincipal("/tela/os/FXMLTelaGerarOs.fxml", 1);
    }

    @FXML
    private void actionBtMotificar(ActionEvent event) {
        
        clikbotaoMenuPrincipal("/tela/os/FXMLTelaMotificar.fxml", 2);
    }

    @FXML
    private void actionBtFinalizar(ActionEvent event) {
        
        clikbotaoMenuPrincipal("/tela/os/FXMLTelaFinalizar.fxml", 3);
    }

    public void clikbotaoMenuPrincipal(String nomeTela, int botao) {

        //carrega tela
        try {

            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource(nomeTela));

            AnchorPane.setTopAnchor(a, 0.0);
            AnchorPane.setLeftAnchor(a, 0.0);
            AnchorPane.setRightAnchor(a, 0.0);
            AnchorPane.setBottomAnchor(a, 0.0);

            apTelaEquipe.getChildren().setAll(a);
            // Util.setTelaCarregada(apTelaEquipe);
            // Util.setTelaEquipeCarregada(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //troca cor botão
        trocarCorBotao(botao);

    }

    public void trocarCorBotao(int botao) {

        switch (botao) {
            case 1:
                btGerar.setStyle("-fx-background-color: #2a2a2a ;");
                btMotificar.setStyle("-fx-background-color: #000000 ;");
                btFinalizar.setStyle("-fx-background-color: #000000 ;");
                break;
            case 2:
                btGerar.setStyle("-fx-background-color: #000000 ;");
                btMotificar.setStyle("-fx-background-color: #2a2a2a ;");
                btFinalizar.setStyle("-fx-background-color: #000000 ;");
                break;
            case 3:
                btGerar.setStyle("-fx-background-color: #000000 ;");
                btMotificar.setStyle("-fx-background-color: #000000 ;");
                btFinalizar.setStyle("-fx-background-color: #2a2a2a ;");
                break;

        }
    }

}
