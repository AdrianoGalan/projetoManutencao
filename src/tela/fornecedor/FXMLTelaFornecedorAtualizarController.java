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
import model.Pf;
import model.Pj;
import model.Telefone;
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
public class FXMLTelaFornecedorAtualizarController implements Initializable {

    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCnpj;
    @FXML
    private JFXTextField tfNomeContato;
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
    private Button btAtualizar;
    @FXML
    private Button btConsultar;

    private int idPessoa;
    private int idEndereco;
    private int idTelefone;
    private int idTelefone2;
    private int idEmail;
    private int idPj;
    @FXML
    private JFXCheckBox cbFabricanMaquina;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> tipoTel = FXCollections.observableArrayList("RES", "CEL", "COM");
        cbTipoTel1.setItems(tipoTel);
        cbTipoTel2.setItems(tipoTel);

        tfCnpj.setEditable(false);

        if (Util.getPjAtualizar() != null) {

            consultar(Util.getPjAtualizar().getCnpj());
        }

        Util.setPjAtualizar(null);

    }

    @FXML
    private void actionBtAtualizar(ActionEvent event) {

        if (!tfCnpj.getText().isEmpty()) {

            atualizaFornecedor();

        } else {
            Util.mensagem("Clique no botão consultar", "Consultar");
        }

    }

    @FXML
    private void actionBtConsultar(ActionEvent event) {

        consultar(tfCnpj.getText());
    }

    public void consultar(String cnpj) {

        Pj pj;
        Endereco e;
        List<Telefone> tels;
        Email email;

        if (!cnpj.isEmpty()) {

            pj = buscaPorCnpj(cnpj);
            if (pj != null) {

                int idPessoa = pj.getPessoa().getIdPessoa();

                e = buscaEndereco(idPessoa);
                tels = buscaTelefone(idPessoa);
                email = buscaEmail(idPessoa);

                povoarCampos(pj, e, tels, email);

            } else {
                Util.mensagem("CNPJ " + cnpj + " Não cadastrada", "CNPJ invalida");
            }

        } else {
            Util.mensagem("Preencha a CNPJ", "CNPJ");
        }

    }

    public Pj buscaPorCnpj(String cnpj) {

        Pj pj;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Pj where cnpj = :cnpj");
            q.setParameter("cnpj", cnpj);
            pj = (Pj) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            Util.mensagem(e.getMessage(), "Erro...");
            return null;

        }

        return pj;
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
            Util.mensagem(e.getMessage(), "Erro...");
            return null;

        }

        return en;
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
            Util.mensagem(e.getMessage(), "Erro...");

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
            Util.mensagem(e.getMessage(), "Erro...");
            return null;
        }

        return email;

    }

    public void povoarCampos(Pj pj, Endereco e, List<Telefone> tels, Email email) {
        tfCnpj.setEditable(true);

        tfNome.setText(pj.getPessoa().getNome());
        tfCnpj.setText(pj.getCnpj());
        tfRua.setText(e.getRua());
        tfNumeroRua.setText(e.getNumero());
        tfBairro.setText(e.getBairro());
        tfCidade.setText(e.getCidade());
        tfEmail1.setText(email.getEmail());
        tfPais.setText(e.getPais());
        tfNomeContato.setText(pj.getNomeContato());
        tfEstado.setText(e.getEstado());

        idPessoa = pj.getPessoa().getIdPessoa();
        idEndereco = e.getIdEndereco();
        idEmail = email.getIdEmail();
        idPj = pj.getIdPJ();

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

        tfCnpj.setEditable(false);

    }

    public Fabricante buscaFabricante(int idPj) {

        Fabricante fabricante;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("From Fabricante Where Id_pj_fabricante = :idPj");
            q.setParameter("idPj", idPj);
            fabricante = (Fabricante) q.uniqueResult();

            s.getTransaction().commit();

        } catch (Exception e) {
            Util.mensagem(e.getMessage(), "Erro...");
            return null;
        }

        return fabricante;

    }

    public boolean atualizaFornecedor() {

        boolean pjFabricante = false;
        if (buscaFabricante(idPj) != null) {
            pjFabricante = true;
        }

        Pj pj = new Pj();
        Pessoa p = new Pessoa();
        Endereco e = new Endereco();
        Telefone telefone1 = new Telefone();
        Telefone telefone2 = new Telefone();
        Email email = new Email();
        Fabricante fabricante = new Fabricante();

        p.setIdPessoa(idPessoa);
        pj.setIdPJ(idPj);
        pj.setNomeContato(tfNomeContato.getText());
        e.setIdEndereco(idEndereco);
        email.setIdEmail(idEmail);
        telefone1.setIdTelefone(idTelefone);
        telefone2.setIdTelefone(idTelefone2);

        p.setNome(tfNome.getText());
        pj.setCnpj(tfCnpj.getText());
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
            s.update(p);
            s.update(e);
            s.update(email);
            s.update(pj);
            s.update(telefone1);
            if (!tfNumeroTel2.getText().isEmpty()) {
                s.update(telefone2);
            }

            if (cbFabricanMaquina.isSelected()) {
                if (!pjFabricante) {
                    s.save(fabricante);
                }
            }

            s.getTransaction().commit();
            limparCampos();
            Util.mensagem("Atualizado com Sucesso", "Atualizado com Sucesso");
            return true;

        } catch (HibernateException ex) {
            String erro = ex.getMessage();
            Util.mensagem(erro, "Erro ao Atualizar");
            System.out.println(erro);
            return false;
        }

    }

    public void limparCampos() {

        tfNome.setText("");
        tfCnpj.setText("");
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
        tfPais.setText("");

    }

}
