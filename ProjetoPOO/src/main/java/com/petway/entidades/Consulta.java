package com.petway.entidades;

import com.petway.operacoes.Operacoes;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe que representa uma consulta veterinária.
 * Armazena informações específicas de uma consulta.
 */

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDConsulta;
    private LocalTime Horario;
    private LocalDate Data;
    private double Valor;
    @ManyToOne
    @JoinColumn(name = "CPF_Vet", referencedColumnName = "CPF")
    private Veterinario veterinario;
    @ManyToOne
    @JoinColumn(name = "IDPet", referencedColumnName = "IDPet")
    private Pet pet;
    @Transient
    private Tutor tutor;

    /**
     * Construtor privado da classe Consulta.
     * Cria uma instância de Consulta com as informações fornecidas.
     *
     * @param horario     O horário da consulta.
     * @param data        A data da consulta.
     * @param valor       O valor da consulta.
     * @param veterinario O veterinário responsável pela consulta.
     * @param pet         O pet que será consultado.
     */

    private Consulta( LocalTime horario, LocalDate data, double valor, Veterinario veterinario, Pet pet) {

        this.Horario = horario;
        this.Data = data;
        this.Valor = valor;
        this.veterinario = veterinario;
        this.pet = pet;

    }

    /**
     * Construtor vazio da classe Consulta, obrigatório do Hibernate.
     */

    public Consulta() {

    }

    /**
     * Cria uma instância de Consulta, insere no banco de dados e retorna a instância criada.
     *
     * @param horario     O horário da consulta.
     * @param data        A data da consulta.
     * @param valor       O valor da consulta.
     * @param veterinario O veterinário responsável pela consulta.
     * @param pet         O pet que será consultado.
     * @return A instância de Consulta criada.
     */

    public static Consulta criaConsulta(  LocalTime horario, LocalDate data, double valor, Veterinario veterinario, Pet pet){

        Consulta consulta = new Consulta( horario, data, valor, veterinario, pet);
        Operacoes op = new Operacoes();
        op.inserir(consulta);
        return consulta;
    }

    /**
     * Pesquisa uma consulta no banco de dados pelo ID da consulta.
     *
     * @param IDConsulta O ID da consulta a ser pesquisada.
     * @return A instância de Consulta encontrada, ou null se não encontrada.
     */

    public static Consulta pesquisaConsulta (int IDConsulta) {
        Operacoes op = new Operacoes();
        return (Consulta) op.pesquisa(IDConsulta, Consulta.class);
    }

    /**
     * Remove uma consulta do banco de dados pelo ID da consulta.
     *
     * @param IDConsulta O ID da consulta a ser removida.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */

    public static boolean removeConsulta(int IDConsulta){
        Operacoes op = new Operacoes();
        return op.remove(IDConsulta, Consulta.class); }

    /**
     * Salva as alterações da consulta no banco de dados.
     *
     * @return true se a operação de salvar for bem-sucedida, false caso contrário.
     */

    public boolean salvar(){
        Operacoes op = new Operacoes();
        return op.salvar(this);
    }


    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setValor(double Valor){
        this.Valor = Valor;
    }
    public LocalTime getHorario() {
        return Horario;
    }

    public void setHorario(LocalTime horario) {
        this.Horario = horario;
    }
    public LocalDate getData() {
        return Data;
    }
    public void setData(LocalDate data) {
        this.Data = data;
    }

    public Pet getPet() {
        return pet;
    }
    public double getValor() {
        return Valor;
    }
    public Veterinario getVeterinario() {
        return veterinario;
    }

    public int getIDConsulta() {
        return IDConsulta;
    }

    @Override
    public String toString() {
        return("ID da Consulta: "+IDConsulta+" Data: "+Data+" Horario: "+Horario+" Valor: "+Valor);
    }
}
