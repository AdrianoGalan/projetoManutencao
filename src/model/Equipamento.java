/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author drico
 */
@Entity
@Table(name = "equipamento")
public class Equipamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEquipamento;
    private String sigla;
    private String tipo;
    private String modelo;
    private String matriculaEquipamento;
    private int statusEquipamento;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fabricante_equipamento")
    private Fabricante fabricante;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_area_equipamento")
    private Area area;

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
        this.sigla = sigla.toUpperCase();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.toUpperCase();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.toUpperCase();
    }

    public String getMatriculaEquipamento() {
        return matriculaEquipamento;
    }

    public void setMatriculaEquipamento(String matriculaEquipamento) {
        this.matriculaEquipamento = matriculaEquipamento.toUpperCase();
    }

    public int getStatusEquipamento() {
        return statusEquipamento;
    }

    public void setStatusEquipamento(int statusEquipamento) {
        this.statusEquipamento = statusEquipamento;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return sigla ;
    }
    
    

    

}
