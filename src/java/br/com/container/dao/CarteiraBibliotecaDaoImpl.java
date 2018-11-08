/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.CarteiraBiblioteca;
import br.com.container.modelo.Sala;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Silvio
 */
public class CarteiraBibliotecaDaoImpl extends BaseDaoImpl<CarteiraBiblioteca, String> implements CarteiraBibliotecaDao, Serializable {

    @Override
    public CarteiraBiblioteca pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (CarteiraBiblioteca) session.get(CarteiraBiblioteca.class, id);
    }

    @Override
    public List<CarteiraBiblioteca> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from CarteiraBiblioteca s ORDER BY s.aluno").list();
    }

    @Override
    public List<CarteiraBiblioteca> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from CarteiraBiblioteca s where s.aluno.nome like :nome ORDER BY s.aluno.nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    
}
