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
public class Telefone {
    
    private int idTelefone;
    private String tipo;
    private String numero;
    private String ddd;
    private String ddi;
    private int fkPessoaTelefone;

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

    public int getIdPessoaTelefone() {
        return fkPessoaTelefone;
    }

    public void setIdPessoaTelefone(int idPessoaTelefone) {
        this.fkPessoaTelefone = idPessoaTelefone;
    }
    
    
    
}
