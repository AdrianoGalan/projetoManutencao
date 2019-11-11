/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.usuario;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
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
import model.Funcionario;
import model.Pessoa;
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
public class FXMLTelaUsuarioCadastrarController implements Initializable {

    @FXML
    private JFXTextField tfUsuario;
    @FXML
    private JFXComboBox<String> cbNivel;
    @FXML
    private JFXComboBox<Funcionario> cbNome;
    @FXML
    private JFXPasswordField tfSenha;
    @FXML
    private JFXPasswordField tfConfirmaSenha;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btLimpar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> nivel = FXCollections.observableArrayList("ADMINISTRADOR", "MANUTENÇÃO", "USUARIO");
        cbNivel.setItems(nivel);

        List<Funcionario> funcionarios = buscarFuncionariosAtivos();

        for (Funcionario f : funcionarios) {

            cbNome.getItems().add(f);

        }

    }

    @FXML
    private void actionBtSalvar(ActionEvent event) {

        Funcionario f = cbNome.getValue();

        if (validarCampos()) {
            salvarUsuario(f);
        }
        

    }

    @FXML
    private void actionBtLimpar(ActionEvent event) {
        
        limparCampos();
    }

    public void salvarUsuario(Funcionario f) {

        Usuarios usuario = new Usuarios();

        usuario.setFuncionario(f);
        usuario.setLogin(tfUsuario.getText());
        usuario.setSenha(tfSenha.getText());
        usuario.setNivel(cbNivel.getValue());
        usuario.setStatusUsuario(1);

        try {
            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            s.save(usuario);
            s.getTransaction().commit();
            limparCampos();
            Util.mensagem("Salvo com Sucesso", "Salvo com Sucesso");

        } catch (HibernateException ex) {
            String erro = ex.getMessage();

            Util.mensagem(erro, "Erro ao Salvar");
        }

    }

    public List<Funcionario> buscarFuncionariosAtivos() {

        String sql = "From Funcionario Where Statusfuncionario = 1 ";

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            List<Funcionario> f = (List<Funcionario>) s.createQuery(sql).list();

            s.getTransaction().commit();

            return f;

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }

    private boolean validaLogin() {

        if (consultaLogin() != null) {
            Util.mensagem("Login já utilizado", "Login invalido");
            return false;
        } else {
            return true;
        }

    }

    public boolean validarFuncionario() {

        Funcionario f = cbNome.getValue();
        int idFuncionario = f.getIdFuncionario();

        Usuarios u = null;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Usuarios where id_funcionario_usuarios = :idfuncionario");
            q.setParameter("idfuncionario", idFuncionario);
            u = (Usuarios) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }
        if (u == null) {

            return true;

        } else {
            Util.mensagem("Funcionario ja cadastrado", "erro funcionario");
            return false;
        }

    }
    
     public Funcionario buscarPorMatricula(String matricula) {

        Funcionario f;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Funcionario where matricula = :matricula");
            q.setParameter("matricula", matricula);
            f = (Funcionario) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            Util.mensagem(e.getMessage(), "Erro...");
            return null;

        }

        return f;

    }

    public Usuarios consultaLogin() {

        String login = tfUsuario.getText();

        Usuarios u;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Usuarios where login = :login");
            q.setParameter("login", login);
            u = (Usuarios) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");
            return null;
        }

        return u;

    }

    public boolean validarCampos() {

        if (cbNome.getValue() != null) {
            if (!tfUsuario.getText().isEmpty()) {
                if (cbNivel.getValue() != null) {
                    if (!tfSenha.getText().isEmpty()) {
                        if (!tfConfirmaSenha.getText().isEmpty()) {
                            if (tfSenha.getText().equals(tfConfirmaSenha.getText())) {
                                if (validarFuncionario()) {
                                    if (validaLogin()) {
                                        return true;
                                    }

                                }

                            } else {
                                Util.mensagem("Senhas não são iguais", "Erro senha");
                            }

                        } else {
                            Util.mensagem("Confirme a Senha ", "Erro confirme senha");
                        }

                    } else {
                        Util.mensagem("Preencha o Senha", "Erro Senha");
                    }
                } else {
                    Util.mensagem("Seleciona nivel de acesso", "Erro nivel");

                }
            } else {
                Util.mensagem("Preencha o Login", "Erro Login");
            }
        } else {
            Util.mensagem("Selecione um funcionario", "Erro Funcionario");
        }

        return false;
    }

    public void limparCampos() {

        tfUsuario.setText("");
        tfSenha.setText("");
        tfConfirmaSenha.setText("");
        cbNome.setValue(null);
        cbNivel.setValue("");

    }
}
