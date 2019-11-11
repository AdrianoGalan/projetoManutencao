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
@Table(name = "defeito")
public class Defeito implements Serializable, Comparable<Defeito> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idDefeito;
    private String titulo;
    private String desDefeito;
    private String desReparo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_equipamento_defeito")
    private Equipamento equipamento;

 

    public Defeito() {
    }

    public Defeito(String titulo, Equipamento equipamento) {
        this.titulo = titulo.toUpperCase();
        this.equipamento = equipamento;
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
        this.titulo = titulo.toUpperCase();
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

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public String toString() {
        return  titulo ;
    }

    @Override
    public int compareTo(Defeito o) {
        
         return titulo.compareTo(o.getTitulo());
       
    
    }

   

  

}
