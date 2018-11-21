/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.AtividadeTemplate;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Silvio
 */
public class AtividadeTemplateDaoImpl extends BaseDaoImpl<AtividadeTemplate, Long> implements AtividadeTemplateDao, Serializable {

    @Override
    public AtividadeTemplate pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (AtividadeTemplate) session.get(AtividadeTemplate.class, id);
    }

    @Override
    public List<AtividadeTemplate> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from AtividadeTemplate").list();
    }

    @Override
    public List<AtividadeTemplate> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from AtividadeTemplate where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override 
    public void finalizar(Long id, Session session) throws HibernateException {
        Query consulta = session.createQuery("UPDATE AtividadeTemplate a SET a.dt_entrega = now() where a.id = :id");
        consulta.setParameter("id", id);
        consulta.executeUpdate();
    }

}
