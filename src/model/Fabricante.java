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
@Table(name = "fabricante")
public class Fabricante implements Serializable, Comparable<Fabricante> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFabricante;
  
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pj_fabricante")
    private Pj pj;

    public Fabricante() {
    }

    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
    }

    public Pj getPj() {
        return pj;
    }

    public void setPj(Pj pj) {
        this.pj = pj;
    }

    @Override
    public String toString() {
        return pj.getPessoa().getNome();
    }

    @Override
    public int compareTo(Fabricante o) {
        
         return pj.getPessoa().getNome().compareTo(o.pj.getPessoa().getNome());
       
    }

    
       
  
}
