/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.equipamento;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.AreaDao;
import dao.EquipamentoDao;
import dao.FabricanteDao;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Area;
import model.Equipamento;
import model.Fabricante;
import model.Usuarios;
import tela.FXMLTelaLoginController;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaEquipamentoCadastrarController implements Initializable {

    @FXML
    private JFXTextField tfSiglaMaquina;
    @FXML
    private JFXComboBox<String> cbTipoMaquina;
    @FXML
    private JFXTextField tfModeloMaquina;
    @FXML
    private JFXTextField tfMatriculaMaquina1;
    @FXML
    private JFXComboBox<Area> cbAreaMaquina;
    @FXML
    private JFXComboBox<Fabricante> cbFabricanteMaquina;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btLimpar;
    @FXML
    private JFXButton btAreaNaoCadastrada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> tipo = Util.getTipoMaquinas();
        cbTipoMaquina.setItems(tipo);

        carregaCbFabricante();
        carregaCbArea();

    }

    @FXML
    private void actionBtSalvar(ActionEvent event) {
        
        salvaEquipamento();
    }

    @FXML
    private void actionBtLimpar(ActionEvent event) {
        
        limparCampos();
        
    }

    public void carregaCbFabricante() {

        FabricanteDao fd = new FabricanteDao();

        List<Fabricante> fabricante = fd.buscarFabricante();

        Collections.sort(fabricante);

        cbFabricanteMaquina.getItems().clear();

        for (Fabricante f : fabricante) {

            cbFabricanteMaquina.getItems().add(f);

        }
    }

    public void carregaCbArea() {

        AreaDao ad = new AreaDao();

        List<Area> area = ad.buscarTodosFabricantes();

        Collections.sort(area);

        cbAreaMaquina.getItems().clear();

        for (Area a : area) {

            cbAreaMaquina.getItems().add(a);

        }
    }

    public void iniciarTela() {

        try {

            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/tela/FXMLTelaArea.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);

            //inicia maiximizado
            stage.setMaximized(true);
            //retira botão de fechar
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            // fecha tela do login
            //btAreaNaoCadastrada.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void actionAreaNaoCadastrada(ActionEvent event) {
        iniciarTela();
    }

    private void salvaEquipamento() {

        if (!tfSiglaMaquina.getText().isEmpty()) {
            if (cbTipoMaquina.getValue() != null) {
                if (!tfModeloMaquina.getText().isEmpty()) {
                    if (!tfMatriculaMaquina1.getText().isEmpty()) {
                        if (cbFabricanteMaquina.getValue() != null) {
                            if (cbAreaMaquina.getValue() != null) {

                                Equipamento e = new Equipamento();
                                e.setSigla(tfSiglaMaquina.getText());
                                e.setTipo(cbTipoMaquina.getValue());
                                e.setModelo(tfModeloMaquina.getText());
                                e.setMatriculaEquipamento(tfMatriculaMaquina1.getText());
                                e.setFabricante(cbFabricanteMaquina.getValue());
                                e.setArea(cbAreaMaquina.getValue());
                                e.setStatusEquipamento(1);
                                limparCampos();
                                

                                EquipamentoDao ed = new EquipamentoDao();
                                ed.salvarEquipamento(e);

                            } else {
                                Util.mensagem("Seleciona a area do Equipamento", "Area Invalida");
                            }

                        } else {
                            Util.mensagem("Seleciona um Fabricante para o Equipamento", "Fabricante Invalida");
                        }
                    } else {
                        Util.mensagem("Preencha Matricula do Equipamento", "Matricula Invalida");
                    }

                } else {
                    Util.mensagem("Preencha Modelo do Equipamento", "Modelo Invalida");
                }
            } else {
                Util.mensagem("Escolha o tipo do Equipamento", "Tipo Invalida");
            }

        } else {
            Util.mensagem("Preencha Sliga do Equipamento", "Sigla Invalida");
        }

    }
    
    private void limparCampos(){
        
        tfSiglaMaquina.setText("");
        tfModeloMaquina.setText("");
        tfMatriculaMaquina1.setText("");
        cbAreaMaquina.setValue(null);
        cbFabricanteMaquina.setValue(null);
        cbTipoMaquina.setValue(null);
        
        
    }
}
