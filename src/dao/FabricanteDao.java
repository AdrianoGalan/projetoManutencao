/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Fabricante;
import model.Pj;
import model.Usuarios;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author drico
 */
public class FabricanteDao {
    
    
  
    // Busca Lista de Fabricante
    public List<Fabricante> buscarFabricante() {
       
        String sql = "From Fabricante";

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            List<Fabricante> f = (List<Fabricante>) s.createQuery(sql).list();

            s.getTransaction().commit();

            return f;

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }
    
    public Fabricante buscaPorNome(String nome){
        
            PjDao pjd = new PjDao();

        Pj pj = pjd.buscaPjNome(nome);
         if (pj == null) {
            return null;
        }
        
        Fabricante f = null;
        
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Fabricante where id_pj_fabricante = :id");
            q.setParameter("id", pj.getIdPJ());
            f = (Fabricante) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return f;
        
        
        
    }


}
