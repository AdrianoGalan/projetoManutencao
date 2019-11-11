/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Usuarios;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaLoginController implements Initializable {

    @FXML
    private JFXTextField tfUsuario;
    @FXML
    private JFXPasswordField tfSenha;
    @FXML
    private JFXButton btEntrar;
    @FXML
    private JFXButton btFechar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }

    @FXML
    private void actionBtEntrar(ActionEvent event) {
    
        entrar();
    
    }

    @FXML
    private void actionBtFechar(ActionEvent event) {

        System.exit(0);
    }

    
    public Usuarios validaUsuario() {

        String login = tfUsuario.getText();
        Usuarios u;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();

            s.beginTransaction();

            q = s.createQuery("from Usuarios where login = :login and statususuario = 1");
            q.setParameter("login", login);
            u = (Usuarios) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {

            Util.mensagem(e.getMessage(), "Erro...");
            return null;

        }

        return u;
    }

    public boolean validaSenha(Usuarios u) {

        if (u.getSenha().equals(tfSenha.getText())) {
            return true;
        } else {
            Util.mensagem("Senha invalida", "Erro Senha");

            return false;
        }

    }

    public void iniciarTela(Usuarios u) {

        String nivel = u.getNivel();
        String nomeTela = "FXML TelaLogin.fxml";

        switch (nivel) {

            case "ADMINISTRADOR":
                nomeTela = "FXMLTelaPrincipal.fxml";
                break;
            case "USUARIO":
                nomeTela = "FXMLTelaPrincipalUsuario.fxml";
                break;
            case "MANUTENÇÃO":
                nomeTela = "FXMLTelaPrincipalManutencao.fxml";
                break;

        }

        try {

            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource(nomeTela));

            Scene scene = new Scene(root);

            stage.setScene(scene);

            //inicia maiximizado
            stage.setMaximized(true);
            //retira botão de fechar
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            // fecha tela do login
            btEntrar.getScene().getWindow().hide();

        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ontecla(KeyEvent event) {
                
        if (event.getCode() == KeyCode.ENTER) {

            entrar();

        }
    }

    private void entrar() {
    
            Usuarios u;

        if (!tfUsuario.getText().isEmpty()) {
            if (!tfSenha.getText().isEmpty()) {
                u = validaUsuario();
                if (u != null) {
                    if (validaSenha(u)) {
                        Util.setUsuarioLogado(u);
                        iniciarTela(u);

                    }

                } else {
                    Util.mensagem("Usuario invalido", "erro no usuario");
                }

            } else {
                Util.mensagem("Preencha senha", "senha invalido");
            }
        } else {
            Util.mensagem("Preencha usuario", "usuario invalido");
        }
    
    }

   

}
