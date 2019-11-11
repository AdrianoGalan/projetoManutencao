/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.fornecedor;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Funcionario;
import model.Pessoa;
import model.Pf;
import model.Pj;
import org.hibernate.Query;
import org.hibernate.Session;
import tela.FXMLTelaPrincipalController;
import util.HibernateUtil;
import util.Util;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaFornecedorConsultarController implements Initializable {

    @FXML
    private JFXTextField tfNome;
    @FXML
    private Button btAtualizar;
    @FXML
    private Button btConsultar;
    @FXML
    private JFXTextField tfCnpj;
    @FXML
    private JFXTextField tfContato;
    @FXML
    private JFXListView<Pj> lvPj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lvPj.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void cliqueBotaoTeclado(KeyEvent event) {

        limparCampos();

        List<Pessoa> pessoa = buscaNome(tfNome.getText());
        povoarListView(pessoa);
    }

    @FXML
    private void actionBtAtualizar(ActionEvent event) {
        
         AnchorPane apTelaEquipe = Util.getTelaFornecedorCarregada().getApTelaEquipe();

        try {                                                                   
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/tela/fornecedor/FXMLTelaFornecedorAtualizar.fxml"));

            AnchorPane.setTopAnchor(a, 0.0);
            AnchorPane.setLeftAnchor(a, 0.0);
            AnchorPane.setRightAnchor(a, 0.0);
            AnchorPane.setBottomAnchor(a, 0.0);

            apTelaEquipe.getChildren().setAll(a);

            Button btConsulta = Util.getTelaFornecedorCarregada().getBtConsultar();
            Button btAtualiza = Util.getTelaFornecedorCarregada().getBtAtualizar();

            btConsulta.setStyle("-fx-background-color: #000000 ;");
            btAtualiza.setStyle("-fx-background-color: #2a2a2a ;");

        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void actionBtConsultar(ActionEvent event) {
        Pj pj = new Pj();

        pj = lvPj.getSelectionModel().getSelectedItem();

        povoarCampos(pj);

        //Salva funcionario consultado
        Util.setPjAtualizar(pj);
        // carrega matricula para tela atualizar
        //Util.setMatricula(tfMatricula.getText());

    }

    private void povoarListView(List<Pessoa> pessoa) {

        lvPj.getItems().clear();

        for (Pessoa p : pessoa) {

            Pj pj;

            if (p != null) {
                pj = buscaPjPorIdPessoa(p.getIdPessoa());
                if (pj != null) {

                    lvPj.getItems().add(pj);
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
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }

    private Pj buscaPjPorIdPessoa(int idPessoa) {

        Pj pj;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("From Pj Where Id_pessoa_pj = :idPessoa");
            q.setParameter("idPessoa", idPessoa);
            pj = (Pj) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            Util.mensagem(e.getMessage(), "Erro...");
            return null;
        }

        return pj;

    }

    public void limparCampos() {

        // tfNome.setText("");
        tfCnpj.setText("");
        tfContato.setText("");

    }

    private void povoarCampos(Pj pj) {

        if (pj != null) {

            tfNome.setText(pj.getPessoa().getNome());
            tfCnpj.setText(pj.getCnpj());
            tfContato.setText(pj.getNomeContato());

        }

    }

}
