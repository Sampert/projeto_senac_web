/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.ProdutoComDefeito;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alunos
 */
public class ProdutoComDefeitoDaoImpl extends BaseDaoImpl<ProdutoComDefeito, Long> implements ProdutoComDefeitoDao {

    @Override
    public ProdutoComDefeito pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (ProdutoComDefeito) session.get(ProdutoComDefeito.class, id);
    }

    @Override
    public List<ProdutoComDefeito> listaTodos(Session session) throws HibernateException {
        Query consulta = session.createQuery("from ProdutoComDefeito");
        return consulta.list();
    }

    @Override
    public List<ProdutoComDefeito> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from ProdutoComDefeito where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

}
