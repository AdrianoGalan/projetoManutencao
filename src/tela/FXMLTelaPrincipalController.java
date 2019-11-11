/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private AnchorPane apMenuPricimpal;
    @FXML
    private VBox vbMenuPrincipal;
    @FXML
    private AnchorPane apTela;
    @FXML
    private Button btOs;
    @FXML
    private Button btEquipamento;
    @FXML
    private Button btEquipe;
    @FXML
    private Button btUsuario;
    @FXML
    private Button btSair;
    @FXML
    private Button btRelatorio;
    @FXML
    private Label labelUsuarioLogado;
    @FXML
    private Label labelData;
    @FXML
    private Button btFornecador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // set nome do usuario logado
        labelUsuarioLogado.setText(Util.getUsuarioLogado().getFuncionario().getPf().getPessoa().getNome());

        // cria uma data
        java.util.Date d = new Date();

        //formata a data para formato no pc
        String data = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);

        // set data na label
        labelData.setText(data);

    }

    @FXML
    private void actionBtOs(ActionEvent event) {

        clikbotaoMenuPrincipal("FXMLTelaOs.fxml", 1);

    }

    @FXML
    private void actionBtEquipamento(ActionEvent event) {

        clikbotaoMenuPrincipal("FXMLTelaEquipamento.fxml", 2);

    }

    @FXML
    private void actionBtEquipe(ActionEvent event) {

        clikbotaoMenuPrincipal("FXMLTelaEquipe.fxml", 3);
    }

    @FXML
    private void actionBtFornecedor(ActionEvent event) {
        clikbotaoMenuPrincipal("FXMLTelaFornecedor.fxml", 7);

    }

    @FXML
    private void actionBtUsuario(ActionEvent event) {

        clikbotaoMenuPrincipal("FXMLTelaUsuario.fxml", 4);

    }

    @FXML
    private void actionBtRelatorio(ActionEvent event) {

        clikbotaoMenuPrincipal("FXMLTelaRelatorio.fxml", 6);
        System.out.println(Util.getUsuarioLogado());
    }

    @FXML
    private void actionBtSair(ActionEvent event) {

        trocarCorBotao(5);

        // validar para sair
        Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btSim = new ButtonType("Sim");
        ButtonType btNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogo.setHeaderText("Deseja Sair Programa?");
        dialogo.setTitle("Fechar");
        dialogo.getButtonTypes().setAll(btSim, btNao);
        dialogo.showAndWait().ifPresent(b -> {

            if (b == btSim) {
                System.exit(0);
            }

        });

    }

    public void clikbotaoMenuPrincipal(String nomeTela, int botao) {

        //carrega tela
        try {

            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource(nomeTela));

            AnchorPane.setTopAnchor(a, 0.0);
            AnchorPane.setLeftAnchor(a, 0.0);
            AnchorPane.setRightAnchor(a, 0.0);
            AnchorPane.setBottomAnchor(a, 0.0);

            apTela.getChildren().setAll(a);

        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //troca cor botão
        trocarCorBotao(botao);

    }

    public void trocarCorBotao(int botao) {

        switch (botao) {
            case 1:
                btOs.setStyle("-fx-background-color: #2a2a2a ;");
                btEquipamento.setStyle("-fx-background-color: #000000 ;");
                btEquipe.setStyle("-fx-background-color: #000000 ;");
                btUsuario.setStyle("-fx-background-color: #000000 ;");
                btSair.setStyle("-fx-background-color: #000000 ;");
                btRelatorio.setStyle("-fx-background-color: #000000 ;");
                btFornecador.setStyle("-fx-background-color: #000000 ;");
                break;
            case 2:
                btOs.setStyle("-fx-background-color: #000000 ;");
                btEquipamento.setStyle("-fx-background-color: #2a2a2a ;");
                btEquipe.setStyle("-fx-background-color: #000000 ;");
                btUsuario.setStyle("-fx-background-color: #000000 ;");
                btSair.setStyle("-fx-background-color: #000000 ;");
                btRelatorio.setStyle("-fx-background-color: #000000 ;");
                btFornecador.setStyle("-fx-background-color: #000000 ;");
                break;
            case 3:
                btOs.setStyle("-fx-background-color: #000000 ;");
                btEquipamento.setStyle("-fx-background-color: #000000 ;");
                btEquipe.setStyle("-fx-background-color: #2a2a2a ;");
                btUsuario.setStyle("-fx-background-color: #000000 ;");
                btSair.setStyle("-fx-background-color: #000000 ;");
                btRelatorio.setStyle("-fx-background-color: #000000 ;");
                btFornecador.setStyle("-fx-background-color: #000000 ;");
                break;
            case 4:
                btOs.setStyle("-fx-background-color: #000000 ;");
                btEquipamento.setStyle("-fx-background-color: #000000 ;");
                btEquipe.setStyle("-fx-background-color: #000000 ;");
                btUsuario.setStyle("-fx-background-color: #2a2a2a ;");
                btSair.setStyle("-fx-background-color: #000000 ;");
                btRelatorio.setStyle("-fx-background-color: #000000 ;");
                btFornecador.setStyle("-fx-background-color: #000000 ;");
                break;
            case 5:
                btOs.setStyle("-fx-background-color: #000000 ;");
                btEquipamento.setStyle("-fx-background-color: #000000 ;");
                btEquipe.setStyle("-fx-background-color: #000000 ;");
                btUsuario.setStyle("-fx-background-color: #000000 ;");
                btSair.setStyle("-fx-background-color: #2a2a2a ;");
                btRelatorio.setStyle("-fx-background-color: #000000 ;");
                btFornecador.setStyle("-fx-background-color: #000000 ;");
                break;
            case 6:
                btOs.setStyle("-fx-background-color: #000000 ;");
                btEquipamento.setStyle("-fx-background-color: #000000 ;");
                btEquipe.setStyle("-fx-background-color: #000000 ;");
                btUsuario.setStyle("-fx-background-color: #000000 ;");
                btSair.setStyle("-fx-background-color: #000000 ;");
                btRelatorio.setStyle("-fx-background-color: #2a2a2a ;");
                btFornecador.setStyle("-fx-background-color: #000000 ;");
                break;
            case 7:
                btOs.setStyle("-fx-background-color: #000000 ;");
                btEquipamento.setStyle("-fx-background-color: #000000 ;");
                btEquipe.setStyle("-fx-background-color: #000000 ;");
                btUsuario.setStyle("-fx-background-color: #000000 ;");
                btSair.setStyle("-fx-background-color: #000000 ;");
                btRelatorio.setStyle("-fx-background-color: #000000 ;");
                btFornecador.setStyle("-fx-background-color: #2a2a2a ;");
                break;
        }
    }

}
