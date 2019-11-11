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
public class Funcionario extends Pf {

    private int idFuncionario;
    private String matricula;
    private int statusFuncionario;
    private int fkPfFuncionario;

    public Funcionario() {
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

    public int getFkPfFuncionario() {
        return fkPfFuncionario;
    }

    public void setFkPfFuncionario(int fkPfFuncionario) {
        this.fkPfFuncionario = fkPfFuncionario;
    }

}
