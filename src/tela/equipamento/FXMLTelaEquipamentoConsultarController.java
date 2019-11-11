/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.equipamento;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dao.EquipamentoDao;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Equipamento;
import tela.FXMLTelaPrincipalController;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaEquipamentoConsultarController implements Initializable {

    @FXML
    private JFXTextField tfSigla;
    @FXML
    private JFXTextField tfFabricante;
    @FXML
    private JFXTextField tfMatricula;
    @FXML
    private Button btAtualizar;
    @FXML
    private Button btConsultar;
    @FXML
    private JFXListView<Equipamento> lvEquipamento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cliqueBotaoTeclado(KeyEvent event) {

        EquipamentoDao eqd = new EquipamentoDao();

        limparCampos();

        List<Equipamento> eq = eqd.buscaProSigla(tfSigla.getText());
        povoarListView(eq);

    }

    @FXML
    private void actionBtAtualizar(ActionEvent event) {

        AnchorPane apTelaEquipe = Util.getTelaEquipamentoCarregada().getApTelaEquipe();

        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/tela/equipamento/FXMLTelaEquipamentoAtualizar.fxml"));

            AnchorPane.setTopAnchor(a, 0.0);
            AnchorPane.setLeftAnchor(a, 0.0);
            AnchorPane.setRightAnchor(a, 0.0);
            AnchorPane.setBottomAnchor(a, 0.0);

            apTelaEquipe.getChildren().setAll(a);

            Button btConsulta = Util.getTelaEquipamentoCarregada().getBtConsultar();
            Button btAtualiza = Util.getTelaEquipamentoCarregada().getBtAtualizar();

            btConsulta.setStyle("-fx-background-color: #000000 ;");
            btAtualiza.setStyle("-fx-background-color: #2a2a2a ;");

        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void actionBtConsultar(ActionEvent event) {

        Equipamento eq = new Equipamento();

        eq = lvEquipamento.getSelectionModel().getSelectedItem();

        povoarCampos(eq);

        //Salva Equipamento consultado
        Util.setEquipamentoAtualizar(eq);
      
    }

    private void povoarListView(List<Equipamento> equipamento) {

        lvEquipamento.getItems().clear();

        for (Equipamento e : equipamento) {

            if (e != null) {

                lvEquipamento.getItems().add(e);
            }
        }

    }

    private void povoarCampos(Equipamento eq) {

        if (eq != null) {

            tfSigla.setText(eq.getSigla());
            tfMatricula.setText(eq.getMatriculaEquipamento());
            tfFabricante.setText(eq.getFabricante().getPj().getPessoa().getNome());

        }

    }

    private void limparCampos() {

        tfMatricula.setText("");
        tfFabricante.setText("");

    }
}
