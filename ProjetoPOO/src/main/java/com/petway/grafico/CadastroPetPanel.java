package com.petway.grafico;

import com.petway.entidades.Pet;
import com.petway.entidades.Tutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CadastroPetPanel extends JPanel {
    private JPanel painel_atual =  this;
    private JPanel PainelPrincipal;
    private JTextField campoNome;
    private JComboBox<String> campoEspecie ;
    private JTextField campoRaca;
    private JComboBox<String> campoSexo ;

    private JComboBox<Integer> campoDia;
    private JComboBox<Integer> campoMes;
    private JComboBox<Integer>campoAno;
    private JTextField campoCPF;
    private JPanel InfoNome;
    private JPanel InfoEspecie;
    private JPanel InfoRaca;
    private JPanel InfoSexo;
    private JPanel InfoData;
    private JPanel InfoTutor;
    private JButton finalizarCadastro;
    private JButton fecharPagina;
    private JLabel DataNasc;
    private Pet petExistente = null;

    public CadastroPetPanel(JTabbedPane painel_anterior){

        setLayout(new BorderLayout());
        add(PainelPrincipal, BorderLayout.CENTER);

        //definindo o valor das opces em especie
        DefaultComboBoxModel<String> comboBoxModelEspecie = new DefaultComboBoxModel<>();
        comboBoxModelEspecie.addElement("Felino");
        comboBoxModelEspecie.addElement("Canino");
        comboBoxModelEspecie.addElement("Equino");
        campoEspecie.setModel(comboBoxModelEspecie);

        // definindo o valor das opcoes em sexo
        DefaultComboBoxModel<String> comboBoxModelSexo = new DefaultComboBoxModel<>();
        comboBoxModelSexo.addElement("Feminino");
        comboBoxModelSexo.addElement("Masculino");
        campoSexo.setModel(comboBoxModelSexo);


        // Definindo o vetor de dias
        DefaultComboBoxModel<Integer> comboBoxModelDia = new DefaultComboBoxModel<>();
        int[] dia = new int[31];
        for (int i = 0; i < 31; i++) {
            dia[i] = i + 1;
            comboBoxModelDia.addElement(dia[i]);
        }
        campoDia.setModel(comboBoxModelDia);

        // Definindo o vetor de meses
        DefaultComboBoxModel<Integer> comboBoxModelMes = new DefaultComboBoxModel<>();
        int[] mes = new int[12];
        for (int i = 0; i < 12; i++) {
            mes[i] = i + 1;
            comboBoxModelMes.addElement(mes[i]);
        }
        campoMes.setModel(comboBoxModelMes);

        // Definindo o vetor de anos
        DefaultComboBoxModel<Integer> comboBoxModelAno = new DefaultComboBoxModel<>();
        int[ ] ano = new int[54]; // De 1970 a 2023
        for (int i = 0; i < 54; i++) {
            ano[i] = 1970 + i;
            comboBoxModelAno.addElement(ano[i]);
        }
        campoAno.setModel(comboBoxModelAno);



        finalizarCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String cpf = campoCPF.getText();

                Tutor tutor = Tutor.pesquisaTutorCPF(cpf);
                if(tutor!= null){

                    String nome = campoNome.getText();
                    String especie = (String) campoEspecie.getSelectedItem();
                    String raca = campoRaca.getText();
                    String sexoPet = (String) campoSexo.getSelectedItem();

                    int dia =  (int) campoDia.getSelectedItem();
                    int mes =  (int) campoMes.getSelectedItem();
                    int ano =  (int) campoAno.getSelectedItem();
                    LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

                        if (petExistente == null) {

                            Pet pet = Pet.criaPet(nome, especie, raca, dataNascimento, tutor, sexoPet );

                            if(pet != null){
                                JOptionPane.showMessageDialog(null, "Pet Cadastrado! ", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Erro no sistema ou Pet já cadastrado ", "Erro ao Cadastrar", JOptionPane.ERROR_MESSAGE);
                            }

                        }else{
                            petExistente.setTutor(tutor);
                            petExistente.setNome(nome);
                            petExistente.setEspecie(especie);
                            petExistente.setRaca(raca);
                            petExistente.setData_Nasc(dataNascimento);
                            petExistente.setSexo(sexoPet);

                            if(petExistente.salvar()){
                                JOptionPane.showMessageDialog(null, "Pet Atualizado! ", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "Erro ao Atualizar ", "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                }
                else{
                    JOptionPane.showMessageDialog(null, "Erro no sistema ou Tutor não cadastrado ", "Erro ao Pesquisar", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        fecharPagina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel_anterior.removeTabAt(painel_anterior.indexOfComponent(painel_atual));
            }
        });
    }

    public void setPetExistente(Pet petExistente) {
        this.petExistente = petExistente;

        campoNome.setText(petExistente.getNome());
        campoEspecie.setSelectedItem(petExistente.getEspecie());
        campoRaca.setText(petExistente.getRaca());
        campoSexo.setSelectedItem(petExistente.getSexo());
        campoCPF.setText(petExistente.getTutor().getCPF());

        campoDia.setSelectedItem(petExistente.getData_Nasc().getDayOfMonth());
        campoMes.setSelectedItem(petExistente.getData_Nasc().getMonthValue());
        campoAno.setSelectedItem(petExistente.getData_Nasc().getYear());
    }
}
