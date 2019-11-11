/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
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
public class EquipamentoDao {

    public boolean salvarEquipamento(Equipamento e) {

        if (siglaUsado(e) || matriculaUsado(e)) {
            return false;
        }

        try {
            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            s.save(e);
            s.getTransaction().commit();
            Util.mensagem("Salvo com Sucesso", "Salvo com Sucesso");

        } catch (HibernateException ex) {
            String erro = ex.getMessage();

            Util.mensagem(erro, "Erro ao Salvar");
            return false;
        }

        return true;

    }

    public List<Equipamento> buscaProSigla(String sigla) {

        String sql = "From Equipamento Where Sigla Like '" + "%" + sigla + "%" + "'";

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            List<Equipamento> equipamentos = (List<Equipamento>) s.createQuery(sql).list();

            s.getTransaction().commit();

            return equipamentos;

        } catch (Exception e) {
            Util.mensagem(e.getMessage(), "Erro...");

        }

        return null;
    }

    public boolean siglaUsado(Equipamento e) {

        String sigla = e.getSigla();

        Equipamento eq;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Equipamento where sigla = :sigla");
            q.setParameter("sigla", sigla);
            eq = (Equipamento) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException ex) {
            Util.mensagem(ex.getMessage(), "Erro...");
            return true;
        }

        if (eq != null) {
            Util.mensagem("Sigla já utilizado", "Sigla invalido");
            return true;
        } else {
            return false;
        }

    }

    public boolean matriculaUsado(Equipamento e) {

        String matricula = e.getMatriculaEquipamento();

        Equipamento eq;
        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Equipamento where matriculaequipamento = :matricula");
            q.setParameter("matricula", matricula);
            eq = (Equipamento) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException ex) {
            Util.mensagem(ex.getMessage(), "Erro...");
            return true;
        }

        if (eq != null) {
            Util.mensagem("Matricula já utilizado", "Matricula invalido");
            return true;
        } else {
            return false;
        }

    }

    public boolean atualizaEquipamento(Equipamento e) {

        try {
            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            s.update(e);
            s.getTransaction().commit();
            Util.mensagem("Atualizado com Sucesso", "Atualizado com Sucesso");

        } catch (HibernateException ex) {
            String erro = ex.getMessage();

            Util.mensagem(erro, "Erro ao Atualizar");
            return false;
        }

        return true;

    }

    public Equipamento buscaPorNome(String nome) {

        
        Equipamento e = null;

        Query q;

        try {

            Session s = HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();

            q = s.createQuery("from Equipamento where sigla = :nome");
            q.setParameter("nome", nome);
            e = (Equipamento) q.uniqueResult();
            s.getTransaction().commit();

        } catch (HibernateException ex) {
            Util.mensagem(ex.getMessage(), "Erro... aki");

        }

        return e;

    }
}
