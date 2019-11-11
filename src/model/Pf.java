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
@Table(name="pf")
public class Pf  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPf;
    private String sexo;
    private String cpf;
   // private int fkPessoaPf;
    @OneToOne
    @JoinColumn(name="id_pessoa_pf")
    private Pessoa pessoa;

    public Pf() {
    }

  

    public Pf(String sexo, String cpf, Pessoa pessoa) {
        this.sexo = sexo;
        this.cpf = cpf;
        this.pessoa = pessoa;
    }
    

    public int getIdPf() {
        return idPf;
    }

    public void setIdPf(int idPf) {
        this.idPf = idPf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

//    public int getFkPessoaPf() {
//        return fkPessoaPf;
//    }
//
//    public void setFkPessoaPf(int fkPessoaPf) {
//        this.fkPessoaPf = fkPessoaPf;
//    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    

}
