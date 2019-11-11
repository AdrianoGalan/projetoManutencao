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
public class FXMLTelaEquipeController implements Initializable {

    @FXML
    private Button btConsultar;
    @FXML
    private Button btAtualizar;
    @FXML
    private Button btCadastrar;
    @FXML
    private AnchorPane apTelaEquipe;

    public Button getBtConsultar() {
        return btConsultar;
    }

    public void setBtConsultar(Button btConsultar) {
        this.btConsultar = btConsultar;
    }

    public Button getBtAtualizar() {
        return btAtualizar;
    }

    public void setBtAtualizar(Button btAtualizar) {
        this.btAtualizar = btAtualizar;
    }

    public Button getBtCadastrar() {
        return btCadastrar;
    }

    public void setBtCadastrar(Button btCadastrar) {
        this.btCadastrar = btCadastrar;
    }

    public AnchorPane getApTelaEquipe() {
        return apTelaEquipe;
    }

    public void setApTelaEquipe(AnchorPane apTelaEquipe) {
        this.apTelaEquipe = apTelaEquipe;
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionBtConsultar(ActionEvent event) {

        clikbotaoMenuPrincipal("/tela/equipe/FXMLTelaEquipeConsultar.fxml", 3);
    }

    @FXML
    private void actionBtAtualizar(ActionEvent event) {

        clikbotaoMenuPrincipal("/tela/equipe/FXMLTelaEquipeAtualizar.fxml", 1);
    }

    @FXML
    private void actionBtCatastrar(ActionEvent event) {

        clikbotaoMenuPrincipal("/tela/equipe/FXMLTelaEquipeCadastrar.fxml", 2);
       

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
           Util.setTelaEquipeCarregada(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //troca cor botão
        trocarCorBotao(botao);

    }

    public void trocarCorBotao(int botao) {

        switch (botao) {
            case 1:
                btAtualizar.setStyle("-fx-background-color: #2a2a2a ;");
                btCadastrar.setStyle("-fx-background-color: #000000 ;");
                btConsultar.setStyle("-fx-background-color: #000000 ;");
                break;
            case 2:
                btAtualizar.setStyle("-fx-background-color: #000000 ;");
                btCadastrar.setStyle("-fx-background-color: #2a2a2a ;");
                btConsultar.setStyle("-fx-background-color: #000000 ;");
                break;
            case 3:
                btAtualizar.setStyle("-fx-background-color: #000000 ;");
                btCadastrar.setStyle("-fx-background-color: #000000 ;");
                btConsultar.setStyle("-fx-background-color: #2a2a2a ;");
                break;

        }
    }

}
