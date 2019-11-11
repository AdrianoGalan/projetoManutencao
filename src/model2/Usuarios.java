/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import model.*;

/**
 *
 * @author drico
 */
public class Usuarios {

    private int idUsuarios;
    private String usuario;
    private String senha;
    private String nivel;
    private int fkFuncionarioUsuarios;

    public Usuarios() {
    }

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public int getFkFuncionarioUsuarios() {
        return fkFuncionarioUsuarios;
    }

    public void setFkFuncionarioUsuarios(int fkFuncionarioUsuarios) {
        this.fkFuncionarioUsuarios = fkFuncionarioUsuarios;
    }

}
