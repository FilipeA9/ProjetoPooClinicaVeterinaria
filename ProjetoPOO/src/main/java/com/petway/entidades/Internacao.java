package com.petway.entidades;

import com.petway.operacoes.Operacoes;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Internacao")
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDInternacao")
    private int IDInternacao;

    @Column(name = "Data_Saida")
    private LocalDate dataSaida = null;

    @Column(name = "Data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "Valor")
    private double valor;

    @ManyToOne
    @JoinColumn(name = "IDConsulta", referencedColumnName = "IDConsulta")
    private Consulta consulta;

    private Internacao( LocalDate dataSaida, LocalDate dataInicio, double valor, Consulta consulta) {

        this.dataSaida = dataSaida;
        this.dataInicio = dataInicio;
        this.valor = valor;
        this.consulta = consulta;
    }

    public Internacao() {
    }

    public static Internacao criaInternacao( LocalDate dataSaida, LocalDate dataInicio, double valor, Consulta consulta){

        Internacao internacao = new Internacao( dataSaida, dataInicio, valor, consulta);
        Operacoes op = new Operacoes();
        op.inserir(internacao);
        return internacao;
    }

    public static Internacao pesquisaInternacao (int IDInternacao) {
        Operacoes op = new Operacoes();
        return (Internacao) op.pesquisa(IDInternacao, Internacao.class);
    }

    public static boolean removeInternacao(int IDInternacao){
        Operacoes op = new Operacoes();
        return op.remove(IDInternacao, Internacao.class); }

    public boolean salvar(){
        Operacoes op = new Operacoes();
        return op.salvar(this);
    }



    public int getIDInternacao() {
        return IDInternacao;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    @Override
    public String toString() {
        return "IDInternacao=" + IDInternacao +
                ", dataSaida=" + dataSaida +
                ", dataInicio=" + dataInicio +
                ", valor=" + valor ;
    }
}

