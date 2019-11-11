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
@Table(name = "usuarios")
public class Usuarios implements Serializable, Comparable<Usuarios> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuario;
    private String login;
    private String senha;
    private String nivel;
    private int statusUsuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_funcionario_usuarios")
    private Funcionario funcionario;

    public Usuarios() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(int statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return funcionario.getPf().getPessoa().getNome();
    }

    @Override
    public int compareTo(Usuarios o) {
        
         return funcionario.getPf().getPessoa().getNome().compareTo(o.getFuncionario().getPf().getPessoa().getNome());
       
    }





    
}
