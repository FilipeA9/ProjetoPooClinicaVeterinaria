package com.petway.grafico;

import com.petway.entidades.Consulta;
import com.petway.entidades.Exame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CadastroExamePanel extends JPanel {
    private JPanel paginaAtual = this;
    private JComboBox campoTipo;
    private JTextField campoID;
    private JTextField campoValor;
    private JComboBox campoDia;
    private JComboBox campoMes;
    private JComboBox campoAno;
    private JComboBox campoHora;
    private JComboBox campoMinuto;
    private JTextArea campoLaudo;
    private JPanel txtLaudo;
    private JButton finalizarCadastroButton;
    private JButton fecharPáginaButton;
    private JPanel painelPrincipal;
    private Exame ExameExistente = null;

    public CadastroExamePanel(JTabbedPane pagina_anterior) {

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

        //Definindo o vetor de horas
        DefaultComboBoxModel<Integer> comboBoxModelHora = new DefaultComboBoxModel<>();
        Integer[] horas = new Integer[24];
        for(int i=0; i<24; i++){
            horas[i] = i;
            comboBoxModelHora.addElement(horas[i]);
        }
        campoHora.setModel(comboBoxModelHora);

        //Definindo o vetor de minutos
        DefaultComboBoxModel<Integer> comboBoxModelMinuto = new DefaultComboBoxModel<>();
        Integer[] minutos = new Integer[60];
        for(int i=0; i<60; i++){
            minutos[i] = i;
            comboBoxModelMinuto.addElement(minutos[i]);
        }
        campoMinuto.setModel(comboBoxModelMinuto);

        // definindo opções de tipo de exame
        DefaultComboBoxModel<String> comboBoxModelTipo = new DefaultComboBoxModel<>();
        String[] tipos ={"Hemograma","Raio-x", "Ultrassom", "Endoscopia", "Biopsia"};
        comboBoxModelTipo.addAll(List.of(tipos));
        campoTipo.setModel(comboBoxModelTipo);
        String opcaoPreSelecionada = "Hemograma";
        campoTipo.setSelectedItem(opcaoPreSelecionada);




    finalizarCadastroButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String tipo = (String) campoTipo.getSelectedItem();
            String laudo = campoLaudo.getText();
            double valor = Double.parseDouble(campoValor.getText());
            LocalDate data = LocalDate.of((int) campoAno.getSelectedItem(), (int) campoMes.getSelectedItem(), (int) campoDia.getSelectedItem());
            LocalTime horario = LocalTime.of((int) campoHora.getSelectedItem(), (int) campoMinuto.getSelectedItem());

            Consulta consulta = Consulta.pesquisaConsulta(Integer.parseInt(campoID.getText()));

            if(consulta == null){
                JOptionPane.showMessageDialog(null, "Consulta não encontrada! Insira um ID válido! ", "Exito ao pesquisar", JOptionPane.ERROR_MESSAGE );
            }else{

                if(ExameExistente == null){
                    // está criando um novo exame
                    Exame exame = Exame.criaExame(tipo, laudo, data, horario, consulta, valor);

                    if(exame!=null){
                        JOptionPane.showMessageDialog(null, "Exame Cadastrado! ", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Exame não Cadastrado! ", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                    }


                }else{
                    //está atualizando um exame
                    ExameExistente.setData(data);
                    ExameExistente.setHorario(horario);
                    ExameExistente.setValor(valor);
                    ExameExistente.setTipo(tipo);
                    ExameExistente.setLaudo(laudo);
                    ExameExistente.setConsulta(consulta);

                    if(ExameExistente.salvar()){
                        JOptionPane.showMessageDialog(null, "Exame Atualizado! ", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Exame não Atualizado! ", "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

        }
    });


    fecharPáginaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pagina_anterior.removeTabAt(pagina_anterior.indexOfComponent(paginaAtual));
        }
    });

}
    public void setExameExistente(Exame ExameExistente) {
        this.ExameExistente = ExameExistente;

        campoID.setText(String.valueOf(ExameExistente.getConsulta().getIDConsulta()));
        campoValor.setText(String.valueOf(ExameExistente.getValor()));
        campoMinuto.setSelectedItem(ExameExistente.getHorario().getMinute());
        campoHora.setSelectedItem(ExameExistente.getHorario().getHour());
        campoDia.setSelectedItem(ExameExistente.getData().getDayOfMonth());
        campoMes.setSelectedItem(ExameExistente.getData().getMonthValue());
        campoAno.setSelectedItem(ExameExistente.getData().getYear());
        campoTipo.setSelectedItem(ExameExistente.getTipo());
        campoLaudo.setText(ExameExistente.getLaudo());
    }
}
