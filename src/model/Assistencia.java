/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author drico
 */
public class Assistencia {

    private int idAssistencia;
    private String nomeAssistencia;
    private String nomeContato;
    private int fkFabricanteAssistencia;

    public Assistencia() {
    }

    public int getIdAssistencia() {
        return idAssistencia;
    }

    public void setIdAssistencia(int idAssistencia) {
        this.idAssistencia = idAssistencia;
    }

    public String getNomeAssistencia() {
        return nomeAssistencia;
    }

    public void setNomeAssistencia(String nomeAssistencia) {
        this.nomeAssistencia = nomeAssistencia;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public int getFkFabricanteAssistencia() {
        return fkFabricanteAssistencia;
    }

    public void setFkFabricanteAssistencia(int fkFabricanteAssistencia) {
        this.fkFabricanteAssistencia = fkFabricanteAssistencia;
    }

}
