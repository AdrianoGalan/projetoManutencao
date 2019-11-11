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
public class Pj extends Pessoa{

    private int idPJ;
    private String cnpj;
    private String nomeContato;
    private int fkPessoaPj;

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

    public int getFkPessoaPj() {
        return fkPessoaPj;
    }

    public void setFkPessoaPj(int fkPessoaPj) {
        this.fkPessoaPj = fkPessoaPj;
    }



}
