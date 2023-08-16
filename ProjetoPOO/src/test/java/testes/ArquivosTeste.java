package testes;

import com.github.javafaker.Faker;
import com.petway.entidades.*;
import com.petway.operacoes.JPAUtil;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class ArquivosTeste {

    Faker faker = new Faker();

    public String geraCPF(){
        long minimo = (long) Math.pow(10, 11 - 1);
        long maximo = (long) Math.pow(10, 11) - 1;

        Random random = new Random();
        return String.valueOf(minimo + random.nextLong() % (maximo - minimo + 1));
    }

    /**
     * Gera um tutor com dados aleatorios
     * @return Uma instância de tutor já adicionada no Banco de dados
     * */
    private Tutor randomTutor(){

        String nome = faker.name().fullName();
        String cpf = geraCPF();
        String endereco = faker.address().fullAddress();

        return  Tutor.criaTutor(cpf, nome, endereco);

    }

    private Pet randomPet(Tutor tutor){
        String nome = faker.name().fullName();
        String especie = "canino";
        String raca = "Sem raça definida";
        String sexo = "masculino";
        LocalDate dataNascimento = LocalDate.of(1990,12,04);

        return Pet.criaPet(nome, especie, raca, dataNascimento, tutor, sexo);

    }

    private Veterinario randomVet(){
        LocalDate dataNascimento = LocalDate.of(1990,12,04);
        LocalDate dataEntrada = LocalDate.of(2012, 01, 01);
        Veterinario veterinario = new Veterinario();
        String cpf = geraCPF();
        return (Veterinario) veterinario.criaFuncionario("Monica", cpf, 5000.00, dataNascimento, dataEntrada);

    }

    private Consulta randomConsulta(Pet pet, Veterinario vet){
        LocalDate data = LocalDate.of(2023, 8, 15);
        LocalTime hora = LocalTime.of(15, 30);
        return Consulta.criaConsulta(hora, data, 150.00, vet, pet);
    }

    private

    @AfterAll
    void tearDown() {
        JPAUtil.closeEntityManagerFactory();
    }

    @Test
    public void testaConexaoBD(){
        try {
            Configuration configuration = new Configuration().configure();
            SessionFactory sessionFactory = configuration.buildSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            // Verifica se a conexão foi estabelecida corretamente
            System.out.println("Conexão com o banco de dados bem-sucedida!");

            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }


   @Test
    public void testTutorPet() {
        // Instanciando um Tutor
       String nome = faker.name().fullName();
       String cpf = geraCPF();
       String endereco = faker.address().fullAddress();

       Tutor tutor =  Tutor.criaTutor(cpf, nome, endereco);

        // Instanciando um Pet
        LocalDate dataNascimentoPet = LocalDate.of(2022, 01, 03); // Data de Nascimento do Pet (você pode ajustar a data conforme necessário)
        Pet pet = Pet.criaPet( "Toby", "Canina", "Vira-Lata", dataNascimentoPet, tutor, "Masculino");

        // Testando se os atributos foram configurados corretamente
        assertEquals(cpf, tutor.getCPF());
        assertEquals(nome, tutor.getNome());
        assertEquals(endereco, tutor.getEndr());

        assertEquals("Vira-Lata", pet.getRaca());
        assertEquals("Canina", pet.getEspecie());
        assertEquals("Toby", pet.getNome());
        assertEquals(dataNascimentoPet, pet.getData_Nasc());
        assertEquals(tutor, pet.getTutor());

        Pet.removePet(pet.getIDPet());
        Tutor.removeTutor(cpf);
    }

    @Test
    public void testCriaVeterinário(){
        LocalDate dataNascimento = LocalDate.of(1976,12,04);
        LocalDate dataEntrada = LocalDate.of(2012, 01, 01);
        Veterinario veterinario = new Veterinario();
        String cpf = geraCPF();
        String nome = faker.name().fullName();
        veterinario = (Veterinario) veterinario.criaFuncionario(nome, cpf, 5000.00, dataNascimento, dataEntrada);

        assertEquals(nome, veterinario.getNome());
        assertEquals(cpf, veterinario.getCPF());
        assertEquals(5000.00, veterinario.getRemuneracao(), 0.0001);
        assertEquals(dataNascimento, veterinario.getData_Nasc());
        assertEquals(dataEntrada, veterinario.getData_Entrada());

        veterinario.removeFuncionario(cpf);

    }

    @Test
    public void testCriaGerente(){
        // instanciando um gerente
        String nome = faker.name().fullName();
        String CPF = geraCPF();
        LocalDate dataNasc = LocalDate.of(2000, 4, 5);
        LocalDate dataAdm = LocalDate.of(2023, 8, 11);
        Gerente gerente = new Gerente();
        gerente = (Gerente) gerente.criaFuncionario(nome, CPF, 5000.00 ,  dataNasc, dataAdm);

        assertEquals(nome, gerente.getNome());
        assertEquals(CPF, gerente.getCPF());
        assertEquals(5000.00, gerente.getRemuneracao(), 0.0001);
        assertEquals(dataNasc, gerente.getData_Nasc());
        assertEquals(dataAdm, gerente.getData_Entrada());
        gerente.removeFuncionario(CPF);


    }

    @Test
    public void testCriaAuxVeterinario(){
        String nome = faker.name().fullName();
        String CPF = geraCPF();
        LocalDate dataNasc = LocalDate.of(2000, 4, 5);
        LocalDate dataAdm = LocalDate.of(2023, 8, 11);
        AuxiliarVeterinario aux = new AuxiliarVeterinario();
        aux =(AuxiliarVeterinario) aux.criaFuncionario(nome,CPF, 2000.00, dataNasc, dataAdm);

        assertEquals(nome, aux.getNome());
        assertEquals(CPF, aux.getCPF());
        assertEquals(2000.00, aux.getRemuneracao(), 0.0001);
        assertEquals(dataNasc, aux.getData_Nasc());
        assertEquals(dataAdm, aux.getData_Entrada());
       aux.removeFuncionario(CPF);

    }

    @Test
    public void testCriaRecepcionista(){
        String nome = faker.name().fullName();
        String CPF = geraCPF();
        LocalDate dataNasc = LocalDate.of(2000, 4, 5);
        LocalDate dataAdm = LocalDate.of(2023, 8, 11);
        Recepcionista recp = new Recepcionista();
        recp = (Recepcionista) recp.criaFuncionario(nome,CPF, 1500.00, dataNasc, dataAdm);

        assertEquals(nome, recp.getNome());
        assertEquals(CPF, recp.getCPF());
        assertEquals(1500.00, recp.getRemuneracao(), 0.0001);
        assertEquals(dataNasc, recp.getData_Nasc());
        assertEquals(dataAdm, recp.getData_Entrada());
        recp.removeFuncionario(CPF);

    }

    @Test
    public void testCriaConsulta(){
        Veterinario vet = randomVet();
        Tutor tutor = randomTutor();
        Pet pet = randomPet(tutor);
        LocalDate data = LocalDate.of(2023, 8, 15);
        LocalTime hora = LocalTime.of(15, 30);
        Consulta consulta = Consulta.criaConsulta(hora, data, 150.00, vet, pet);

        assertEquals(vet, consulta.getVeterinario());
        assertEquals(pet, consulta.getPet());
        assertEquals(150, consulta.getValor(), 0.0001);
        assertEquals(data, consulta.getData());
        assertEquals(hora, consulta.getHorario());
        Consulta.removeConsulta(consulta.getIDConsulta());
        Pet.removePet(pet.getIDPet());
        Tutor.removeTutor(tutor.getCPF());
        vet.removeFuncionario(vet.getCPF());

    }

    @Test
    public void testCriaExame(){
        Veterinario vet = randomVet();
        Tutor tutor = randomTutor();
        Pet pet = randomPet(tutor);
        Consulta consulta = randomConsulta(pet, vet);
        LocalDate data = LocalDate.of(2023, 8, 15);
        LocalTime hora = LocalTime.of(15, 30);
        String tipo ="Raio-x";
        String laudo = "Fratura de coluna";
        Exame ex = Exame.criaExame( tipo, laudo, data, hora, consulta, 100.00);

        assertEquals(consulta, ex.getConsulta());
        assertEquals(tipo, ex.getTipo());
        assertEquals(laudo, ex.getLaudo());
        assertEquals(100, ex.getValor(), 0.0001);
        assertEquals(data, ex.getData());
        assertEquals(hora, ex.getHorario());

    }

    @Test
    public void testCriaInternacao(){

    }

    @Test
    public void testCriaProntuario(){

    }

    @Test
    public void testCriaObsInternacao(){

    }

    @Test
    public void testRecuperaFuncionario(){

    }

    @Test
    public void testRecuperaTutor(){

    }

    @Test
    public void testRecuperaPet(){

    }

    @Test
    public void testRecuperaConsulta(){

    }
}
