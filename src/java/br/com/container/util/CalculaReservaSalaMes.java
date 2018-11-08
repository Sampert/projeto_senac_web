/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.util;

import br.com.container.dao.ReservaDao;
import br.com.container.dao.ReservaDaoImpl;
import br.com.container.modelo.Reserva;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alunos
 */
public class CalculaReservaSalaMes {

    private final ReservaDao reservaDao;
    private Session session;
    private List<Reserva> reservas;

    public CalculaReservaSalaMes() {
        reservaDao = new ReservaDaoImpl();
    }

    public String salaReservaMes() {
        reservas = reservaDao.listaTodos(session);
        String result = "";
        Integer jan = 0;
        Integer fev = 0;
        Integer mar = 0;
        Integer abr = 0;
        Integer mai = 0;
        Integer jun = 0;
        Integer jul = 0;
        Integer ago = 0;
        Integer set = 0;
        Integer out = 0;
        Integer nov = 0;
        Integer dez = 0;
        
        for (Reserva reserva : reservas) {
            Integer mes = reserva.getInicio().getMonth();
            switch (mes) {
                case 1:
                    jan++;
                    break;
                case 2:
                    fev++;
                    break;
                case 3:
                    mar++;
                    break;
                case 4:
                    abr++;
                    break;
                case 5:
                    mai++;
                    break;
                case 6:
                    jun++;
                    break;
                case 7:
                    jul++;
                    break;
                case 8:
                    ago++;
                    break;
                case 9:
                    set++;
                    break;
                case 10:
                    out++;
                    break;
                case 11:
                    nov++;
                    break;
                case 12:
                    dez++;
                    break;
                default:
                    break;
            }
        }
        result += jan + ";"
                + fev + ";"
                + mar + ";"
                + abr + ";"
                + mai + ";"
                + jun + ";"
                + jul + ";"
                + ago + ";"
                + set + ";"
                + out + ";"
                + nov + ";"
                + dez;
        System.out.println(result);
        return result;

    }
}
