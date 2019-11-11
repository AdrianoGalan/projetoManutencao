/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.equipe;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ferramenta.ValidaCpf;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javax.persistence.Query;
import model.Email;
import model.Endereco;
import model.Funcionario;
import model.Pessoa;
import model.Pf;
import model.Telefone;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * FXML Controller class
 *
 * @author drico
 */
public class FXMLTelaEquipeCadastrarController implements Initializable {

    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCpf;
    @FXML
    private JFXTextField tfMatricula;
    @FXML
    private JFXComboBox<String> cbSexo;
    @FXML
    private JFXTextField tfRua;
    @FXML
    private JFXTextField tfNumeroRua;
    @FXML
    private JFXTextField tfBairro;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private JFXTextField tfCidade;
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
    private JFXComboBox<String> cbFuncao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> sexo = FXCollections.observableArrayList("F", "M");
        cbSexo.setItems(sexo);

        ObservableList<String> funcao = FXCollections.observableArrayList("ELETRICISTA", "MECANICO", "CALDEIREIRO", "PEDREIRO");
        cbFuncao.setItems(funcao);

        ObservableList<String> estado = FXCollections.observableArrayList("SP", "RJ", "AC", "AL", "AP", "AM", "BA", "CE", "DF",
                "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RN", "RS", "RO", "RR", "SC", "SE", "TO");
        cbEstado.setItems(estado);

        ObservableList<String> tipoTel = FXCollections.observableArrayList("RES", "CEL", "COM");
        cbTipoTel1.setItems(tipoTel);
        cbTipoTel2.setItems(tipoTel);

    }

    @FXML
    private void actionBtSalvar(ActionEvent event) {

        if (validarCampos()) {
            salvarFuncionario();
        }
    }

    @FXML
    private void actionBtLimpar(ActionEvent event) {

        limparCampos();
    }

    public void salvarFuncionario() {

        Funcionario f = new Funcionario();
        Pf pf = new Pf();
        Pessoa p = new Pessoa();
        Endereco e = new Endereco();
        Telefone telefone1 = new Telefone();
        Telefone telefone2 = new Telefone();
        Email email = new Email();

        p.setNome(tfNome.getText());
        pf.setCpf(tfCpf.getText());
        f.setMatricula(tfMatricula.getText());
        f.setFuncao(cbFuncao.getValue());
        f.setStatusFuncionario(1);
        pf.setSexo(cbSexo.getValue());
        e.setRua(tfRua.getText());
        e.setNumero(tfNumeroRua.getText());
        e.setBairro(tfBairro.getText());
        e.setEstado(cbEstado.getValue());
        e.setCidade(tfCidade.getText());
        e.setPais("Brasil");
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
        pf.setPessoa(p);
        email.setPessoa(p);
        telefone1.setPessoa(p);
        telefone2.setPessoa(p);
        f.setPf(pf);

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            s.save(p);
            s.save(e);
            s.save(email);
            s.save(pf);
            s.save(telefone1);
            if (!tfNumeroTel2.getText().isEmpty()) {
                s.save(telefone2);
            }
            s.save(f);

            s.getTransaction().commit();
            limparCampos();
            mensagem("Salvo com Sucesso", "Salvo com Sucesso");

        } catch (HibernateException ex) {
            String erro = ex.getMessage();
            mensagem(erro, "Erro ao Salvar");
        }

        /* f.setTelefone1(telefone1);

        telefone2.setDdd(tfDDD2.getText());
        telefone2.setNumero(tfNumeroTel2.getText());
        telefone2.setTipo(cbTipoTel2.getValue());

        f.setTelefone2(telefone2);

        email.setEmail(tfEmail1.getText());
        f.setEmail(email);

        FuncionarioDao fDao = new FuncionarioDao();

        if (fDao.salvarFucnionario1Telefone(f, telefone1, telefone2, email)) {

            mensagem("Salvo com sucesso", "Salvo com sucesso");
            limparCampos();
        } else {

            mensagem("Erroa o Salvar", "Erro...");
        }*/
    }

    public void limparCampos() {

        tfNome.setText("");
        tfCpf.setText("");
        tfMatricula.setText("");
        tfRua.setText("");
        tfNumeroRua.setText("");
        tfBairro.setText("");
        tfCidade.setText("");
        tfDDD1.setText("");
        tfNumeroTel1.setText("");
        tfEmail1.setText("");
        tfDDD2.setText("");
        tfNumeroTel2.setText("");
        cbSexo.setValue("");
        cbEstado.setValue("");
        cbTipoTel1.setValue("");
        cbTipoTel2.setValue("");
        cbFuncao.setValue("");

    }

    public boolean validaCpf() {

        String cpf = tfCpf.getText();;

        //varifica cpf em branco
        if (!tfCpf.getText().equalsIgnoreCase("")) {

            //varifica cpf valido
            if (ValidaCpf.isCPF(cpf)) {

                if (!verificaCpfUsado(cpf)) {

                    return true;
                }

            } else {
                mensagem("Cpf invalido", "Cpf invalido");
                return false;
            }

        } else {
            mensagem("Por favor preencha o cpf", "Preencha o CPF");
            return false;
        }

        return false;
    }

    public boolean validarCampos() {

        if (validarMome()) {
            return false;
        } else if (!validaCpf()) {
            return false;
        } else if (validarMatricula()) {
            return false;
        } else if (cbFuncao.getValue() == null) {
            mensagem("Escolha uma função", "Preencha Função");
            return false;
        } else if (tfNumeroTel1.getText().isEmpty()) {
            mensagem("Preencha numero Telefone", "preencha telefone");
            return false;
        }

        return true;
    }

    //retorna true para cpf já cadastrado
    //retorna false para cpf não cadastrado
    public boolean verificaCpfUsado(String cpf) {

        String sql = "From Pf Where Cpf = '" + cpf + "'";

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            List<Pf> lista = (List<Pf>) s.createQuery(sql).list();
            s.getTransaction().commit();

            if (lista.isEmpty()) {
                return false;
            } else {
                mensagem("Cpf Ja Cadastrado", "Cpf ja cadastrado");
                return true;
            }

        } catch (HibernateException e) {
        }
        return true;
    }

    public void mensagem(String mgn, String titulo) {

        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setHeaderText(mgn);
        dialogo.setTitle(titulo);
        dialogo.showAndWait();
    }

    //retorna true para matricula já cadastrado
    //retorna false para matricula cadastrado
    private boolean validarMatricula() {

        String matricula = tfMatricula.getText();

        if (!matricula.isEmpty()) {

            String sql = "From Funcionario Where Matricula = '" + matricula + "'";

            try {

                Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                s.beginTransaction();

                List<Funcionario> lista = (List<Funcionario>) s.createQuery(sql).list();
                s.getTransaction().commit();

                if (lista.isEmpty()) {
                    return false;
                } else {
                    mensagem("Matricula Ja Cadastrada", "Matricula ja cadastrada");
                    return true;
                }

            } catch (HibernateException e) {
            }

        } else {
            mensagem("Por  favor preencha a matricula", "preencha matricula");
            return true;

        }

        return false;

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
                    mensagem("Nome Ja Cadastrada", "Nome ja cadastrada");
                    return true;
                }

            } catch (HibernateException e) {
            }

        } else {
            mensagem("Por  favor preencha a Nome", "preencha Nome");
            return true;

        }

        return false;

    }

}
