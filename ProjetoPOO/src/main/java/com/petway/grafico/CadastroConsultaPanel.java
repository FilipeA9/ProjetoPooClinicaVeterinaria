package com.petway.grafico;

import com.petway.entidades.Consulta;
import com.petway.entidades.Pet;
import com.petway.entidades.Veterinario;
import com.petway.operacoes.Operacoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CadastroConsultaPanel extends JPanel{
    private JPanel paginaAtual = this;
    private JPanel painelPrincipal;
    private JTextField campoID;
    private JLabel textoID;
    private JComboBox opcoesVet;
    private JLabel txtData;
    private JComboBox campoDia;
    private JComboBox campoAno;
    private JComboBox campoMes;
    private JLabel txtDia;
    private JLabel txtMes;
    private JLabel txtAno;
    private JComboBox campoHora;
    private JComboBox campoMinuto;
    private JLabel txtHorario;
    private JTextField campoValor;
    private JLabel txtValor;
    private JButton finalizar;
    private JButton fecharPagina;

    private Consulta consultaExistente = null;
public CadastroConsultaPanel(JTabbedPane pagina_anterior) {

    setLayout(new BorderLayout());
    add(painelPrincipal, BorderLayout.CENTER);


    // Definindo o vetor de dias
    DefaultComboBoxModel<Integer> comboBoxModelDia = new DefaultComboBoxModel<Integer>();
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
    int[] ano = new int[54]; // De 1970 a 2023
    for (int i = 0; i < 54; i++) {
        ano[i] = 1970 + i;
        comboBoxModelAno.addElement(ano[i]);
    }
    campoAno.setModel(comboBoxModelAno);

    //Definindo o verto de horas
    DefaultComboBoxModel<Integer> comboBoxModelHora = new DefaultComboBoxModel<>();
    Integer[] horas = new Integer[24];
    for(int i=0; i<24; i++){
        horas[i] = i;
        comboBoxModelHora.addElement(horas[i]);
    }
    campoHora.setModel(comboBoxModelHora);

    //Definindo o verto de minutos
    DefaultComboBoxModel<Integer> comboBoxModelMinuto = new DefaultComboBoxModel<>();
    Integer[] minutos = new Integer[60];
    for(int i=0; i<60; i++){
        minutos[i] = i;
        comboBoxModelMinuto.addElement(minutos[i]);
    }
    campoMinuto.setModel(comboBoxModelMinuto);

    //Definindo o vetor de veterinarios
    Operacoes op = new Operacoes();
    DefaultComboBoxModel<Veterinario> comboBoxModelVet = new DefaultComboBoxModel<>();
    List<Veterinario> veterinarios = op.pesquisaLista(Veterinario.class);
    for (Veterinario veterinario : veterinarios) {
        // Veterinario vet = veterinarios.get(i);
        // String opcao = vet.getNome()+" - cpf: "+vet.getCPF();
        comboBoxModelVet.addElement(veterinario);
    }
    opcoesVet.setModel(comboBoxModelVet);
    opcoesVet.setRenderer(renderer);




    finalizar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                    int idPet = Integer.parseInt(campoID.getText());
                    Pet pet = Pet.pesquisaPet(idPet);
                    if(pet == null){
                        JOptionPane.showMessageDialog(null, "Pet não Cadastrado! ", "Erro ao pesquisar Pet", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Veterinario vet = (Veterinario) opcoesVet.getSelectedItem();
                        LocalTime horario = LocalTime.of( (int) campoHora.getSelectedItem() , (int) campoMinuto.getSelectedItem());
                        LocalDate data = LocalDate.of((int)campoAno.getSelectedItem(), (int)campoMes.getSelectedItem(), (int) campoDia.getSelectedItem());
                        double valor = Double.parseDouble(campoValor.getText()) ;

                        if(consultaExistente == null){
                            // se estiver cadastrando uma consulta nova
                            Consulta consulta = Consulta.criaConsulta(horario, data, valor, vet, pet);
                            if(consulta!=null){
                                JOptionPane.showMessageDialog(null, "Consulta Cadastrada! ", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "Consulta não Cadastrada! Confira as informações preenchidas ", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                            }

                        }else{
                            // se estiver atualizando uma consulta

                            consultaExistente.setData(data);
                            consultaExistente.setHorario(horario);
                            consultaExistente.setPet(pet);
                            consultaExistente.setValor(valor);
                            consultaExistente.setVeterinario(vet);


                            if(consultaExistente.salvar()){
                                JOptionPane.showMessageDialog(null, "Consulta Atualizada! ", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "Consulta não Atualizada! Confira as informações preenchidas ", "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
        }

    });
    fecharPagina.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pagina_anterior.removeTabAt(pagina_anterior.indexOfComponent(paginaAtual));
        }
    });
}

    // Criando a implementação do ListCellRenderer para formatar a exibição
    ListCellRenderer<Veterinario> renderer = new ListCellRenderer<Veterinario>() {
        @Override
        public Component getListCellRendererComponent(
                JList<? extends Veterinario> list,
                Veterinario value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            JLabel label = new JLabel(value.getNome() + " - CPF: " + value.getCPF());

            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            } else {
                label.setBackground(list.getBackground());
                label.setForeground(list.getForeground());
            }

            return label;
        }
    };

    public void setConsultaExistente(Consulta consultaExistente) {
        this.consultaExistente = consultaExistente;

        campoID.setText(String.valueOf(consultaExistente.getPet().getIDPet()));
        campoValor.setText(String.valueOf(consultaExistente.getValor()));
        campoMinuto.setSelectedItem(consultaExistente.getHorario().getMinute());
        campoHora.setSelectedItem(consultaExistente.getHorario().getHour());
        campoDia.setSelectedItem(consultaExistente.getData().getDayOfMonth());
        campoMes.setSelectedItem(consultaExistente.getData().getMonthValue());
        campoAno.setSelectedItem(consultaExistente.getData().getYear());
        opcoesVet.setSelectedItem(consultaExistente.getVeterinario());
    }
}
