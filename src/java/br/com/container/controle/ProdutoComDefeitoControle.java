/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.HibernateUtil;
import br.com.container.dao.ProdutoComDefeitoDao;
import br.com.container.dao.ProdutoComDefeitoDaoImpl;
import br.com.container.modelo.ProdutoComDefeito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Alunos
 */
@ManagedBean(name = "produto_defeito")
@ViewScoped
public class ProdutoComDefeitoControle implements Serializable {

    private boolean mostraToolbar = false;
    private boolean pesquisaPorDisciplina = false;
    private String pesqNome = "";
    private String pesqDisciplina = "";

    private Session session;
    private ProdutoComDefeitoDao dao;

    private ProdutoComDefeito prod;
    private List<ProdutoComDefeito> prods;
    private DataModel<ProdutoComDefeito> modelProdutos;

    private void abreSessao() {
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public void mudaToolbar() {
        prod = new ProdutoComDefeito();
        prods = new ArrayList();
        pesqNome = "";
        mostraToolbar = !mostraToolbar;
    }

    public void pesquisar() {
        dao = new ProdutoComDefeitoDaoImpl();
        try {
            abreSessao();

            if (!pesqNome.equals("")) {
                prods = dao.pesquisaPorNome(pesqNome, session);
            } else {
                prods = dao.listaTodos(session);
            }

            modelProdutos = new ListDataModel(prods);
            pesqNome = null;
            pesqDisciplina = null;
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa professor:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvar() {
        dao = new ProdutoComDefeitoDaoImpl();
        try {
            abreSessao();
            dao.salvarOuAlterar(prod, session);
            Mensagem.salvar("Produto com defeito " + prod.getNome());
        } catch (Exception ex) {
            Mensagem.mensagemError("Erro ao salvar\nTente novamente");
            System.err.println("Erro pesquisa produtos com defeito:\n" + ex.getMessage());
        } finally {
            prod = new ProdutoComDefeito();
            session.close();
        }
    }

    public void alterarProd() {
        mostraToolbar = !mostraToolbar;
        prod = modelProdutos.getRowData();
    }

    public void excluir() {
        prod = modelProdutos.getRowData();
        dao = new ProdutoComDefeitoDaoImpl();
        try {
            abreSessao();
            dao.remover(prod, session);
            Mensagem.excluir("Professor " + prod.getNome());
            prod = new ProdutoComDefeito();
        } catch (Exception ex) {
            System.err.println("Erro ao excluir professor:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    //Getters e Setters
    public boolean isMostraToolbar() {
        return mostraToolbar;
    }

    public void setMostraToolbar(boolean mostraToolbar) {
        this.mostraToolbar = mostraToolbar;
    }

    public boolean isPesquisaPorDisciplina() {
        return pesquisaPorDisciplina;
    }

    public void setPesquisaPorDisciplina(boolean pesquisaPorDisciplina) {
        this.pesquisaPorDisciplina = pesquisaPorDisciplina;
    }

    public String getPesqNome() {
        return pesqNome;
    }

    public void setPesqNome(String pesqNome) {
        this.pesqNome = pesqNome;
    }

    public String getPesqDisciplina() {
        return pesqDisciplina;
    }

    public void setPesqDisciplina(String pesqDisciplina) {
        this.pesqDisciplina = pesqDisciplina;
    }

    public ProdutoComDefeito getProd() {
        if (prod == null) {
            prod = new ProdutoComDefeito();
        }
        return prod;
    }

    public void setProd(ProdutoComDefeito prod) {
        this.prod = prod;
    }

    public List<ProdutoComDefeito> getProds() {
        if (prods == null) {
            prods = new ArrayList();
        }
        return prods;
    }

    public void setProds(List<ProdutoComDefeito> prods) {
        this.prods = prods;
    }

    public DataModel<ProdutoComDefeito> getModelProds() {
        return modelProdutos;
    }

    public void setModelProds(DataModel<ProdutoComDefeito> modelProds) {
        this.modelProdutos = modelProds;
    }
}
