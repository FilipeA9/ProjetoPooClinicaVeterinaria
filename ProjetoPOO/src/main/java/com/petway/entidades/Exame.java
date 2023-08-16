package com.petway.entidades;

import com.petway.operacoes.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe que representa um exame veterinário.
 * Armazena informações específicas de um exame.
 */

@Entity
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDExame;
    private String Tipo;
    private String Laudo;
    private double Valor;
    private LocalDate Data;
    private LocalTime Horario;
    @ManyToOne
    @JoinColumn(name = "IDConsulta", referencedColumnName = "IDConsulta")
    private Consulta consulta;

    /**
     * Construtor privado da classe Exame.
     * Cria uma instância de Exame com as informações fornecidas.
     *
     * @param Tipo     O tipo de exame.
     * @param Laudo    O laudo do exame.
     * @param Data     A data do exame.
     * @param Horario  O horário do exame.
     * @param consulta A consulta associada ao exame.
     * @param Valor    O valor do exame.
     */

    private Exame( String Tipo, String Laudo, LocalDate Data, LocalTime Horario, Consulta consulta, double Valor){
        this.Tipo = Tipo;
        this.Laudo = Laudo;
        this.Data = Data;
        this.Horario = Horario;
        this.consulta = consulta;
        this.Valor = Valor;
    }

    /**
     * Construtor vazio da classe Exame, obrogatório para usar o Hibernate.
     */

    public Exame() {

    }

    /**
     * Cria uma instância de Exame, insere no banco de dados e retorna a instância criada.
     *
     * @param Tipo     O tipo de exame.
     * @param Laudo    O laudo do exame.
     * @param Data     A data do exame.
     * @param Horario  O horário do exame.
     * @param consulta A consulta associada ao exame.
     * @param Valor    O valor do exame.
     * @return A instância de Exame criada.
     */

    public static Exame criaExame( String Tipo, String Laudo, LocalDate Data, LocalTime Horario, Consulta consulta, double Valor){

        Exame exame = new Exame( Tipo, Laudo, Data, Horario, consulta, Valor);
        Operacoes op = new Operacoes();
        op.inserir(exame);
        return exame;
    }

    /**
     * Pesquisa um exame no banco de dados pelo ID do exame.
     *
     * @param IDExame O ID do exame a ser pesquisado.
     * @return A instância de Exame encontrada, ou null se não encontrada.
     */

    public static Exame pesquisaExame (int IDExame) {
        Operacoes op = new Operacoes();
        return (Exame) op.pesquisa(IDExame, Exame.class);
    }

    /**
     * Remove um exame do banco de dados pelo ID do exame.
     *
     * @param IDExame O ID do exame a ser removido.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */

    public static boolean removeExame(int IDExame){
        Operacoes op = new Operacoes();
        return op.remove(IDExame, Pet.class); }
    /**
     * Salva as alterações do exame no banco de dados.
     *
     * @return true se a operação de salvar for bem-sucedida, false caso contrário.
     */

    public boolean salvar(){
        Operacoes op = new Operacoes();
        return op.salvar(this);
    }

    @Override
    public String toString() {
        return "IDExame=" + IDExame +
                ", Tipo='" + Tipo + '\'' +
                ", Laudo='" + Laudo + '\'' +
                ", Valor=" + Valor +
                ", Data=" + Data +
                ", Horario=" + Horario ;
    }

    public int getIDExame() {
        return IDExame;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getLaudo() {
        return Laudo;
    }

    public double getValor() {
        return Valor;
    }

    public LocalDate getData() {
        return Data;
    }

    public LocalTime getHorario() {
        return Horario;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public void setLaudo(String laudo) {
        Laudo = laudo;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public void setData(LocalDate data) {
        Data = data;
    }

    public void setHorario(LocalTime horario) {
        Horario = horario;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
