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
public class Email {

    private int idEmail;
    private String email;
    private int fkPessoaEmail;

    public Email() {
    }

    public int getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(int idEmail) {
        this.idEmail = idEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFkPessoaEmail() {
        return fkPessoaEmail;
    }

    public void setFkPessoaEmail(int fkPessoaEmail) {
        this.fkPessoaEmail = fkPessoaEmail;
    }

}
