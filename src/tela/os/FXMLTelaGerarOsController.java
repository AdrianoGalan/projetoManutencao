/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.os;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DefeitoDao;
import dao.EquipamentoDao;
import dao.OsDao;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.Timer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import model.Defeito;
import model.Equipamento;
import model.Os;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaGerarOsController implements Initializable {

    @FXML
    private JFXTextField tfSiglaMaquina;
    @FXML
    private Button btOk;
    @FXML
    private Label labelArea;
    @FXML
    private Label labelFabricante;
    @FXML
    private Label labeltipo;
    @FXML
    private Label labelArea1;
    @FXML
    private Label labelFabricante1;
    @FXML
    private Label labeltipo1;
    @FXML
    private JFXComboBox<Defeito> cbDefeito;
    @FXML
    private JFXComboBox<String> cbOficina;
    @FXML
    private JFXComboBox<String> cbPrioridade;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btLimpar;
    @FXML
    private JFXButton btDefeito;

    private Equipamento equipamentoAtual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<String> oficina = FXCollections.observableArrayList("ELETRICA", "MECANICO", "CALDERARIA", "CIVIL");
        cbOficina.setItems(oficina);

        ObservableList<String> prioridade = FXCollections.observableArrayList("P1", "P2", "P3");
        cbPrioridade.setItems(prioridade);
    }

    @FXML
    private void actionBtOk(ActionEvent event) {

        carregaMaquina(tfSiglaMaquina.getText());

        if (equipamentoAtual != null) {
            carregaCbDefeitos();
        }

    }

    @FXML
    private void actionBtSalvar(ActionEvent event) {

        gerarOs();

    }

    @FXML
    private void actionBtLimpar(ActionEvent event) {

        limparCampos();
    }

    @FXML
    private void actionBtDefeito(ActionEvent event) {

        String defeito;

        // caixa para carregar novo defeito

            
            defeito = Util.caixaEntrada("NOVO DEFEITO", "Digite o Defeito Apresentado na Maquina", "Defeito:");
            
             TextInputDialog dialogoNome = new TextInputDialog();
        
      

     
            //cria um anovo defeito
            Defeito d = new Defeito(defeito, equipamentoAtual);
            //cria um defeito dao
            DefeitoDao dd = new DefeitoDao();

            if (equipamentoAtual != null) {

                dd.salvarDefeito(d);
                carregaCbDefeitos();
            }
        

    }

    private void carregaMaquina(String nome) {

        EquipamentoDao eqd = new EquipamentoDao();
        Equipamento eq = eqd.buscaPorNome(nome);
        equipamentoAtual = eq;

        if (eq != null) {
            povoarCampos(eq);
        } else {
            Util.mensagem("Maquina não cadastrada", "Maquina Invalida");
        }

    }

    public void carregaCbDefeitos() {

        DefeitoDao dd = new DefeitoDao();

        List<Defeito> defeito = dd.buscarDefeitos(equipamentoAtual);

        Collections.sort(defeito);

        cbDefeito.getItems().clear();

        for (Defeito d : defeito) {

            cbDefeito.getItems().add(d);

        }
    }

    private void povoarCampos(Equipamento eq) {

        labelArea.setText(eq.getArea().toString());
        labelFabricante.setText(eq.getFabricante().toString());
        labeltipo.setText(eq.getTipo());
        tfSiglaMaquina.setText(eq.getSigla());

    }

    private void limparCampos() {

        labelArea.setText("");
        labelFabricante.setText("");
        labeltipo.setText("");
        tfSiglaMaquina.setText("");
        equipamentoAtual = null;
        cbDefeito.setValue(null);
        cbOficina.setValue(null);
        cbPrioridade.setValue(null);

    }

    private boolean validarCampos() {

        if (equipamentoAtual != null) {
            if (cbDefeito.getValue() != null) {
                if (!cbPrioridade.getValue().isEmpty()) {
                    if (!cbOficina.getValue().isEmpty()) {
                        return true;
                    } else {
                        Util.mensagem("Oficina não selecionada", "Seleciona uma oficina");
                        return false;
                    }
                } else {
                    Util.mensagem("Prioridade não selecionada", "Seleciona uma prioridade");
                    return false;
                }

            } else {
                Util.mensagem("Defeito não selecionada", "Seleciona uma defeito");
                return false;
            }

        } else {
            Util.mensagem("Maquina não selecionada", "Seleciona uma maquina");
            return false;
        }

    }

    private void gerarOs() {

        OsDao osd = new OsDao();

        // cria uma data
        Date d = new Date();

        //formata a data para formato no pc
        String data = DateFormat.getDateInstance().format(d);

        if (validarCampos()) {

            Os os = new Os();
            os.setPrioridade(cbPrioridade.getValue());
            os.setDefeito(cbDefeito.getValue());
            os.setOficina(cbOficina.getValue());
            os.setStatus("ATIVO");
            os.setDataInicio(d);
            os.setHoraInicio(d);

            if (osd.salvarDefeito(os)) {
                limparCampos();
            }
        }
    }

}
