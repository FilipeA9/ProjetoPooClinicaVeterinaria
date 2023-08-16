package com.petway.entidades;

import com.petway.operacoes.Operacoes;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Classe que representa um auxiliar de limpeza, uma subclasse de Funcionario.
 * Armazena informações específicas de um auxiliar de limpeza.
 */

@Entity
@Table(name = "Aux_Limp")
public class AuxiliarLimpeza extends Funcionario {

    private static String cargo = "Auxiliar de Limpeza";

    /**
     * Construtor da classe AuxiliarLimpeza.
     * Cria uma instância de AuxiliarLimpeza com as informações fornecidas.
     *
     * @param Nome         O nome do auxiliar de limpeza.
     * @param CPF          O CPF do auxiliar de limpeza.
     * @param Remuneracao  A remuneração do auxiliar de limpeza.
     * @param Data_Nasc    A data de nascimento do auxiliar de limpeza.
     * @param Data_Entrada A data de entrada do auxiliar de limpeza na empresa.
     */

    public AuxiliarLimpeza(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada){
        super(Nome, CPF, Remuneracao, Data_Nasc, Data_Entrada);
    }
    /**
     * Construtor vazio da classe AuxiliarLimpeza obrigatório do Hibernate.
     */

    public AuxiliarLimpeza() {

    }

    /**
     * Cria uma instância de AuxiliarLimpeza e insere no banco de dados.
     *
     * @param Nome         O nome do auxiliar de limpeza.
     * @param CPF          O CPF do auxiliar de limpeza.
     * @param Remuneracao  A remuneração do auxiliar de limpeza.
     * @param Data_Nasc    A data de nascimento do auxiliar de limpeza.
     * @param Data_Entrada A data de entrada do auxiliar de limpeza na empresa.
     * @return A instância de AuxiliarLimpeza criada.
     */

    @Override
    public Funcionario criaFuncionario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada) {
        AuxiliarLimpeza aux = new AuxiliarLimpeza(Nome, CPF,Remuneracao,Data_Nasc, Data_Entrada);
        Operacoes op = new Operacoes();
        op.inserir(aux);
        return aux;
    }

    /**
     * Pesquisa um auxiliar de limpeza no banco de dados pelo CPF.
     *
     * @param CPF O CPF do auxiliar de limpeza a ser pesquisado.
     * @return A instância de AuxiliarLimpeza encontrada, ou null se não encontrado.
     */

    @Override
    public Funcionario pesquisaFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return  (AuxiliarLimpeza) op.pesquisa(CPF, AuxiliarLimpeza.class);
    }

    /**
     * Remove um auxiliar de limpeza do banco de dados pelo CPF.
     *
     * @param CPF O CPF do auxiliar de limpeza a ser removido.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */

    @Override
    public boolean removeFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return op.remove(CPF, AuxiliarLimpeza.class);

    }

    /**
     * Retorna a representação em string do auxiliar de limpeza.
     *
     * @return A representação em string do auxiliar de limpeza.
     */
    @Override
    public String toString() {
        return "Cargo: Auxiliar de Limpeza"+super.toString();
    }

    /**
     * Obtém o cargo do auxiliar de limpeza.
     *
     * @return O cargo "Auxiliar de Limpeza".
     */
    public static String getCargo() {
        return cargo;
    }
}
