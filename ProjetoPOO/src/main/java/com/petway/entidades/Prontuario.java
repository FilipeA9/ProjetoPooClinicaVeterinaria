package com.petway.entidades;

import com.petway.operacoes.Operacoes;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe que representa um Prontuário de um animal em uma clínica veterinária.
 * O prontuário contém informações sobre medicações, alimentação, pressão, temperatura, etc.
 * Esta classe é mapeada como uma entidade JPA para interagir com o banco de dados.
 */

@Entity
@Table(name = "Prontuario")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProntuario")
    private int IDProntuario;

    @Column(name = "Med_Aplicada")
    private String medAplicada;

    @Column(name = "Data_hora")
    private LocalDateTime dataHora;

    @Column(name = "Alimentacoes")
    private String alimentacoes;

    @Column(name = "Pressao")
    private String pressao;

    @Column(name = "Temp")
    private double temp;

    @ManyToOne
    @JoinColumn(name = "IDInternacao")
    private Internacao internacao;

    // Construtor vazio (obrigatório para uso do Hibernate)
    public Prontuario() {
    }

    // Construtor com todos os atributos
    private Prontuario(String medAplicada, LocalDateTime dataHora, String alimentacoes,
                      String pressao, double temp, Internacao internacao) {
        this.medAplicada = medAplicada;
        this.dataHora = dataHora;
        this.alimentacoes = alimentacoes;
        this.pressao = pressao;
        this.temp = temp;
        this.internacao = internacao;
    }


    /**
     * Cria um novo Prontuário no banco de dados.
     *
     * @param medAplicada  Medicação aplicada no prontuário.
     * @param dataHora     Data e hora do registro.
     * @param alimentacoes Informações sobre alimentações.
     * @param pressao      Informações sobre a pressão.
     * @param temp         Temperatura registrada.
     * @param internacao   Internação associada ao prontuário.
     * @return O prontuário criado.
     */
    public static Prontuario criaProntuario(String medAplicada, LocalDateTime dataHora, String alimentacoes,
                                            String pressao, double temp, Internacao internacao){

        Prontuario pronturario = new Prontuario( medAplicada, dataHora,alimentacoes,pressao,temp, internacao);
        Operacoes op = new Operacoes();
        op.inserir(pronturario);

        return pronturario;
    }

    /**
     * Pesquisa um prontuário no banco de dados pelo seu ID.
     *
     * @param IDProntuario O ID do prontuário a ser pesquisado.
     * @return O prontuário encontrado ou null se não for encontrado.
     */
    public static Prontuario pesquisaProntuario(int IDProntuario){
        Operacoes op = new Operacoes();
        return (Prontuario) op.pesquisa(IDProntuario, Prontuario.class);
    }

    /**
     * Remove um prontuário do banco de dados pelo seu ID.
     *
     * @param IDProntuario O ID do prontuário a ser removido.
     * @return true se o prontuário foi removido com sucesso, false caso contrário.
     */
    public static boolean removeProntuario(int IDProntuario){

        Operacoes op = new Operacoes();
        return op.remove(IDProntuario, Prontuario.class);
    }

    /**
     * Salva as alterações feitas no prontuário no banco de dados.
     *
     * @return true se as alterações foram salvas com sucesso.
     */


    public boolean salvar(){

        Operacoes op = new Operacoes();
        return op.salvar(this);
    }



    /**
     * Cria uma nova observação de prontuário associada a este prontuário.
     *
     * @param observacao A observação a ser adicionada.
     * @return A observação de prontuário criada.
     */
    public ObsProntuario criarObservacao(String observacao){
        ObsProntuario obs = ObsProntuario.criaObservacao(observacao, this);
        return obs;
    }

    // Getters e Setters
    public int getIdProntuario() {
        return IDProntuario;
    }

    public void setIdProntuario(int idProntuario) {
        this.IDProntuario = idProntuario;
    }

    public String getMedAplicada() {
        return medAplicada;
    }

    public void setMedAplicada(String medAplicada) {
        this.medAplicada = medAplicada;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getAlimentacoes() {
        return alimentacoes;
    }

    public void setAlimentacoes(String alimentacoes) {
        this.alimentacoes = alimentacoes;
    }

    public String getPressao() {
        return pressao;
    }

    public void setPressao(String pressao) {
        this.pressao = pressao;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Internacao getInternacao() {
        return internacao;
    }

    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
    }

    @Override
    public String toString() {
        return "idProntuario=" + IDProntuario +
                ", medAplicada='" + medAplicada + '\'' +
                ", dataHora=" + dataHora +
                ", alimentacoes='" + alimentacoes + '\'' +
                ", pressao=" + pressao +
                ", temp=" + temp ;
    }
}

