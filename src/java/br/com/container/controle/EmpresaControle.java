/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.EmpresaDao;
import br.com.container.dao.EmpresaDaoImpl;
import br.com.container.dao.HibernateUtil;
import br.com.container.modelo.Empresa;
import br.com.container.modelo.Endereco;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author orion
 */
@ManagedBean(name = "empresaC")
@ViewScoped
public class EmpresaControle implements Serializable {

    private Empresa empresa;
    private Endereco endereco;
    private EmpresaDao empresaDao;
    private Session sessao;
    private DataModel<Empresa> modelEmpresa;
    private List<Empresa> empresas;
    private boolean mostra_toolbar;

    private void abreSessao() {
        if (sessao == null) {
            sessao = HibernateUtil.abreSessao();
        } else if (!sessao.isOpen()) {
            sessao = HibernateUtil.abreSessao();
        }
    }

    public void novo() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void novaPesquisa() {
        mostra_toolbar = !mostra_toolbar;
        limpar();
    }

    public void preparaAlterar() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void pesquisar() {
        empresaDao = new EmpresaDaoImpl();
        try {
            abreSessao();
            empresas = empresaDao.pesquisaPorNome(empresa.getNome(), sessao);
            modelEmpresa = new ListDataModel(empresas);
            empresa.setNome(null);
        } catch (Exception e) {
            System.out.println("erro ao pesquisar curso por nome: " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void limpar() {
        empresa = null;
        endereco = null;

    }

    public void carregarParaAlterar() {
        mostra_toolbar = !mostra_toolbar;
        empresa = modelEmpresa.getRowData();
        endereco = empresa.getEndereco();
    }

    public void excluir() {
        empresa = modelEmpresa.getRowData();
        empresaDao = new EmpresaDaoImpl();
        abreSessao();
        try {
            empresaDao.remover(empresa, sessao);
            empresas.remove(empresa);
            modelEmpresa = new ListDataModel(empresas);
            Mensagem.excluir("Empresa");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir emrpesa: " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void salvar() {
        empresa.setEndereco(endereco);
        endereco.setEmpresa(empresa);
        empresaDao = new EmpresaDaoImpl();
        abreSessao();
        try {
            empresaDao.salvarOuAlterar(empresa, sessao);
            Mensagem.salvar("Empresa " + empresa.getNome());
            limpar();

        } catch (HibernateException e) {
            System.out.println("Erro ao salvar empresa " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro no salvar empresa " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public DataModel<Empresa> getModelEmpresa() {
        return modelEmpresa;
    }

    public void setModelEmpresa(DataModel<Empresa> modelEmpresa) {
        this.modelEmpresa = modelEmpresa;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

}
