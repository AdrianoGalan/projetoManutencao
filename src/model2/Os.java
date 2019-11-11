/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import model.*;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author drico
 */
public class Os {

    private int idOs;
    private String numeroOs;
    private String prioridade;
    private Date dataInicio;
    private Date dataInicioReparo;
    private Date dataFim;
    private Date dataFimReparo;
    private Time horaInicio;
    private Time horaInicioReparo;
    private Time horaFim;
    private Time horaFimReparo;
    private Defeito defeito;

    public Os() {
    }

    public int getIdOs() {
        return idOs;
    }

    public void setIdOs(int idOs) {
        this.idOs = idOs;
    }

    public String getNumeroOs() {
        return numeroOs;
    }

    public void setNumeroOs(String numeroOs) {
        this.numeroOs = numeroOs;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataInicioReparo() {
        return dataInicioReparo;
    }

    public void setDataInicioReparo(Date dataInicioReparo) {
        this.dataInicioReparo = dataInicioReparo;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataFimReparo() {
        return dataFimReparo;
    }

    public void setDataFimReparo(Date dataFimReparo) {
        this.dataFimReparo = dataFimReparo;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraInicioReparo() {
        return horaInicioReparo;
    }

    public void setHoraInicioReparo(Time horaInicioReparo) {
        this.horaInicioReparo = horaInicioReparo;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    public Time getHoraFimReparo() {
        return horaFimReparo;
    }

    public void setHoraFimReparo(Time horaFimReparo) {
        this.horaFimReparo = horaFimReparo;
    }

    public Defeito getDefeito() {
        return defeito;
    }

    public void setDefeito(Defeito defeito) {
        this.defeito = defeito;
    }

}
