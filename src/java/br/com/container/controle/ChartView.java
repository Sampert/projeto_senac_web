package br.com.container.controle;

import br.com.container.dao.HibernateUtil;
import br.com.container.dao.ReservaDao;
import br.com.container.dao.ReservaDaoImpl;
import br.com.container.modelo.GraficoReservaPorMesSala;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "chartC")
public class ChartView implements Serializable {

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private List<GraficoReservaPorMesSala> todosG;
    private Session session;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    private void abreSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() {

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Diario Usabilidade");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries meses = new ChartSeries();
        carregarGrafico();
        meses.setLabel("Meses");
        meses.set("Janeiro", todosG.get(0).getQuantidade());
        meses.set("Fevereiro", todosG.get(1).getQuantidade());
        meses.set("Março", todosG.get(2).getQuantidade());
        meses.set("Abril", todosG.get(3).getQuantidade());
        meses.set("Maio", todosG.get(4).getQuantidade());
        meses.set("Junho", todosG.get(5).getQuantidade());
        meses.set("Julho", todosG.get(6).getQuantidade());
        meses.set("Agosto", todosG.get(7).getQuantidade());
        meses.set("Setembro", todosG.get(8).getQuantidade());
        meses.set("Outubro", todosG.get(9).getQuantidade());
        meses.set("Novembro", todosG.get(10).getQuantidade());
        meses.set("Dezembro", todosG.get(11).getQuantidade());

        model.addSeries(meses);

        return model;
    }

    public void carregarGrafico() {
        ReservaDao reserva = new ReservaDaoImpl();
        abreSessao();
        todosG = reserva.pesquisarTotalMesSala(session);
        session.close();
     }
}
