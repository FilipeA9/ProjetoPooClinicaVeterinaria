package com.petway.entidades;

import com.petway.operacoes.Operacoes;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Gerente")
public class Gerente extends Funcionario {

    private static String cargo = "Gerente";

    public Gerente(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada){
        super(Nome, CPF, Remuneracao, Data_Nasc, Data_Entrada);
    }

    public Gerente() {

    }

    @Override
    public Funcionario criaFuncionario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada) {
        Gerente gerente = new Gerente(Nome, CPF,Remuneracao,Data_Nasc, Data_Entrada);
        Operacoes op = new Operacoes();
        op.inserir(gerente);
        return gerente;
    }

    @Override
    public Funcionario pesquisaFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return  (Gerente) op.pesquisa(CPF, Gerente.class);
    }

    @Override
    public boolean removeFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return op.remove(CPF, Gerente.class);

    }


    @Override
    public String toString() {
        return "Cargo: Gerente "+super.toString();
    }

    public static String getCargo() {
        return cargo;
    }
}
