/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.usuario;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Funcionario;
import model.Usuarios;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaUsuarioAtualizarController implements Initializable {

    @FXML
    private JFXComboBox<Usuarios> cbNome;
    @FXML
    private JFXComboBox<String> cbNivel;
    @FXML
    private JFXTextField tfUsuario;
    @FXML
    private JFXComboBox<String> cbStatus;
    @FXML
    private JFXPasswordField tfSenhaNova;
    @FXML
    private JFXPasswordField tfConfirmaSenha;
    @FXML
    private Button btLimpar;
    @FXML
    private Button btAtualizar;
    @FXML
    private JFXCheckBox checBoxFuncionarioInativo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> nivel = FXCollections.observableArrayList("ADMINISTRADOR", "MANUTENÇÃO", "USUARIO");
        cbNivel.setItems(nivel);

        ObservableList<String> status = FXCollections.observableArrayList("ATIVO", "INATIVO");
        cbStatus.setItems(status);

        carregaCbFuncionarios();

        tfUsuario.setEditable(false);

    }

    @FXML
    private void actionBtLimpar(ActionEvent event) {
        limparCampos();
    }

    @FXML
    private void actionBtAtualizar(ActionEvent event) {

        entrar();

    }

    @FXML
    private void actioncbFincionario(ActionEvent event) {
        Usuarios usuario = cbNome.getValue();

        if (usuario != null) {
            //abilita edição no tf login
            tfUsuario.setEditable(true);

            cbNivel.setValue(usuario.getNivel());
            tfUsuario.setText(usuario.getLogin());
            if (usuario.getStatusUsuario() == 1) {
                cbStatus.setValue("ATIVO");

            } else {
                cbStatus.setValue("INATIVO");
            }

            //desabilita edição no tf login
            tfUsuario.setEditable(false);
        }
    }

    @FXML
    private void actionchecBoxFuncionarioInativo(ActionEvent event) {

        carregaCbFuncionarios();
    }

    private List<Usuarios> buscarUsuariosAtivos() {
        String sql;

        if (checBoxFuncionarioInativo.isSelected()) {
            sql = "From Usuarios ";
        } else {
            sql = "From Usuarios where statususuario = 1  ";
        }

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            List<Usuarios> u = (List<Usuarios>) s.createQuery(sql).list();

            s.getTransaction().commit();

            return u;

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }

    public void carregaCbFuncionarios() {

        List<Usuarios> usuarios = buscarUsuariosAtivos();

        Collections.sort(usuarios);

        cbNome.getItems().clear();
        limparCampos();

        for (Usuarios u : usuarios) {

            cbNome.getItems().add(u);

        }
    }

    public boolean atualisarUsuario() {

        Usuarios u = cbNome.getValue();

        if (controleSenha()) {
            u.setSenha(tfSenhaNova.getText());
        }

        if (Util.getUsuarioLogado().getIdUsuario() == u.getIdUsuario() && !"ADMINISTRADOR".equals(cbNivel.getValue())) {

            Util.mensagem("Não é possivel alterar o nivel de acesso, usuario em uso", "Erro Usuario Logado");
            return false;

        } else {
            u.setNivel(cbNivel.getValue());
        }

        if ("INATIVO".equals(cbStatus.getValue())) {
            if (Util.getUsuarioLogado().getIdUsuario() != u.getIdUsuario()) {

                u.setStatusUsuario(0);

            } else {
                Util.mensagem("Não é possivel desativar, usuario em uso", "Erro Usuario Logado");
                return false;
            }
        } else {
            if (u.getFuncionario().getStatusFuncionario() == 1) {

                u.setStatusUsuario(1);

            } else {
                Util.mensagem("Funcionario não esta ativo, Ative o funcionario", "Funcionario inativo");
                return false;
            }

        }

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            s.update(u);

            s.getTransaction().commit();
            limparCampos();
            Util.mensagem("Atualizado com Sucesso", "Atualizado com Sucesso");
            return true;

        } catch (HibernateException ex) {
            String erro = ex.getMessage();
            Util.mensagem(erro, "Erro ao Atualizar");
            return false;

        }
    }

    public boolean controleSenha() {

        if (tfSenhaNova.getText().isEmpty() && tfConfirmaSenha.getText().isEmpty()) {
            return false;
        } else {

            if (!tfSenhaNova.getText().isEmpty()) {
                if (!tfConfirmaSenha.getText().isEmpty()) {

                    if (tfSenhaNova.getText().equals(tfConfirmaSenha.getText())) {
                        return true;
                    } else {
                        Util.mensagem("Senhas não são iguais", "Erro senha");
                    }

                } else {
                    Util.mensagem("Confirme a Senha", "Erro confirmar senha");
                }

            } else {
                Util.mensagem("Preencha a nova Senha", "Erro nova senha");
            }

        }

        return false;
    }

    public void limparCampos() {

        tfUsuario.setText("");
        tfSenhaNova.setText("");
        tfConfirmaSenha.setText("");
        cbNome.setValue(null);
        cbStatus.setValue("");
        cbNivel.setValue("");

    }

    @FXML
    private void onTecla(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {

            entrar();

        }
    }

    private void entrar() {

        if (cbNome.getValue() != null) {

            if (atualisarUsuario()) {

                carregaCbFuncionarios();

            }
        } else {
            Util.mensagem("Selecione um funcionario", "Funcionario invalido");
        }

    }

}
