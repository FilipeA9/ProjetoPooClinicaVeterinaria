package com.petway.entidades;


import com.petway.operacoes.Operacoes;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe abstrata que representa um funcionário em uma clínica veterinária.
 * Fornece uma estrutura básica para as informações de um funcionário.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Funcionario {
    @Id
    private String CPF;
    private String Nome;

    private LocalDate Data_Nasc;
    private double Remuneracao;
    private LocalDate Data_Entrada;

    /**
     * Construtor da classe Funcionario.
     *
     * @param Nome         O nome do funcionário.
     * @param CPF          O CPF do funcionário.
     * @param Remuneracao  A remuneração do funcionário.
     * @param Data_Nasc    A data de nascimento do funcionário.
     * @param Data_Entrada A data de entrada do funcionário na clínica.
     */

    public Funcionario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada){
        this.Nome = Nome;
        this.CPF = CPF;
        this.Remuneracao = Remuneracao;
        this.Data_Nasc = Data_Nasc;
        this.Data_Entrada = Data_Entrada;

    }

    /**
     * Construtor vazio da classe Funcionario, obrigatório para usar o Hibernate.
     */

    public Funcionario() {

    }

    // operacoes com o banco de dados ----------------------------------------------------------------------------------

    /**
     * Método abstrato para criar um objeto do tipo Funcionario.
     *
     * @param Nome         O nome do funcionário.
     * @param CPF          O CPF do funcionário.
     * @param Remuneracao  A remuneração do funcionário.
     * @param Data_Nasc    A data de nascimento do funcionário.
     * @param Data_Entrada A data de entrada do funcionário na clínica.
     * @return Uma instância de Funcionario criada.
     */
    public abstract Funcionario criaFuncionario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada);
    /**
     * Pesquisa um funcionário no banco de dados pelo CPF.
     *
     * @param CPF O CPF do funcionário a ser pesquisado.
     * @return A instância de Funcionario encontrada, ou null se não encontrada.
     */

    //metodo que retorna um objeto funcionario a partir da pesquisa pelo cpf no Banco de dados
    public Funcionario pesquisaFuncionario (String CPF){
        Operacoes op = new Operacoes();
        return (Funcionario) op.pesquisa(CPF, Funcionario.class);
    };

    /**
     * Método abstrato para remover um funcionário pelo CPF.
     *
     * @param CPF O CPF do funcionário a ser removido.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */

    // metodo que remove um funcionario pelo cpf
    public abstract boolean removeFuncionario(String CPF);

    /**
     * Salva as alterações do funcionário no banco de dados.
     *
     * @return true se a operação de salvar for bem-sucedida, false caso contrário.
     */

    public boolean salvar(){ Operacoes op = new Operacoes(); return op.salvar(this);}


    // getters e setters -----------------------------------------------------------------------------------------------


    public String getNome() {
        return Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public double getRemuneracao() {
        return Remuneracao;
    }

    public LocalDate getData_Entrada() {
        return Data_Entrada;
    }

    public LocalDate getData_Nasc() {
        return Data_Nasc;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setData_Entrada(LocalDate data_Entrada) {
        Data_Entrada = data_Entrada;
    }

    public void setData_Nasc(LocalDate data_Nasc) {
        Data_Nasc = data_Nasc;
    }

    public void setRemuneracao(double remuneracao) {
        Remuneracao = remuneracao;
    }

    @Override
    public String toString() {
        return "CPF='" + CPF + '\'' +
                ", Nome='" + Nome + '\'' +
                ", Data_Nasc=" + Data_Nasc +
                ", Remuneracao=" + Remuneracao +
                ", Data_Entrada=" + Data_Entrada;
    }
}
