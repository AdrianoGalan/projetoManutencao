/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Pessoa;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author drico
 */
public class PessoaDao {
    
       public Pessoa buscaPessoaNome(String nome) {

        Pessoa p = null;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Pessoa where nome = :nome");
            q.setParameter("nome", nome);
            p = (Pessoa) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return p;

    }
    
}
