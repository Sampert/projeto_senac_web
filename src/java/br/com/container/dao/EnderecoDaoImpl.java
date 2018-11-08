/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Endereco;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alunos
 */
public class EnderecoDaoImpl extends BaseDaoImpl<Endereco, Long> implements EnderecoDao, Serializable {

    @Override
    public Endereco pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Endereco) session.get(Endereco.class, id);
    }

    @Override
    public List<Endereco> listaTodos(Session session) throws HibernateException {
        Query consulta = session.createQuery("from Endereco");
        return consulta.list();
    }

    @Override
    public List<Endereco> pesquisaPorNome(String logradouro, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Endereco e where e.logradouro like :logradouro");
        consulta.setParameter("logradouro", "%" + logradouro + "%");
        return consulta.list();
    }

}
