/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author drico
 */
@Entity
@Table(name = "os")
public class Os implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idOs;

    private String numeroOs;
    private String prioridade;
    private String oficina;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicioReparo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFimReparo;
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Temporal(TemporalType.TIME)
    private Date horaInicioReparo;
    @Temporal(TemporalType.TIME)
    private Date horaFim;
    @Temporal(TemporalType.TIME)
    private Date horaFimReparo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_os_defeito")
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

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraInicioReparo() {
        return horaInicioReparo;
    }

    public void setHoraInicioReparo(Date horaInicioReparo) {
        this.horaInicioReparo = horaInicioReparo;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public Date getHoraFimReparo() {
        return horaFimReparo;
    }

    public void setHoraFimReparo(Date horaFimReparo) {
        this.horaFimReparo = horaFimReparo;
    }

    public Defeito getDefeito() {
        return defeito;
    }

    public void setDefeito(Defeito defeito) {
        this.defeito = defeito;
    }

}
