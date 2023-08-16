package com.petway.entidades;


import com.petway.operacoes.Operacoes;
import javax.persistence.*;

/**
 * Classe que representa uma Observação de Prontuário.
 * Armazena informações relacionadas a observações em prontuários médicos.
 */
@Entity
@Table(name = "obs_prontuario")
public class ObsProntuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDObservacao")
    private int IDObservacao;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "IDProntuario", referencedColumnName = "IDProntuario")
    private Prontuario prontuario;

    /**
     * Construtor vazio da classe ObsProntuario (obrigatório para uso do Hibernate).
     */
    public ObsProntuario() {
    }

    /**
     * Construtor da classe ObsProntuario.
     *
     * @param observacao Observação a ser registrada.
     * @param prontuario Prontuário ao qual a observação está relacionada.
     */
    private ObsProntuario(String observacao, Prontuario prontuario) {
        this.observacao = observacao;
        this.prontuario = prontuario;
    }

    /**
     * Cria e insere uma nova observação de prontuário no banco de dados.
     *
     * @param observacao Observação a ser registrada.
     * @param prontuario Prontuário ao qual a observação está relacionada.
     * @return Objeto ObsProntuario criado e inserido no banco de dados.
     */

    public static ObsProntuario criaObservacao(String observacao, Prontuario prontuario){

        ObsProntuario obsProntuario = new ObsProntuario(observacao, prontuario);
        Operacoes op = new Operacoes();
        op.inserir(obsProntuario);
        return obsProntuario;
    }

    /**
     * Pesquisa uma observação de prontuário no banco de dados pelo ID.
     *
     * @param IDObservacao ID da observação a ser pesquisada.
     * @return Objeto ObsProntuario encontrado, ou null se não encontrado.
     */

    public static ObsProntuario pesquisaObservacao (int IDObservacao) {
        Operacoes op = new Operacoes();
        return (ObsProntuario) op.pesquisa(IDObservacao, Prontuario.class);
    }

    /**
     * Remove uma observação de prontuário do banco de dados pelo ID.
     *
     * @param IDObservacao ID da observação a ser removida.
     * @return true se a observação foi removida com sucesso, false caso contrário.
     */

    public static boolean removeObservacao(int IDObservacao){
        Operacoes op = new Operacoes();
        return op.remove(IDObservacao, ObsProntuario.class); }

    /**
     * Salva as alterações feitas na observação de prontuário no banco de dados.
     *
     * @return true se a observação foi salva com sucesso, false caso contrário.
     */

    public boolean salvar(){

        Operacoes op = new Operacoes();
        return op.salvar(this);
    }

    // Getters e Setters
    public int getIDObservacao() {
        return IDObservacao;
    }

    public void setIDObservacao(int idProntuario) {
        this.IDObservacao = idProntuario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    /**
     * Retorna uma representação em formato de String do objeto ObsProntuario.
     *
     * @return Representação em String do objeto ObsProntuario.
     */

    @Override
    public String toString() {
        return
                "IDObservacao=" + IDObservacao +
                ", observacao='" + observacao;
    }
}

