/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author drico
 */
@Entity
@Table(name = "area")
public class Area implements Serializable, Comparable<Area> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idArea;

    private String nomeArea;

    public Area() {
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNomeArea() {
        return nomeArea;
    }

    public void setNomeArea(String nomeArea) {
        this.nomeArea = nomeArea;
    }

    @Override
    public String toString() {
        return nomeArea;
    }

    @Override
    public int compareTo(Area o) {

        return nomeArea.compareTo(o.getNomeArea());
    }

    
}
