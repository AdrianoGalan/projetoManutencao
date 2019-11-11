/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.equipe;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Funcionario;
import model.Pessoa;
import model.Pf;
import model.Telefone;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import tela.FXMLTelaEquipeController;
import tela.FXMLTelaPrincipalController;
import util.HibernateUtil;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaEquipeConsultarController implements Initializable {

    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCpf;
    @FXML
    private JFXTextField tfMatricula;
    @FXML
    private JFXComboBox<String> cbFuncao;
    @FXML
    private JFXListView<Funcionario> lvfuncionario;
    @FXML
    private Button btAtualizar;
    @FXML
    private Button btConsultar;

    private Funcionario funcionario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> funcao = FXCollections.observableArrayList("ELETRICISTA", "MECANICO", "CALDEIREIRO", "PEDREIRO");
        cbFuncao.setItems(funcao);

        lvfuncionario.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    private void actionBtAtualizar(ActionEvent event) {

        AnchorPane apTelaEquipe = Util.getTelaEquipeCarregada().getApTelaEquipe();

        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/tela/equipe/FXMLTelaEquipeAtualizar.fxml"));

            AnchorPane.setTopAnchor(a, 0.0);
            AnchorPane.setLeftAnchor(a, 0.0);
            AnchorPane.setRightAnchor(a, 0.0);
            AnchorPane.setBottomAnchor(a, 0.0);

            apTelaEquipe.getChildren().setAll(a);

            Button btConsulta = Util.getTelaEquipeCarregada().getBtConsultar();
            Button btAtualiza = Util.getTelaEquipeCarregada().getBtAtualizar();

            btConsulta.setStyle("-fx-background-color: #000000 ;");
            btAtualiza.setStyle("-fx-background-color: #2a2a2a ;");

        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void actionBtConsultar(ActionEvent event) {

        funcionario = lvfuncionario.getSelectionModel().getSelectedItem();

        povoarCampos(funcionario);
        
        //Salva funcionario consultado
        Util.setFuncionarioAtualizar(funcionario);
        // carrega matricula para tela atualizar
        Util.setMatricula(tfMatricula.getText());

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
            mensagem(e.getMessage(), "Erro...");
            return null;

        }

        return f;

    }

    public Funcionario buscaFuncionarioPorIdPf(int idPf) {

        Funcionario funcionario;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("From Funcionario Where Id_pf_funcionario = :idPessoa");
            q.setParameter("idPessoa", idPf);
            funcionario = (Funcionario) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            mensagem(e.getMessage(), "Erro...");
            return null;
        }

        return funcionario;

    }

    public Pf buscaPfPorIdPessoa(int idpessoa) {

        Pf pf;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("From Pf Where Id_pessoa_pf = :idPessoa");
            q.setParameter("idPessoa", idpessoa);
            pf = (Pf) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            mensagem(e.getMessage(), "Erro...");
            return null;
        }

        return pf;

    }

    public void povoarListView(List<Pessoa> pessoa) {

        lvfuncionario.getItems().clear();

        for (Pessoa p : pessoa) {

            Funcionario f;
            Pf pf;

            if (p != null) {
                pf = buscaPfPorIdPessoa(p.getIdPessoa());
                if (pf != null) {
                    f = buscaFuncionarioPorIdPf(pf.getIdPf());
                    lvfuncionario.getItems().add(f);
                }

            }

        }

    }

    public List<Pessoa> buscaNome(String nome) {

        String sql = "From Pessoa Where Nome Like '" + "%" + nome + "%" + "'";

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            List<Pessoa> pessoa = (List<Pessoa>) s.createQuery(sql).list();

            s.getTransaction().commit();

            return pessoa;

        } catch (Exception e) {
            mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }

    public void povoarCampos(Funcionario f) {

        tfNome.setText(f.getPf().getPessoa().getNome());
        tfCpf.setText(f.getPf().getCpf());
        tfMatricula.setText(f.getMatricula());
        cbFuncao.setValue(f.getFuncao());

    }

    @FXML
    private void cliqueBotaoTeclado(KeyEvent event) {

        limparCampos();

        List<Pessoa> pessoa = buscaNome(tfNome.getText());
        povoarListView(pessoa);
    }

    public void limparCampos() {

        // tfNome.setText("");
        tfCpf.setText("");
        tfMatricula.setText("");
        cbFuncao.setValue("");

    }

    public void mensagem(String mgn, String titulo) {

        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setHeaderText(mgn);
        dialogo.setTitle(titulo);
        dialogo.showAndWait();
    }

}
