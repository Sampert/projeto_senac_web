/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.AlunoDao;
import br.com.container.dao.AlunoDaoImpl;
import br.com.container.dao.HibernateUtil;
import br.com.container.modelo.Aluno;
import br.com.container.modelo.Endereco;
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
@ManagedBean(name = "alunoC")
@ViewScoped
public class AlunoControle {

    private Aluno aluno;
    private AlunoDao alunoDao;
    private Session sessao;
    private Endereco endereco;
    private DataModel<Aluno> modelAlunos;
    private List<Aluno> alunos;
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
        aluno = new Aluno();
        endereco = new Endereco();
        mostra_toolbar = !mostra_toolbar;
    }

    public void preparaAlterar() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void pesquisar() {
        alunoDao = new AlunoDaoImpl();
        try {
            abreSessao();
            aluno = alunoDao.pesquisaPorCpf(aluno.getCpf(), sessao);
//            alunos = alunoDao.pesquisaPorCpf(aluno.getCpf(), sessao);
//            modelAlunos = new ListDataModel(alunos);
            aluno.setCpf(null);
        } catch (Exception e) {
            System.out.println("erro ao pesquisar aluno por cpf: " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void limpar() {
        aluno = new Aluno();
        endereco = new Endereco();
    }

    //metodos getts e setts
    public void carregarParaAlterar() {
        mostra_toolbar = !mostra_toolbar;
        endereco = aluno.getEndereco();

    }

    public void excluir() {
        alunoDao = new AlunoDaoImpl();
        abreSessao();
        try {
            alunoDao.remover(aluno, sessao);
            Mensagem.excluir("Aluno");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir: " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void salvar() {
        aluno.setEndereco(endereco);
        endereco.setPessoa(aluno);
        alunoDao = new AlunoDaoImpl();
        abreSessao();
        try {

            alunoDao.salvarOuAlterar(aluno, sessao);
            Mensagem.salvar("Aluno " + aluno.getNome());
            aluno = null;
            endereco = null;

        } catch (HibernateException e) {
            boolean isLoginDuplicado = e.getCause().getMessage().contains("'email_UNIQUE'");
            if (isLoginDuplicado) {
                Mensagem.campoExiste("E-mail");
            }
            System.out.println("Erro ao salvar aluno " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro no salvar alunoDao Controle "
                    + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void limparTela() {
        limpar();
    }

    public Aluno getAluno() {
        if (aluno == null) {
            aluno = new Aluno();
            aluno.setWhatsapp(true);
        }
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAlunos() {
        if (alunos == null) {
            alunos = new ArrayList();
        }
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public DataModel<Aluno> getModelAlunos() {
        return modelAlunos;
    }

    public void setModelAlunos(DataModel<Aluno> modelAlunos) {
        this.modelAlunos = modelAlunos;
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

    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

}
