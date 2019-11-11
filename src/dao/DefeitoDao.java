/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Defeito;
import model.Equipamento;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author drico
 */
public class DefeitoDao {

    public boolean salvarDefeito(Defeito d) {

        if (defeitoExistente(d.getTitulo(), d.getEquipamento())) {
            return false;
        } else {

            try {
                Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                s.beginTransaction();
                s.save(d);
                s.getTransaction().commit();
                Util.mensagem("Salvo com Sucesso", "Salvo com Sucesso");

            } catch (HibernateException ex) {
                String erro = ex.getMessage();

                Util.mensagem(erro, "Erro ao Salvar");
                return false;
            }

            return true;
        }
    }

    public boolean defeitoExistente(String defeito, Equipamento equipamento) {

        String id_equipamento = String.valueOf(equipamento.getIdEquipamento());
        Defeito d;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Defeito where titulo = :defeito AND id_equipamento_defeito = :id_equipamento");
            q.setParameter("defeito", defeito);
            q.setParameter("id_equipamento", id_equipamento);
            d = (Defeito) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException ex) {
            Util.mensagem(ex.getMessage(), "Erro...");
            return true;
        }

        if (d != null) {
            Util.mensagem("Defeito já Apresentado", "Defeito já Apresentado favor selecionar o defeito na lista");
            return true;
        } else {
            return false;
        }

    }

    public List<Defeito> buscarDefeitos(Equipamento equipamento) {

        String idEquipamneto = String.valueOf(equipamento.getIdEquipamento());
        String sql = "from Defeito where id_equipamento_defeito = " + idEquipamneto;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            List<Defeito> df = (List<Defeito>) s.createQuery(sql).list();

            s.getTransaction().commit();

            return df;

        } catch (HibernateException e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }

}
