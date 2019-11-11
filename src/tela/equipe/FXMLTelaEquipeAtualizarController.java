/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela.equipe;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.Email;
import model.Endereco;
import model.Funcionario;
import model.Pessoa;
import model.Pf;
import model.Telefone;
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
public class FXMLTelaEquipeAtualizarController implements Initializable {

    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCpf;
    @FXML
    private JFXTextField tfMatricula;
    @FXML
    private JFXComboBox<String> cbSexo;
    @FXML
    private JFXComboBox<String> cbFuncao;
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
    private Button btAtualizar;
    @FXML
    private Button btConsultar;
    @FXML
    private JFXComboBox<String> cbStatus;

    private String matriculaSalva;

    private int idPessoa;
    private int idEndereco;
    private int idTelefone;
    private int idTelefone2;
    private int idEmail;
    private int idFuncionario;
    private int idPf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> sexo = FXCollections.observableArrayList("F", "M");
        cbSexo.setItems(sexo);

        ObservableList<String> status = FXCollections.observableArrayList("ATIVO", "INATIVO");
        cbStatus.setItems(status);

        ObservableList<String> funcao = FXCollections.observableArrayList("ELETRICISTA", "MECANICO", "CALDEIREIRO", "PEDREIRO");
        cbFuncao.setItems(funcao);

        ObservableList<String> estado = FXCollections.observableArrayList("SP", "RJ", "AC", "AL", "AP", "AM", "BA", "CE", "DF",
                "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RN", "RS", "RO", "RR", "SC", "SE", "TO");
        cbEstado.setItems(estado);

        ObservableList<String> tipoTel = FXCollections.observableArrayList("RES", "CEL", "COM");
        cbTipoTel1.setItems(tipoTel);
        cbTipoTel2.setItems(tipoTel);

        tfCpf.setEditable(false);
        matriculaSalva = Util.getMatricula();

        // testa matricula tela consultar  
        if (matriculaSalva != null) {
            consultar(matriculaSalva);
        }

        System.out.println(matriculaSalva);

