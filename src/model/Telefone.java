/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author drico
 */
@Entity
@Table(name = "telefone")
public class Telefone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTelefone;
    private String tipo;
    private String numero;
    private String ddd;
    private String ddi;
    //private int fkPessoaTelefone;
    @OneToOne
    @JoinColumn(name = "id_pessoa_telefone")
    private Pessoa pessoa;

    public Telefone() {
    }


    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

//    public int getIdPessoaTelefone() {
//        return fkPessoaTelefone;
//    }
//
//    public void setIdPessoaTelefone(int idPessoaTelefone) {
//        this.fkPessoaTelefone = idPessoaTelefone;
//    }
//
//    public int getFkPessoaTelefone() {
//        return fkPessoaTelefone;
//    }
//
//    public void setFkPessoaTelefone(int fkPessoaTelefone) {
//        this.fkPessoaTelefone = fkPessoaTelefone;
//    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
