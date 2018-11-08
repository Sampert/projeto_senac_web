/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author silvio
 */
@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Funcionario extends Pessoa implements Serializable {

  
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataContratacao;

    @ManyToOne
    @JoinColumn(name = "idFuncao")
    private Funcao funcao;

    public Funcionario() {
    }
    
    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

}
