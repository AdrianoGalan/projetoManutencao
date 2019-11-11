/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Os;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author drico
 */
public class OsDao {
    
    public boolean salvarDefeito(Os os) {

       

            try {
                Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                s.beginTransaction();
                s.save(os);
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
