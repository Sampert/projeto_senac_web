/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Alunos
 */
@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Aluno extends Pessoa implements Serializable {

    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCateira")
    private CarteiraBiblioteca carteiraBiblioteca;

    public Aluno() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public CarteiraBiblioteca getCarteiraBiblioteca() {
        return carteiraBiblioteca;
    }

    public void setCarteiraBiblioteca(CarteiraBiblioteca carteiraBiblioteca) {
        this.carteiraBiblioteca = carteiraBiblioteca;
    }

}
