/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFuncionario;
    private String matricula;
    private int statusFuncionario;
    private String funcao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pf_funcionario")
    private Pf pf;

    public Funcionario() {
    }

    public Funcionario(String matricula, int statusFuncionario, Pf pf) {
        this.matricula = matricula;
        this.statusFuncionario = statusFuncionario;
        this.pf = pf;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getStatusFuncionario() {
        return statusFuncionario;
    }

    public void setStatusFuncionario(int statusFuncionario) {
        this.statusFuncionario = statusFuncionario;
    }

    public Pf getPf() {
        return pf;
    }

    public void setPf(Pf pf) {
        this.pf = pf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return pf.getPessoa().getNome();

    }
}
