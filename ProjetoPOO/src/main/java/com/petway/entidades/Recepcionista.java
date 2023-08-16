package com.petway.entidades;

import com.petway.operacoes.Operacoes;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Recepcionista")
public class Recepcionista extends Funcionario{

    private static String cargo = "Recepcionista";

    public Recepcionista(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada){
        super(Nome, CPF, Remuneracao, Data_Nasc, Data_Entrada);
    }

    public Recepcionista() {

    }

    @Override
    public Funcionario criaFuncionario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada) {
         Recepcionista recepcionista = new Recepcionista(Nome, CPF,Remuneracao,Data_Nasc, Data_Entrada);
        Operacoes op = new Operacoes();
        op.inserir(recepcionista);
        return recepcionista;
    }

    @Override
    public Funcionario pesquisaFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return  (Recepcionista) op.pesquisa(CPF, Recepcionista.class);
    }

    @Override
    public boolean removeFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return op.remove(CPF, Recepcionista.class);

    }


    @Override
    public String toString() {
        return "Cargo: Recepcionista "+super.toString();
    }

    public static String getCargo() {
        return cargo;
    }
}
