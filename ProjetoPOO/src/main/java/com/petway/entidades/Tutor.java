package com.petway.entidades;

import com.petway.operacoes.Operacoes;
import com.petway.operacoes.OperacoesException;

import javax.persistence.*;
import javax.swing.*;

@Entity

public class Tutor {

        @Id
        private String CPF;
        private String Endr;
        private String Nome;


        public Tutor(){}
        private Tutor( String CPF, String Nome, String Endr){
                this.CPF = CPF;
                this.Endr = Endr;
                this.Nome = Nome;

        }

        public static Tutor criaTutor( String CPF, String Nome, String Endr){

                Tutor tutor = new Tutor(CPF, Nome, Endr);
               Operacoes op = new Operacoes();
                op.inserir(tutor);
                return tutor;
        }

        //metodo que retorna um objeto Tutor a partir da pesquisa pelo cpf no Banco de dados
        public static Tutor pesquisaTutorCPF (String CPF) {
                Operacoes op = new Operacoes();
                return (Tutor) op.pesquisa(CPF, Tutor.class);
        }

        // metodo que remove um tutor pelo cpf
        public static boolean removeTutor(String CPF){

                Operacoes op = new Operacoes();
                return op.remove(CPF, Tutor.class);
        }

        public boolean salvar(){
                Operacoes op = new Operacoes();
                return op.salvar(this);
        }



        public String getCPF() { return CPF; }

        public String getEndr() { return Endr; }

        public String getNome() { return Nome; }

        public void setCPF(String CPF) { this.CPF = CPF; }

        public void setEndr(String endr) { this.Endr = endr; }

        public void setNome(String nome) { this.Nome = nome; }

        @Override
        public String toString() {
                return ("Nome: "+Nome+" CPF: "+CPF+" Endereco: "+Endr);
        }
}
