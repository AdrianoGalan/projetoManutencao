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
public class Equipamento {

    private int idEquipamento;
    private String sigla;
    private String tipo;
    private String modelo;
    private String matriculaEquipamento;
    private int statusEquipamento;
    private int fkFabricanteEquipamento;
    private int fkAreaEquipamento;

    public Equipamento() {
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatriculaEquipamento() {
        return matriculaEquipamento;
    }

    public void setMatriculaEquipamento(String matriculaEquipamento) {
        this.matriculaEquipamento = matriculaEquipamento;
    }

    public int getStatusEquipamento() {
        return statusEquipamento;
    }

    public void setStatusEquipamento(int statusEquipamento) {
        this.statusEquipamento = statusEquipamento;
    }

    public int getFkFabricanteEquipamento() {
        return fkFabricanteEquipamento;
    }

    public void setFkFabricanteEquipamento(int fkFabricanteEquipamento) {
        this.fkFabricanteEquipamento = fkFabricanteEquipamento;
    }

    public int getFkAreaEquipamento() {
        return fkAreaEquipamento;
    }

    public void setFkAreaEquipamento(int fkAreaEquipamento) {
        this.fkAreaEquipamento = fkAreaEquipamento;
    }

}
