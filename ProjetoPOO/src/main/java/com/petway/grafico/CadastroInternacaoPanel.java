package com.petway.grafico;

import com.petway.entidades.Consulta;
import com.petway.entidades.Internacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CadastroInternacaoPanel extends JPanel {
    private JPanel paginaAtual = this;
    private JTextField campoID;
    private JTextField campoValor;
    private JComboBox campoDiaEntrada;
    private JComboBox campoMesEntrada;
    private JComboBox campoAnoEntrada;
    private JComboBox campoDiaSaida;
    private JComboBox campoMesSaida;
    private JComboBox campoAnoSaida;
    private JCheckBox checkLiberado;
    private JLabel txtValor;
    private JLabel txtID;
    private JLabel txtDiaSaida;
    private JButton finalizarCadastroButton;
    private JButton fecharPáginaButton;
    private JPanel painelPrincipal;
    private JPanel painelDataSaida;
    private Internacao internacaoExistente = null;

    public CadastroInternacaoPanel(JTabbedPane pagina_anterior) {

    setLayout(new BorderLayout());
    add(painelPrincipal, BorderLayout.CENTER);

    painelDataSaida.setVisible(false);


    // Definindo o vetor de dias
    DefaultComboBoxModel<Integer> comboBoxModelDiaEntrada = new DefaultComboBoxModel<Integer>();
        DefaultComboBoxModel<Integer> comboBoxModelDiaSaida = new DefaultComboBoxModel<Integer>();
    int[] dia = new int[31];
    for (int i = 0; i < 31; i++) {
        dia[i] = i + 1;
        comboBoxModelDiaEntrada.addElement(dia[i]);
        comboBoxModelDiaSaida.addElement(dia[i]);
    }
    campoDiaEntrada.setModel(comboBoxModelDiaEntrada);
    campoDiaSaida.setModel(comboBoxModelDiaSaida);


    // Definindo o vetor de meses
    DefaultComboBoxModel<Integer> comboBoxModelMesEntrada = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Integer> comboBoxModelMesSaida = new DefaultComboBoxModel<>();
    int[] mes = new int[12];
    for (int i = 0; i < 12; i++) {
        mes[i] = i + 1;
        comboBoxModelMesEntrada.addElement(mes[i]);
        comboBoxModelMesSaida.addElement(mes[i]);
    }
    campoMesEntrada.setModel(comboBoxModelMesEntrada);
    campoMesSaida.setModel(comboBoxModelMesSaida);


    // Definindo o vetor de anos
    DefaultComboBoxModel<Integer> comboBoxModelAnoEntrada = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Integer> comboBoxModelAnoSaida = new DefaultComboBoxModel<>();
    int[] ano = new int[54]; // De 1970 a 2023
    for (int i = 0; i < 54; i++) {
        ano[i] = 1970 + i;
        comboBoxModelAnoEntrada.addElement(ano[i]);
        comboBoxModelAnoSaida.addElement(ano[i]);
    }
    campoAnoEntrada.setModel(comboBoxModelAnoEntrada);
    campoAnoSaida.setModel(comboBoxModelAnoSaida);


    finalizarCadastroButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LocalDate dataEntrada = LocalDate.of((int) campoAnoEntrada.getSelectedItem(), (int) campoMesEntrada.getSelectedItem(), (int) campoDiaEntrada.getSelectedItem());
            double valor = Double.parseDouble(campoValor.getText());
            Consulta consulta = Consulta.pesquisaConsulta(Integer.parseInt(campoID.getText()));

            if(consulta == null){
                JOptionPane.showMessageDialog(null, "Consulta não encontrada! Insira um ID válido! ", "Erro ao pesquisar", JOptionPane.ERROR_MESSAGE);
            }

            Internacao internacao = null;

            if(checkLiberado.isSelected()){ // possui data de saida
                LocalDate dataSaida = LocalDate.of((int) campoAnoSaida.getSelectedItem(), (int) campoMesSaida.getSelectedItem(), (int) campoDiaSaida.getSelectedItem());

                if(internacaoExistente == null){ // esta criando internacao

                    internacao = Internacao.criaInternacao(dataSaida, dataEntrada, valor, consulta);

                    if(internacao!=null){
                        JOptionPane.showMessageDialog(null, "Internação Cadastrada! ", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "Internação não Cadastrada! ", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                    }

                }else{//esta atualizando uma internacao
                    internacaoExistente.setConsulta(consulta);
                    internacaoExistente.setValor(valor);
                    internacaoExistente.setDataInicio(dataEntrada);
                    internacaoExistente.setDataSaida(dataSaida);

                    if(internacaoExistente.salvar()){
                        JOptionPane.showMessageDialog(null, "Internação Atualizada! ", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "Internação não Atualizada! ", "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }else{

                if(internacaoExistente == null){
                    internacao = Internacao.criaInternacao(null, dataEntrada, valor, consulta);
                    if(internacao!=null){
                        JOptionPane.showMessageDialog(null, "Internação Cadastrada! ", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "Internação não Cadastrada! ", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    internacaoExistente.setConsulta(consulta);
                    internacaoExistente.setValor(valor);
                    internacaoExistente.setDataInicio(dataEntrada);

                    if(internacaoExistente.salvar()){
                        JOptionPane.showMessageDialog(null, "Internação Atualizada! ", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "Internação não Atualizada! ", "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
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
        checkLiberado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkLiberado.isSelected()){
                    painelDataSaida.setVisible(true);
                }
                else{
                    painelDataSaida.setVisible(false);
                }
            }
        });
    }

    public void setInternacaoExistente(Internacao internacaoExistente) {
        this.internacaoExistente = internacaoExistente;

        campoID.setText(String.valueOf(internacaoExistente.getConsulta().getIDConsulta()));
        campoValor.setText(String.valueOf(internacaoExistente.getValor()));
        campoDiaEntrada.setSelectedItem(internacaoExistente.getDataInicio().getDayOfMonth());
        campoMesEntrada.setSelectedItem(internacaoExistente.getDataInicio().getMonthValue());
        campoAnoEntrada.setSelectedItem(internacaoExistente.getDataInicio().getYear());

        if(internacaoExistente.getDataSaida() != null){
            campoDiaSaida.setSelectedItem(internacaoExistente.getDataSaida().getDayOfMonth());
            campoMesSaida.setSelectedItem(internacaoExistente.getDataSaida().getMonthValue());
            campoAnoSaida.setSelectedItem(internacaoExistente.getDataSaida().getYear());
            checkLiberado.isSelected();
        }


    }
}
