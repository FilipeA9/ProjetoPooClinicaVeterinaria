package com.petway.entidades;

import com.petway.operacoes.Operacoes;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Aux_Vet")
public class AuxiliarVeterinario extends Funcionario {

    private static String cargo = "Auxiliar Veterinário";

    public AuxiliarVeterinario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada){
        super(Nome, CPF, Remuneracao, Data_Nasc, Data_Entrada);
    }

    public AuxiliarVeterinario() {

    }

    @Override
    public Funcionario criaFuncionario(String Nome, String CPF, double Remuneracao, LocalDate Data_Nasc, LocalDate Data_Entrada) {
       AuxiliarVeterinario aux = new AuxiliarVeterinario(Nome, CPF,Remuneracao,Data_Nasc, Data_Entrada);
        Operacoes op = new Operacoes();
        op.inserir(aux);
        return aux;
    }

    @Override
    public Funcionario pesquisaFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return  (AuxiliarVeterinario) op.pesquisa(CPF, AuxiliarVeterinario.class);
    }

    @Override
    public boolean removeFuncionario(String CPF) {
        Operacoes op = new Operacoes();
        return op.remove(CPF, AuxiliarVeterinario.class);

    }


    @Override
    public String toString() {
        return "Cargo: Auxiliar Veterinário: "+super.toString();
    }

    public static String getCargo() {
        return cargo;
    }
}
