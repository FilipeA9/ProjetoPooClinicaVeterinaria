package com.petway.entidades;

import com.petway.operacoes.*;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe que representa um Veterinário, derivada da classe Funcionario.
 * Armazena informações específicas de um veterinário, como CRMV.
 */

@Entity
@Table(name = "Veterinario")
public class Veterinario extends Funcionario{
    private String CRMV;
    private static String cargo = "Veterinario";

    /**
     * Construtor privado da classe Veterinario.
     *
     * @param Nome        Nome do veterinário.
     * @param CPF         CPF do veterinário.
     * @param Remuneracao Remuneração do veterinário.
     * @param Data_Nasc   Data de nascimento do veterinário.
     * @param Data_Entrada Data de entrada do veterinário na empresa.
     */

    private Veterinario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada){
        super(Nome, CPF, Remuneracao, Data_Nasc, Data_Entrada);
    }

    /**
     * Construtor público vazio da classe Veterinario (obrigatório para uso do Hibernate).
     */

    public Veterinario() {

    }

    /**
     * Cria um novo objeto Veterinario e o insere no banco de dados.
     *
     * @param Nome        Nome do veterinário.
     * @param CPF         CPF do veterinário.
     * @param Remuneracao Remuneração do veterinário.
     * @param Data_Nasc   Data de nascimento do veterinário.
     * @param Data_Entrada Data de entrada do veterinário na empresa.
     * @return Objeto Veterinario criado e inserido no banco de dados.
     */

    @Override
    public Funcionario criaFuncionario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada) {
        Veterinario veterinario = new Veterinario(Nome, CPF,Remuneracao,Data_Nasc, Data_Entrada);
        Operacoes op = new Operacoes();
        op.inserir(veterinario);
        return veterinario;
    }

    /**
     * Pesquisa um veterinário no banco de dados pelo CPF.
     *
     * @param CPF CPF do veterinário a ser pesquisado.
     * @return Objeto Veterinario encontrado, ou null se não encontrado.
     */

    @Override
    public Funcionario pesquisaFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return  (Veterinario) op.pesquisa(CPF, Veterinario.class);
    }

    /**
     * Remove um veterinário do banco de dados pelo CPF.
     *
     * @param CPF CPF do veterinário a ser removido.
     * @return true se o veterinário foi removido com sucesso, false caso contrário.
     */
    @Override
    public boolean removeFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return op.remove(CPF, Veterinario.class);

    }

    /**
     * Retorna uma representação em formato de String do objeto Veterinario.
     *
     * @return Representação em String do objeto Veterinario.
     */

    @Override
    public String toString() {
        return "Cargo: Veterinário "+super.toString()+" CRMV :"+this.CRMV;
    }

    /**
     * Obtém o CRMV (Conselho Regional de Medicina Veterinária) do veterinário.
     *
     * @return CRMV do veterinário.
     */
    public String getCRMV() {
        return CRMV;
    }

    /**
     * Define o CRMV (Conselho Regional de Medicina Veterinária) do veterinário.
     *
     * @param CRMV CRMV a ser definido para o veterinário.
     */
    public void setCRMV(String CRMV) {
        this.CRMV = CRMV;
    }

    /**
     * Obtém o cargo do veterinário.
     *
     * @return Cargo do veterinário.
     */

    public static String getCargo() {
        return cargo;
    }
}
