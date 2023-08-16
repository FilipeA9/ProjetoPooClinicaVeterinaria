package com.petway.grafico;

import com.petway.entidades.Internacao;
import com.petway.entidades.Prontuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CadastroProntuarioPanel extends JPanel {
    private JPanel paginaAtual = this;
    private JTextField campoID;
    private JTextField campoMed;
    private JTextField campoAlim;
    private JTextField campoDiastole;
    private JTextField campoSistole;
    private JTextField campoTemp;
    private JComboBox campoDia;
    private JComboBox campoMes;
    private JComboBox campoAno;
    private JComboBox campoHora;
    private JComboBox campoMinuto;
    private JButton finalizarCadastro;
    private JButton fecharPágina;
    private JPanel painelPrincipal;
    private  Prontuario prontuarioExistente = null;

    public CadastroProntuarioPanel(JTabbedPane pagina_anterior) {

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


    finalizarCadastro.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LocalTime horario = LocalTime.of( (int) campoHora.getSelectedItem() , (int) campoMinuto.getSelectedItem());
            LocalDate data = LocalDate.of((int)campoAno.getSelectedItem(), (int)campoMes.getSelectedItem(), (int) campoDia.getSelectedItem());

            String alimentacao = campoAlim.getText();
            String medicacao = campoMed.getText();
            String pressao = campoSistole.getText()+"/"+campoDiastole.getText();
            double temperatura = Double.parseDouble(campoTemp.getText());

            Internacao  internacao = Internacao.pesquisaInternacao(Integer.parseInt(campoID.getText()));

            if(internacao==null){
                JOptionPane.showMessageDialog(null, "Internação não encontrada! Insira um ID válido! ", "Erro ao pesquisar", JOptionPane.ERROR_MESSAGE);
            }else{

                if(prontuarioExistente == null){
                    Prontuario prontuario = Prontuario.criaProntuario(medicacao, LocalDateTime.of(data, horario), alimentacao, pressao, temperatura, internacao );

                    if(prontuario!= null){
                        JOptionPane.showMessageDialog(null, "Internação cadastrada!", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Internação não cadastrada!", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                    }

                }else{
                    prontuarioExistente.setInternacao(internacao);
                    prontuarioExistente.setAlimentacoes(alimentacao);
                    prontuarioExistente.setDataHora(LocalDateTime.of(data, horario));
                    prontuarioExistente.setPressao(pressao);
                    prontuarioExistente.setTemp(temperatura);
                    prontuarioExistente.setMedAplicada(medicacao);

                    if(prontuarioExistente.salvar()){
                        JOptionPane.showMessageDialog(null, "Internação Atualizada!", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Internação não Atualizada! verifique os valores preenchidos", "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }
    });
    fecharPágina.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pagina_anterior.removeTabAt(pagina_anterior.indexOfComponent(paginaAtual));
        }
    });
}

    public void setProntuarioExistente(Prontuario prontuarioExistente) {
        this.prontuarioExistente = prontuarioExistente;

        campoID.setText(String.valueOf(prontuarioExistente.getInternacao().getIDInternacao()));
        campoMed.setText(prontuarioExistente.getMedAplicada());
        campoAlim.setText(prontuarioExistente.getAlimentacoes());
        campoTemp.setText(String.valueOf(prontuarioExistente.getTemp()));

        campoDia.setSelectedItem(prontuarioExistente.getDataHora().getDayOfMonth());
        campoMes.setSelectedItem(prontuarioExistente.getDataHora().getMonthValue());
        campoAno.setSelectedItem(prontuarioExistente.getDataHora().getYear());

        campoHora.setSelectedItem(prontuarioExistente.getDataHora().getHour());
        campoMinuto.setSelectedItem(prontuarioExistente.getDataHora().getMinute());

        String[] valores = prontuarioExistente.getPressao().split("/");

        campoDiastole.setText(valores[1]);
        campoSistole.setText(valores[0]);


    }
}
