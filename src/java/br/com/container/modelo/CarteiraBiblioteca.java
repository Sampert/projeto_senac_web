/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alunos
 */
@Entity
@Table(name = "carteira_biblioteca")
public class CarteiraBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer numeroCarteira;

    @Temporal(TemporalType.DATE)
    private Date validade;

    @OneToOne
    private Curso curso;

    @OneToOne(mappedBy = "carteiraBiblioteca", cascade = CascadeType.ALL)
    private Aluno aluno;

    public CarteiraBiblioteca(Integer numeroCarteira, Date validade, Curso curso, Aluno aluno) {
        this.numeroCarteira = numeroCarteira;
        this.validade = validade;
        this.curso = curso;
        this.aluno = aluno;
    }

    public CarteiraBiblioteca() {
    }

    public Integer getNumeroCarteira() {
        return numeroCarteira;
    }

    public void setNumeroCarteira(Integer numeroCarteira) {
        this.numeroCarteira = numeroCarteira;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

}
