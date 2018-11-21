/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.container.modelo.PlanejamentoTemplate;

/**
 *
 * @author Silvio
 */
public class PlanejamentoTemplateDaoImpl extends BaseDaoImpl<PlanejamentoTemplate, Long> implements PlanejamentoTemplateDao, Serializable {

    @Override
    public PlanejamentoTemplate pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (PlanejamentoTemplate) session.get(PlanejamentoTemplate.class, id);
    }

    @Override
    public List<PlanejamentoTemplate> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from PlanejamentoTemplate").list();
    }

    @Override
    public List<PlanejamentoTemplate> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from PlanejamentoTemplate p left join fetch p.atividadeTemplates where p.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

}
