package imd.ufrn.br.scpmobile.model;

import java.util.List;


/**
 * Created by f596024 on 07/12/2017.
 */

public class Projeto {
    private int id;
    private String nome;
    private String descricao;
    private Pessoa patrocinador;
    private Pessoa gerente;
    private List<Pessoa> equipe;
    private List<Entregavel> entregaveis;
    private List<Mudanca> mudancas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Pessoa patrocinador) {
        this.patrocinador = patrocinador;
    }

    public Pessoa getGerente() {
        return gerente;
    }

    public void setGerente(Pessoa gerente) {
        this.gerente = gerente;
    }

    public List<Pessoa> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Pessoa> equipe) {
        this.equipe = equipe;
    }

    public List<Entregavel> getEntregaveis() {
        return entregaveis;
    }

    public void setEntregaveis(List<Entregavel> entregaveis) {
        this.entregaveis = entregaveis;
    }

    public List<Mudanca> getMudancas() {
        return mudancas;
    }

    public void setMudancas(List<Mudanca> mudancas) {
        this.mudancas = mudancas;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}