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
import dao.PjDao;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Area;
import model.Equipamento;
import model.Fabricante;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaEquipamentoAtualizarController implements Initializable {

    @FXML
    private JFXTextField tfSiglaMaquina;
    @FXML
    private JFXComboBox<String> cbTipoMaquina;
    @FXML
    private JFXTextField tfModeloMaquina;
    @FXML
    private JFXTextField tfMatriculaMaquina1;
    @FXML
    private JFXComboBox<String> cbAreaMaquina;
    @FXML
    private JFXComboBox<String> cbFabricanteMaquina;
    @FXML
    private JFXButton btAreaNaoCadastrada;
    @FXML
    private JFXComboBox<String> cbStatus;
    @FXML
    private Button btAtualizar;
    @FXML
    private Button btConsultar;
    private Equipamento eqAtualizzar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> tipo = Util.getTipoMaquinas();
        cbTipoMaquina.setItems(tipo);

        ObservableList<String> status = FXCollections.observableArrayList("ATIVO", "INATIVO");
        cbStatus.setItems(status);

        carregaCbFabricante();
        carregaCbArea();

        if (Util.getEquipamentoAtualizar() != null) {

            povoarCampo(Util.getEquipamentoAtualizar());
            eqAtualizzar = Util.getEquipamentoAtualizar();
        }

        tfSiglaMaquina.setEditable(false);
        tfMatriculaMaquina1.setEditable(false);

        Util.setEquipamentoAtualizar(null);
    }

    @FXML
    private void actionAreaNaoCadastrada(ActionEvent event) {
    }

    @FXML
    private void actionBtAtualizar(ActionEvent event) {

        if (eqAtualizzar != null) {

            atualizarEquipamento(eqAtualizzar);

        }
    }

    @FXML
    private void actionBtConsultar(ActionEvent event) {
    }

    private void atualizarEquipamento(Equipamento eq) {

        AreaDao ad = new AreaDao();
        FabricanteDao fd = new FabricanteDao();

        if (!tfSiglaMaquina.getText().isEmpty()) {
            if (cbTipoMaquina.getValue() != null) {
                if (!tfModeloMaquina.getText().isEmpty()) {
                    if (!tfMatriculaMaquina1.getText().isEmpty()) {
                        if (cbFabricanteMaquina.getValue() != null) {
                            if (cbAreaMaquina.getValue() != null) {

                                Equipamento e = eq;
                                e.setSigla(tfSiglaMaquina.getText());
                                e.setTipo(cbTipoMaquina.getValue());
                                e.setModelo(tfModeloMaquina.getText());
                                e.setMatriculaEquipamento(tfMatriculaMaquina1.getText());
                                e.setFabricante(fd.buscaPorNome(cbFabricanteMaquina.getValue()));
                                e.setArea(ad.buscaPorNome(cbAreaMaquina.getValue()));

                                if ("INATIVO".equals(cbStatus.getValue())) {

                                    e.setStatusEquipamento(0);

                                } else {
                                    e.setStatusEquipamento(1);
                                }

                                limparCampos();

                                EquipamentoDao ed = new EquipamentoDao();
                                ed.atualizaEquipamento(e);

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

    private void limparCampos() {

        tfSiglaMaquina.setText("");
        tfModeloMaquina.setText("");
        tfMatriculaMaquina1.setText("");
        cbAreaMaquina.setValue(null);
        cbFabricanteMaquina.setValue(null);
        cbTipoMaquina.setValue(null);

    }

    private void povoarCampo(Equipamento eq) {

        tfSiglaMaquina.setEditable(true);
        tfMatriculaMaquina1.setEditable(true);

        tfSiglaMaquina.setText(eq.getSigla());
        tfModeloMaquina.setText(eq.getModelo());
        tfMatriculaMaquina1.setText(eq.getMatriculaEquipamento());
        cbAreaMaquina.setValue(eq.getArea().toString());
        cbFabricanteMaquina.setValue(eq.getFabricante().toString());
        cbTipoMaquina.setValue(eq.getTipo());

        if (eq.getStatusEquipamento() == 0) {
            cbStatus.setValue("INATIVO");
        } else {
            cbStatus.setValue("ATIVO");
        }

        tfSiglaMaquina.setEditable(false);
        tfMatriculaMaquina1.setEditable(false);

    }

    public void carregaCbFabricante() {

        FabricanteDao fd = new FabricanteDao();

        List<Fabricante> fabricante = fd.buscarFabricante();

        Collections.sort(fabricante);

        cbFabricanteMaquina.getItems().clear();

        for (Fabricante f : fabricante) {

            cbFabricanteMaquina.getItems().add(f.toString());

        }
    }

    public void carregaCbArea() {

        AreaDao ad = new AreaDao();

        List<Area> area = ad.buscarTodosFabricantes();

        Collections.sort(area);

        cbAreaMaquina.getItems().clear();

        for (Area a : area) {

            cbAreaMaquina.getItems().add(a.toString());

        }
    }

}
