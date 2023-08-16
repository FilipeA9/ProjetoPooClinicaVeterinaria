package com.petway.entidades;

import com.petway.operacoes.Operacoes;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe que representa um Pet.
 * Armazena informações relacionadas a animais de estimação.
 */

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDPet;
    private String Nome;
    private String Especie;
    private String Raca;
    private String Sexo;
    private LocalDate Data_Nasc;
    @ManyToOne
    @JoinColumn(name = "CPF_Tutor", referencedColumnName = "CPF")
    private Tutor tutor;

    /**
     * Construtor da classe Pet.
     *
     * @param Nome      Nome do pet.
     * @param Especie   Espécie do pet.
     * @param Raca      Raça do pet.
     * @param Data_Nasc Data de nascimento do pet.
     * @param tutor     Tutor responsável pelo pet.
     * @param sexo      Sexo do pet.
     */
    private Pet( String Nome,String Especie,  String Raca,  LocalDate Data_Nasc, Tutor tutor, String sexo){
        this.Nome = Nome;
        this.Data_Nasc = Data_Nasc;
        this.tutor = tutor;
        this.Raca = Raca;
        this.Especie = Especie;
        this.Sexo = sexo;

    }
    /**
     * Construtor vazio da classe Pet obrigatório do Hibernate.
     */

    public Pet() {

    }

    /**
     * Cria um novo pet e o insere no banco de dados.
     *
     * @param Nome      Nome do pet.
     * @param Especie   Espécie do pet.
     * @param Raca      Raça do pet.
     * @param Data_Nasc Data de nascimento do pet.
     * @param tutor     Tutor responsável pelo pet.
     * @param sexo      Sexo do pet.
     * @return Objeto Pet criado e inserido no banco de dados.
     */
    public static Pet criaPet( String Nome,String Especie,  String Raca,  LocalDate Data_Nasc, Tutor tutor, String sexo){

        Pet pet = new Pet( Nome, Especie, Raca, Data_Nasc, tutor, sexo);
        Operacoes op = new Operacoes();
        op.inserir(pet);
        return pet;
    }

    /**
     * Pesquisa um pet no banco de dados pelo ID.
     *
     * @param IDPet ID do pet a ser pesquisado.
     * @return Objeto Pet encontrado, ou null se não encontrado.
     */

    public static Pet pesquisaPet (int IDPet) {
        Operacoes op = new Operacoes();
        return (Pet) op.pesquisa(IDPet, Pet.class);
    }

    /**
     * Remove um pet do banco de dados pelo ID.
     *
     * @param IDPet ID do pet a ser removido.
     * @return true se o pet foi removido com sucesso, false caso contrário.
     */

    public static boolean removePet(int IDPet){
        Operacoes op = new Operacoes();
        return op.remove(IDPet, Pet.class); }

    /**
     * Salva as alterações feitas no pet no banco de dados.
     *
     * @return true se o pet foi salvo com sucesso, false caso contrário.
     */

    public boolean salvar(){

        Operacoes op = new Operacoes();
        return op.salvar(this);
    }

    public int getIDPet() {
        return IDPet;
    }

    public String getNome() {
        return Nome;
    }

    public LocalDate getData_Nasc() {
        return Data_Nasc;
    }

    public String getRaca() {
        return Raca;
    }

    public String getEspecie() {
        return Especie;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public void setRaca(String raca) {
        Raca = raca;
    }

    public void setData_Nasc(LocalDate data_Nasc) {
        Data_Nasc = data_Nasc;
    }

    /**
     * Retorna uma representação em formato de String do objeto Pet.
     *
     * @return Representação em String do objeto Pet.
     */

    @Override
    public String toString() {
        return("Nome: "+Nome+" ID :"+IDPet+" especie: "+Especie+" raca: "+Raca+" Data de nascimento: "+Data_Nasc);
    }
}
