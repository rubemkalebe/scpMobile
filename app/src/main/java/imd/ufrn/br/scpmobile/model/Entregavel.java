package imd.ufrn.br.scpmobile.model;

/**
 * Created by f596024 on 07/12/2017.
 */

public class Entregavel {
    private Projeto projeto;
    private int id;
    private String nome;
    private String descricao;
    private String data_inicio;
    private String data_fim_prev;
    private String data_fim;

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

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

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim_prev() {
        return data_fim_prev;
    }

    public void setData_fim_prev(String data_fim_prev) {
        this.data_fim_prev = data_fim_prev;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    @Override
    public String toString() {
        return "Entregavel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data_inicio=" + data_inicio +
                ", data_fim_prev=" + data_fim_prev +
                ", data_fim=" + data_fim +
                '}';
    }
}