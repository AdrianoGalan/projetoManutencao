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
public class Pf extends Pessoa {

    private int idPf;
    private String sexo;
    private String cpf;
    private int fkPessoaPf;

    public Pf() {
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

    public int getFkPessoaPf() {
        return fkPessoaPf;
    }

    public void setFkPessoaPf(int fkPessoaPf) {
        this.fkPessoaPf = fkPessoaPf;
    }

}
