/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Professor;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author cel05
 */
public interface ProfessorDao extends BaseDao<Professor, Long>{
    List<Professor> pesqPorDisciplina(String disciplina, Session session) throws HibernateException;
    List<Professor> pesqPorBairro(String bairro, Session session) throws HibernateException;
    List<Professor> pesqPorCidade(String cidade, Session session) throws HibernateException;
    List<Professor> pesqPorNomeEDisciplina(String nome, String disciplina, Session session) throws HibernateException;
    List<Professor> pesqPorBairroCidade(String bairro, String cidade, Session session) throws HibernateException;
}
