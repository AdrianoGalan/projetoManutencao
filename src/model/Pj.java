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
@Table(name="pj")
public class Pj implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPJ;
    private String cnpj;
    private String nomeContato;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @OneToOne
    @JoinColumn(name = "id_pessoa_pj")
    private Pessoa pessoa;

    public Pj() {
    }

    public int getIdPJ() {
        return idPJ;
    }

    public void setIdPJ(int idPJ) {
        this.idPJ = idPJ;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

   @Override
    public String toString() {
        return pessoa.getNome();

    }

}
