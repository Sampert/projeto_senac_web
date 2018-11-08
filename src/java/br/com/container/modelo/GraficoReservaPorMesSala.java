/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;

/**
 *
 * @author Alunos
 */
public class GraficoReservaPorMesSala implements Serializable {

    private int mes;
    private long quantidade;

    public GraficoReservaPorMesSala(int mes, long quantidade) {
        this.mes = mes;
        this.quantidade = quantidade;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

 

}
