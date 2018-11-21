/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.AtividadeTemplate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Silvio
 */
public interface AtividadeTemplateDao extends BaseDao<AtividadeTemplate, Long> {

    void finalizar(Long id, Session session) throws HibernateException;
}
