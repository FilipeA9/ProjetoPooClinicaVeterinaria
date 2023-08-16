package com.petway.grafico;

import com.petway.entidades.*;
import com.petway.operacoes.Operacoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class CadastroFuncionarioPanel extends JPanel {

    private JPanel paginaAtual = this;
    private JTextField campoNome;
    private JLabel txtNome;
    private JTextField campoCPF;
    private JLabel txtCPF;
    private JCheckBox veterinarioCheck;
    private JTextField campoCRMV;
    private JComboBox campoDia;
    private JComboBox campoAno;
    private JComboBox campoMes;
    private JLabel txtData;
    private JLabel txtDia;
    private JLabel txtMes;
    private JLabel txtAno;
    private JComboBox campoDiaAdm;
    private JComboBox campoAnoAdm;
    private JComboBox campoMesAdm;
    private JTextField campoRemuneracao;
    private JButton finalizarCadastro;
    private JButton fecharPagina;
    private JPanel painelPrincipal;
    private JLabel txtCRMV;
    private JComboBox campoCargo;
    private JLabel txtCargo;

    private Funcionario funcionarioExistente = null;

    public CadastroFuncionarioPanel(JTabbedPane pagina_anterior) {

    setLayout(new BorderLayout());
    add(painelPrincipal, BorderLayout.CENTER);

    campoCRMV.setVisible(false);
    txtCRMV.setVisible(false);


        // Definindo o vetor de dias
        DefaultComboBoxModel<Integer> comboBoxModelDiaNasc = new DefaultComboBoxModel<Integer>();
        DefaultComboBoxModel<Integer> comboBoxModelDiaAdm = new DefaultComboBoxModel<Integer>();
        int[] dia = new int[31];
        for (int i = 0; i < 31; i++) {
            dia[i] = i + 1;
            comboBoxModelDiaNasc.addElement(dia[i]);
            comboBoxModelDiaAdm.addElement(dia[i]);
        }
        campoDia.setModel(comboBoxModelDiaNasc);
        campoDiaAdm.setModel(comboBoxModelDiaAdm);

        // Definindo o vetor de meses
        DefaultComboBoxModel<Integer> comboBoxModelMesNasc = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Integer> comboBoxModelMesAdm = new DefaultComboBoxModel<Integer>();
        int[] mes = new int[12];
        for (int i = 0; i < 12; i++) {
            mes[i] = i + 1;
            comboBoxModelMesNasc.addElement(mes[i]);
            comboBoxModelMesAdm.addElement(mes[i]);
        }
        campoMes.setModel(comboBoxModelMesNasc);
        campoMesAdm.setModel(comboBoxModelMesAdm);

        // Definindo o vetor de anos
        DefaultComboBoxModel<Integer> comboBoxModelAnoNasc = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Integer> comboBoxModelAnoAdm = new DefaultComboBoxModel<Integer>();
        int[] ano = new int[54]; // De 1970 a 2023
        for (int i = 0; i < 54; i++) {
            ano[i] = 1970 + i;
            comboBoxModelAnoNasc.addElement(ano[i]);
            comboBoxModelAnoAdm.addElement(ano[i]);
        }
        campoAno.setModel(comboBoxModelAnoNasc);
        campoAnoAdm.setModel(comboBoxModelAnoAdm);

        DefaultComboBoxModel<String> comboBoxModelCargo = new DefaultComboBoxModel<>();
        String[] cargos ={"Veterinário","Recepcionista", "Auxiliar Veterinário", "Auxiliar de Limpeza", "Gerente"};
        comboBoxModelCargo.addAll(List.of(cargos));
        campoCargo.setModel(comboBoxModelCargo);
        String opcaoPreSelecionada = "Auxiliar Veterinário";
        campoCargo.setSelectedItem(opcaoPreSelecionada);




    finalizarCadastro.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LocalDate dataNascimento = LocalDate.of((int) campoAno.getSelectedItem(), (int) campoMes.getSelectedItem(), (int) campoDia.getSelectedItem());
            LocalDate dataAdm = LocalDate.of((int) campoAnoAdm.getSelectedItem(), (int) campoMesAdm.getSelectedItem(), (int) campoDiaAdm.getSelectedItem());
            String nome = campoNome.getText();
            String cpf = campoCPF.getText();
            double remuneracao = Double.parseDouble(campoRemuneracao.getText());

            if(funcionarioExistente == null){
                registraFuncionario();

            }else{

                if(verificaMudancaCargo(funcionarioExistente, campoCargo.getSelectedItem())){

                    int escolha = JOptionPane.showConfirmDialog(null, "Cuidado, você está alterando o cargo desse funcionário, isso fará com que o registro anterior do funcionário seja apagado e um novo será criado. Deseja continuar?", "Aviso alteração de cargo", JOptionPane.YES_NO_OPTION);

                    if (escolha == JOptionPane.YES_OPTION) {
                        Operacoes op = new Operacoes();
                        op.remove(funcionarioExistente.getCPF(), Funcionario.class);
                        registraFuncionario();

                    }

                }else{ // está atualizando as informações de um funcionário

                    if(campoCargo.getSelectedItem() == "Veterinário"){
                        funcionarioExistente.setNome(nome);
                        funcionarioExistente.setCPF(cpf);
                        funcionarioExistente.setData_Entrada(dataAdm);
                        funcionarioExistente.setData_Nasc(dataNascimento);
                        funcionarioExistente.setRemuneracao(remuneracao);

                        ((Veterinario)funcionarioExistente).setCRMV(campoCRMV.getText());


                    } else{
                        funcionarioExistente.setNome(nome);
                        funcionarioExistente.setCPF(cpf);
                        funcionarioExistente.setData_Entrada(dataAdm);
                        funcionarioExistente.setData_Nasc(dataNascimento);
                        funcionarioExistente.setRemuneracao(remuneracao);

                    }


                    if(funcionarioExistente.salvar()){
                        JOptionPane.showMessageDialog(null, "Funcionario Atualizado! ", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                    } else{
                        JOptionPane.showMessageDialog(null, "Falha ao atualizar funcionário! Confira as informações preenchidas", "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
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

        campoCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(campoCargo.getSelectedItem() == "Veterinário"){
                    campoCRMV.setVisible(true);
                    txtCRMV.setVisible(true);
                }
                else{
                    campoCRMV.setVisible(false);
                    txtCRMV.setVisible(false);
                }
            }
        });
    }

    public void setFuncionarioExistente(Funcionario funcionarioExistente ) {
        this.funcionarioExistente = funcionarioExistente;

        campoNome.setText(funcionarioExistente.getNome());
        campoCPF.setText(funcionarioExistente.getCPF());
        campoRemuneracao.setText(String.valueOf(funcionarioExistente.getRemuneracao()));
        campoDia.setSelectedItem(funcionarioExistente.getData_Nasc().getDayOfMonth());
        campoMes.setSelectedItem(funcionarioExistente.getData_Nasc().getMonthValue());
        campoAno.setSelectedItem(funcionarioExistente.getData_Nasc().getYear());
        campoDiaAdm.setSelectedItem(funcionarioExistente.getData_Entrada().getDayOfMonth());
        campoMesAdm.setSelectedItem(funcionarioExistente.getData_Entrada().getMonthValue());
        campoAnoAdm.setSelectedItem(funcionarioExistente.getData_Entrada().getYear());

        if(funcionarioExistente instanceof Veterinario){
            campoCargo.setSelectedItem("Veterinário");
            campoCRMV.setText(((Veterinario) funcionarioExistente).getCRMV());
        } else if (funcionarioExistente instanceof AuxiliarLimpeza) {
            campoCargo.setSelectedItem("Auxiliar de Limpeza");
        } else if (funcionarioExistente instanceof AuxiliarVeterinario) {
            campoCargo.setSelectedItem("Auxiliar Veterinário");
        } else if (funcionarioExistente instanceof Recepcionista) {
            campoCargo.setSelectedItem("Recepcionista");
        } else if (funcionarioExistente instanceof Gerente) {
            campoCargo.setSelectedItem("Gerente");
        }

    }

    public boolean verificaMudancaCargo(Funcionario funcionario, Object cargo ){
        if(funcionario instanceof Veterinario && cargo!="Veterinário"){
            return true;
        } else if (funcionario instanceof AuxiliarVeterinario && cargo!="Auxiliar Veterinário") {
            return true;
        } else if (funcionario instanceof AuxiliarLimpeza && cargo!= "Auxiliar de Limpeza") {
            return true;
        } else if(funcionario instanceof Recepcionista && cargo!="Recepcionista"){
            return true;
        } else return funcionario instanceof Gerente && cargo != "Gerente";
    }

    public void registraFuncionario(){
        LocalDate dataNascimento = LocalDate.of((int) campoAno.getSelectedItem(), (int) campoMes.getSelectedItem(), (int) campoDia.getSelectedItem());
        LocalDate dataAdm = LocalDate.of((int) campoAnoAdm.getSelectedItem(), (int) campoMesAdm.getSelectedItem(), (int) campoDiaAdm.getSelectedItem());
        String nome = campoNome.getText();
        String cpf = campoCPF.getText();
        double remuneracao = Double.parseDouble(campoRemuneracao.getText());

        Funcionario funcionario = null;
        if(campoCargo.getSelectedItem() == "Veterinário"){
            Veterinario vet = new Veterinario();
            vet.criaFuncionario(nome, cpf, remuneracao, dataNascimento, dataAdm);
            vet.setCRMV(campoCRMV.getText());
            vet.salvar();
            funcionario = vet;
        } else if (campoCargo.getSelectedItem() == "Recepcionista") {
            Recepcionista recepcionista = new Recepcionista();
            recepcionista.criaFuncionario(nome, cpf, remuneracao, dataNascimento, dataAdm);
            funcionario = recepcionista;
        } else if (campoCargo.getSelectedItem() == "Auxiliar Veterinário") {
            AuxiliarVeterinario aux = new AuxiliarVeterinario();
            aux.criaFuncionario(nome, cpf, remuneracao, dataNascimento, dataAdm);
            funcionario = aux;

        } else if (campoCargo.getSelectedItem() == "Auxiliar de Limpeza") {
            AuxiliarLimpeza aux = new AuxiliarLimpeza();
            aux.criaFuncionario(nome, cpf, remuneracao, dataNascimento, dataAdm);
            funcionario = aux;

        } else if (campoCargo.getSelectedItem() == "Gerente") {
            Gerente gerente = new Gerente();
            gerente.criaFuncionario(nome, cpf, remuneracao, dataNascimento, dataAdm);
            funcionario = gerente;
        }


        if(funcionario!=null){
            JOptionPane.showMessageDialog(null, "Funcionario Cadastrado! ", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar funcionário! Confira as informações preenchidas", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
        }
    }


}
