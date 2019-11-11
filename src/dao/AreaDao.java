/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Area;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author drico
 */
public class AreaDao {

    public List<Area> buscarTodosFabricantes() {

        String sql = "From Area";

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            List<Area> area = (List<Area>) s.createQuery(sql).list();

            s.getTransaction().commit();

            return area;

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }

    public boolean salvarArea(Area a) {

        if (nomeUsado(a)) {
            return false;
        }

        try {
            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            s.save(a);
            s.getTransaction().commit();
            Util.mensagem("Salvo com Sucesso", "Salvo com Sucesso");

        } catch (HibernateException ex) {
            String erro = ex.getMessage();

            Util.mensagem(erro, "Erro ao Salvar");
            return false;
        }

        return true;

    }

    public boolean nomeUsado(Area a) {

        String nome = a.getNomeArea();

      

        if (buscaPorNome(nome) != null) {
            Util.mensagem("Nome já utilizado", "Nome invalido");
            return true;
        } else {
            return false;
        }

    }

    public Area buscaPorNome(String nome) {

        Area ar = null;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Area where nomearea = :area");
            q.setParameter("area", nome);
            ar = (Area) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");
         
        }
        
        return ar;
   

    }

}
