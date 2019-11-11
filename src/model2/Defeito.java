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
public class Defeito {

    private int idDefeito;
    private String titulo;
    private String desDefeito;
    private String desReparo;
    private int fkEquipamentoDefeito;

    public Defeito() {
    }

    public int getIdDefeito() {
        return idDefeito;
    }

    public void setIdDefeito(int idDefeito) {
        this.idDefeito = idDefeito;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesDefeito() {
        return desDefeito;
    }

    public void setDesDefeito(String desDefeito) {
        this.desDefeito = desDefeito;
    }

    public String getDesReparo() {
        return desReparo;
    }

    public void setDesReparo(String desReparo) {
        this.desReparo = desReparo;
    }

    public int getFkEquipamentoDefeito() {
        return fkEquipamentoDefeito;
    }

    public void setFkEquipamentoDefeito(int fkEquipamentoDefeito) {
        this.fkEquipamentoDefeito = fkEquipamentoDefeito;
    }

}
