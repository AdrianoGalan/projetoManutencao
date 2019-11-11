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
public class Fabricante extends Pj {

    private int idFabricante;
    private int fkPjFabricante;

    public Fabricante() {
    }

    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
    }

    public int getFkPjFabricante() {
        return fkPjFabricante;
    }

    public void setFkPjFabricante(int fkPjFabricante) {
        this.fkPjFabricante = fkPjFabricante;
    }

}