        Util.setMatricula(null);

    }

    @FXML
    private void actionBtAtualizar(ActionEvent event) {

        if (!tfCpf.getText().isEmpty()) {

            atualizaFuncionario();

        } else {
            mensagem("Clique no botão consultar", "Consultar");
        }

    }

    @FXML
    private void actionBtConsultar(ActionEvent event) {

        consultar(tfMatricula.getText());

    }

    public void consultar(String matricula) {

        Funcionario f;
        Endereco e;
        List<Telefone> tels;
        Email email;

        if (!matricula.isEmpty()) {

            f = buscarPorMatricula(matricula);
            if (f != null) {

                int idPessoa = f.getPf().getPessoa().getIdPessoa();

                e = buscaEndereco(idPessoa);
                tels = buscaTelefone(idPessoa);
                email = buscaEmail(idPessoa);

                povoarCampos(f, e, tels, email);

            } else {
                mensagem("Matricula " + tfMatricula.getText() + " Não cadastrada", "Matricula invalida");
            }

        } else {
            mensagem("Preencha a matricula", "MATRICULA");
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
            mensagem(e.getMessage(), "Erro...");
            return null;

        }

        return f;

    }

    public Endereco buscaEndereco(int idPessoa) {

        Endereco en;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Endereco where Id_pessoa_endereco = :idPessoa");
            q.setParameter("idPessoa", idPessoa);
            en = (Endereco) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            mensagem(e.getMessage(), "Erro...");
            return null;

        }

        return en;
    }

    public Usuarios buscaUsuario(int idFuncionario) {

        Usuarios u;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Usuarios where Id_funcionario_usuarios = :idFuncionario");
            q.setParameter("idFuncionario", idFuncionario);
            u = (Usuarios) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            mensagem(e.getMessage(), "Erro...");
            return null;

        }

        return u;
    }

    public List<Telefone> buscaTelefone(int idPessoa) {

        String sql = "From Telefone Where Id_pessoa_telefone = '" + idPessoa + "'";

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            List<Telefone> telefones = (List<Telefone>) s.createQuery(sql).list();

            s.getTransaction().commit();

            return telefones;

        } catch (Exception e) {
            mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }

    public Email buscaEmail(int idPessoa) {

        Email email;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("From Email Where Id_pessoa_email = :idPessoa");
            q.setParameter("idPessoa", idPessoa);
            email = (Email) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            mensagem(e.getMessage(), "Erro...");
            return null;
        }

        return email;

    }

    public void povoarCampos(Funcionario f, Endereco e, List<Telefone> tels, Email email) {
        tfCpf.setEditable(true);

        tfNome.setText(f.getPf().getPessoa().getNome());
        tfCpf.setText(f.getPf().getCpf());
        tfMatricula.setText(f.getMatricula());
        tfRua.setText(e.getRua());
        tfNumeroRua.setText(e.getNumero());
        tfBairro.setText(e.getBairro());
        tfCidade.setText(e.getCidade());
        tfEmail1.setText(email.getEmail());
        cbSexo.setValue(f.getPf().getSexo());
        cbEstado.setValue(e.getEstado());
        cbFuncao.setValue(f.getFuncao());
        if (f.getStatusFuncionario() == 1) {
            cbStatus.setValue("ATIVO");
        } else {

            cbStatus.setValue("INATIVO");

        }

        idPessoa = f.getPf().getPessoa().getIdPessoa();
        idEndereco = e.getIdEndereco();
        idEmail = email.getIdEmail();
        idFuncionario = f.getIdFuncionario();
        idPf = f.getPf().getIdPf();

        for (int i = 0; i < tels.size(); i++) {

            switch (i) {
                case 0:
                    tfDDD1.setText(tels.get(i).getDdd());
                    tfNumeroTel1.setText(tels.get(i).getNumero());
                    cbTipoTel1.setValue(tels.get(i).getTipo());
                    tfDDD2.setText("");
                    tfNumeroTel2.setText("");
                    cbTipoTel2.setValue("");
                    idTelefone = tels.get(i).getIdTelefone();
                    break;
                case 1:
                    tfDDD2.setText(tels.get(i).getDdd());
                    tfNumeroTel2.setText(tels.get(i).getNumero());
                    cbTipoTel2.setValue(tels.get(i).getTipo());
                    idTelefone2 = tels.get(i).getIdTelefone();
                    break;

            }

        }

        tfCpf.setEditable(false);

    }

    public boolean atualizaFuncionario() {

        Funcionario f = new Funcionario();
        Pf pf = new Pf();
        Pessoa p = new Pessoa();
        Endereco e = new Endereco();
        Telefone telefone1 = new Telefone();
        Telefone telefone2 = new Telefone();
        Email email = new Email();

        p.setIdPessoa(idPessoa);
        f.setIdFuncionario(idFuncionario);
        pf.setIdPf(idPf);
        e.setIdEndereco(idEndereco);
        email.setIdEmail(idEmail);
        telefone1.setIdTelefone(idTelefone);
        telefone2.setIdTelefone(idTelefone2);

        System.out.println(cbStatus.getValue());

        if ("INATIVO".equals(cbStatus.getValue())) {

            f.setStatusFuncionario(0);
            if (!atualizarUsuario()) {
                return false;
            }

        } else {
            f.setStatusFuncionario(1);
        }

        p.setNome(tfNome.getText());
        pf.setCpf(tfCpf.getText());
        f.setMatricula(tfMatricula.getText());
        f.setFuncao(cbFuncao.getValue());
        pf.setSexo(cbSexo.getValue());
        e.setRua(tfRua.getText());
        e.setNumero(tfNumeroRua.getText());
        e.setBairro(tfBairro.getText());
        e.setEstado(cbEstado.getValue());
        e.setCidade(tfCidade.getText());
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
            s.update(p);
            s.update(e);
            s.update(email);
            s.update(pf);
            s.update(telefone1);
            if (!tfNumeroTel2.getText().isEmpty()) {
                s.update(telefone2);
            }
            s.update(f);

            s.getTransaction().commit();
            limparCampos();
            mensagem("Atualizado com Sucesso", "Atualizado com Sucesso");
            return true;

        } catch (HibernateException ex) {
            String erro = ex.getMessage();
            mensagem(erro, "Erro ao Atualizar");
            System.out.println(erro);
            return false;
        }

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
        cbStatus.setValue("");

    }

    public void mensagem(String mgn, String titulo) {

        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setHeaderText(mgn);
        dialogo.setTitle(titulo);
        dialogo.showAndWait();
    }

    private boolean atualizarUsuario() {

        Usuarios u = buscaUsuario(idFuncionario);

        if (Util.getUsuarioLogado().getIdUsuario() != u.getIdUsuario()) {

            u.setStatusUsuario(0);

            try {

                Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                s.beginTransaction();
                s.update(u);

                s.getTransaction().commit();

                //Util.mensagem("Atualizado com Sucesso", "Atualizado com Sucesso");
                return true;

            } catch (HibernateException ex) {
                String erro = ex.getMessage();
                Util.mensagem(erro, "Erro ao Atualizar usuario");
                return false;

            }

        } else {
            Util.mensagem("Não é possivel desativar, usuario em uso", "Erro Usuario Logado");
            return false;

        }

    }
}
