/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Pessoa;
import model.Pj;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author drico
 */
public class PjDao {

    public Pj buscaPjNome(String nome) {

        PessoaDao pd = new PessoaDao();

        Pessoa p = pd.buscaPessoaNome(nome);
        if (p == null) {
            return null;
        }

        Pj pj = null;

        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Pj where id_pessoa_pj = :id");
            q.setParameter("id", p.getIdPessoa());
            pj = (Pj) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return pj;

    }

}
