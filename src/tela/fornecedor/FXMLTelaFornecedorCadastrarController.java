/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.fornecedor;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Email;
import model.Endereco;
import model.Fabricante;
import model.Funcionario;
import model.Pessoa;
import model.Pj;
import model.Telefone;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaFornecedorCadastrarController implements Initializable {

    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCnpj;
    @FXML
    private JFXTextField tfRua;
    @FXML
    private JFXTextField tfNumeroRua;
    @FXML
    private JFXTextField tfBairro;
    @FXML
    private JFXTextField tfCidade;
    @FXML
    private JFXTextField tfEstado;
    @FXML
    private JFXTextField tfPais;
    @FXML
    private JFXTextField tfDDD1;
    @FXML
    private JFXTextField tfNumeroTel1;
    @FXML
    private JFXComboBox<String> cbTipoTel1;
    @FXML
    private JFXTextField tfEmail1;
    @FXML
    private JFXTextField tfDDD2;
    @FXML
    private JFXTextField tfNumeroTel2;
    @FXML
    private JFXComboBox<String> cbTipoTel2;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btLimpar;
    @FXML
    private JFXTextField tfNomeContato;
    @FXML
    private JFXCheckBox cbFabricanteMaquina;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> tipoTel = FXCollections.observableArrayList("RES", "CEL", "COM");
        cbTipoTel1.setItems(tipoTel);
        cbTipoTel2.setItems(tipoTel);
    }

    @FXML
    private void actionBtSalvar(ActionEvent event) {
        if (validarCampos()) {
            salvarPj();
        }
    }

    @FXML
    private void actionBtLimpar(ActionEvent event) {

        limparCampos();
    }

    public void salvarPj() {

        Pj pj = new Pj();
        Pessoa p = new Pessoa();
        Endereco e = new Endereco();
        Telefone telefone1 = new Telefone();
        Telefone telefone2 = new Telefone();
        Email email = new Email();
        Fabricante fabricante = new Fabricante();

        p.setNome(tfNome.getText());
        pj.setCnpj(tfCnpj.getText());
        pj.setNomeContato(tfNomeContato.getText());
        e.setRua(tfRua.getText());
        e.setNumero(tfNumeroRua.getText());
        e.setBairro(tfBairro.getText());
        e.setEstado(tfEstado.getText());
        e.setCidade(tfCidade.getText());
        e.setPais(tfPais.getText());
        email.setEmail(tfEmail1.getText());

        telefone1.setDdd(tfDDD1.getText());
        telefone1.setDdi("+55");
        telefone1.setNumero(tfNumeroTel1.getText());
        telefone1.setTipo(cbTipoTel1.getValue());

        telefone2.setDdd(tfDDD2.getText());
        telefone2.setDdi("+55");
        telefone2.setNumero(tfNumeroTel2.getText());
        telefone2.setTipo(cbTipoTel2.getValue());

        e.setPessoa(p);
        pj.setPessoa(p);
        email.setPessoa(p);
        telefone1.setPessoa(p);
        telefone2.setPessoa(p);
        fabricante.setPj(pj);
        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            s.save(p);
            s.save(e);
            s.save(email);

            if (cbFabricanteMaquina.isSelected()) {
                s.save(fabricante);
            }

            s.save(telefone1);
            if (!tfNumeroTel2.getText().isEmpty()) {
                s.save(telefone2);
            }

            s.save(pj);

            s.getTransaction().commit();
            limparCampos();
            Util.mensagem("Salvo com Sucesso", "Salvo com Sucesso");

        } catch (HibernateException ex) {
            String erro = ex.getMessage();
            Util.mensagem(erro, "Erro ao Salvar");
        }

    }

    public void limparCampos() {

        tfNome.setText("");
        tfNomeContato.setText("");
        tfRua.setText("");
        tfNumeroRua.setText("");
        tfBairro.setText("");
        tfCidade.setText("");
        tfDDD1.setText("");
        tfNumeroTel1.setText("");
        tfEmail1.setText("");
        tfDDD2.setText("");
        tfNumeroTel2.setText("");
        tfEstado.setText("");
        cbTipoTel1.setValue("");
        cbTipoTel2.setValue("");
        tfCnpj.setText("");
        

    }

    public boolean validarCampos() {

        if (validarMome()) {
            return false;
        } else if (!validaCnpj()) {
            return false;
        } else if (tfNomeContato.getText().isEmpty()) {
            Util.mensagem("Escolha uma função", "Preencha Função");
            return false;
        } else if (tfNumeroTel1.getText().isEmpty()) {
            Util.mensagem("Preencha numero Telefone", "preencha telefone");
            return false;
        }

        return true;
    }

    private boolean validarMome() {

        String nome = tfNome.getText();

        if (!nome.isEmpty()) {

            String sql = "From Pessoa Where Nome = '" + nome + "'";

            try {

                Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                s.beginTransaction();

                List<Funcionario> lista = (List<Funcionario>) s.createQuery(sql).list();
                s.getTransaction().commit();

                if (lista.isEmpty()) {
                    return false;
                } else {
                    Util.mensagem("Nome Ja Cadastrada", "Nome ja cadastrada");
                    return true;
                }

            } catch (HibernateException e) {
            }

        } else {
            Util.mensagem("Por  favor preencha a Nome", "preencha Nome");
            return true;

        }

        return false;

    }

    private boolean validaCnpj() {

        if (tfCnpj.getText().isEmpty()) {
            Util.mensagem("Preencha CNPJ", "Preencha CNPJ");
            return false;
        }
        return true;
    }
}
