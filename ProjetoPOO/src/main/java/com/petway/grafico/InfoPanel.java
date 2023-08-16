package com.petway.grafico;

import com.petway.entidades.*;
import com.petway.operacoes.Operacoes;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um painel de informações para exibir detalhes e interagir com uma entidade.
 * Pode ser usado para exibir informações de várias entidades e realizar ações como alteração e remoção.
 */
public class InfoPanel extends JPanel implements ModeloInfoPanel {
    JPanel painel_atual = this;
    Object obj = null;

    /**
     * Construtor da classe InfoPanel.
     * Configura a interface gráfica para exibir informações e interagir com uma entidade.
     *
     * @param nome_chave        O nome da chave (campo único) da entidade.
     * @param classe_operacoes  A classe de operações para a entidade.
     * @param painel_anterior   O painel anterior onde esta aba será adicionada.
     */
    public InfoPanel( String nome_chave, Class classe_operacoes , JTabbedPane painel_anterior  ) {

        setLayout(new BorderLayout());

        JLabel jlab = new JLabel("Digite o "+nome_chave);
        JTextField campoChave = new JTextField(20);
        campoChave.setActionCommand("Pesquisar");
        JPanel pesquisarPanel = new JPanel();
        pesquisarPanel.add(jlab); pesquisarPanel.add(campoChave) ; pesquisarPanel.add(pesquisarBotao);
        add(pesquisarPanel, BorderLayout.NORTH);


        // JTextArea infoTextArea = new JTextArea();
        //infoTextArea.setEditable(false);
        // add(infoTextArea, BorderLayout.CENTER);
        JList<Object> lista;
        DefaultListModel<Object> listModel;
        listModel = new DefaultListModel<>();
        lista = new JList<>(listModel);
        add(new JScrollPane(lista), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();

        buttonPanel.add(alterarButton);
        buttonPanel.add(removerButton);
        buttonPanel.add(continuarButton);

        add(buttonPanel, BorderLayout.SOUTH);

        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para alterar os dados da entidade

                if(obj == null){
                    JOptionPane.showMessageDialog(null, "Não foi possível encontrar um registro para essa chave! Insira um valor válido e clique em Pesquisar", "Erro ao pesquisar", JOptionPane.ERROR_MESSAGE);
                } else if (obj instanceof List<?>) {
                    JOptionPane.showMessageDialog(null, "Selecione uma das opções", "Erro ao processar seleção", JOptionPane.ERROR_MESSAGE);
                } else{

                    if(classe_operacoes == Consulta.class){

                        int quantPaineis = painel_anterior.getTabCount();
                        JPanel panelAdicionado = new CadastroConsultaPanel(painel_anterior);
                        ((CadastroConsultaPanel) panelAdicionado).setConsultaExistente((Consulta) obj);
                        painel_anterior.addTab("Cadastro de Consulta", panelAdicionado);
                        painel_anterior.setSelectedIndex(quantPaineis);

                    } else if (classe_operacoes == Exame.class) {

                        int quantPaineis = painel_anterior.getTabCount();
                        JPanel panelAdicionado = new CadastroExamePanel(painel_anterior);
                        ((CadastroExamePanel) panelAdicionado).setExameExistente((Exame) obj);
                        painel_anterior.addTab("Cadastro de Exame", panelAdicionado);
                        painel_anterior.setSelectedIndex(quantPaineis);

                    } else if (classe_operacoes == Internacao.class) {

                        int quantPaineis = painel_anterior.getTabCount();
                        JPanel panelAdicionado = new CadastroInternacaoPanel(painel_anterior);
                        ((CadastroInternacaoPanel) panelAdicionado).setInternacaoExistente((Internacao) obj);
                        painel_anterior.addTab("Cadastro de Internação", panelAdicionado);
                        painel_anterior.setSelectedIndex(quantPaineis);

                    } else if (classe_operacoes == Prontuario.class) {

                        int quantPaineis = painel_anterior.getTabCount();
                        JPanel panelAdicionado = new CadastroProntuarioPanel(painel_anterior);
                        ((CadastroProntuarioPanel) panelAdicionado).setProntuarioExistente((Prontuario) obj);
                        painel_anterior.addTab("Cadastro de Prontuário", panelAdicionado);
                        painel_anterior.setSelectedIndex(quantPaineis);

                    } else if (classe_operacoes == ObsProntuario.class) {

                        int quantPaineis = painel_anterior.getTabCount();
                        JPanel panelAdicionado = new CadastroObsPanel(painel_anterior);
                        ((CadastroObsPanel) panelAdicionado).setObsExistente((ObsProntuario) obj);
                        painel_anterior.addTab("Cadastro de Obs-Prontuário", panelAdicionado);
                        painel_anterior.setSelectedIndex(quantPaineis);

                    } else if (classe_operacoes == Pet.class) {

                        int quantPaineis = painel_anterior.getTabCount();
                        JPanel panelAdicionado = new CadastroPetPanel(painel_anterior);
                        ((CadastroPetPanel) panelAdicionado).setPetExistente((Pet) obj);
                        painel_anterior.addTab("Cadastro de Pet", panelAdicionado);
                        painel_anterior.setSelectedIndex(quantPaineis);

                    } else if (classe_operacoes == Tutor.class) {

                        int quantPaineis = painel_anterior.getTabCount();
                        JPanel panelAdicionado = new CadastroTutorPanel(painel_anterior);
                        ((CadastroTutorPanel) panelAdicionado).setTutorExistente((Tutor) obj);
                        painel_anterior.addTab("Cadastro de Tutor", panelAdicionado);
                        painel_anterior.setSelectedIndex(quantPaineis);

                    } else if (classe_operacoes == Funcionario.class) {

                        int quantPaineis = painel_anterior.getTabCount();
                        JPanel panelAdicionado = new CadastroFuncionarioPanel(painel_anterior);
                        ((CadastroFuncionarioPanel) panelAdicionado).setFuncionarioExistente((Funcionario) obj);
                        painel_anterior.addTab("Cadastro de Funcionário", panelAdicionado);
                        painel_anterior.setSelectedIndex(quantPaineis);

                    }

                }

            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para remover a entidade
                Operacoes op = new Operacoes();
                if(op.remove(campoChave.getText(), classe_operacoes)){
                    JOptionPane.showMessageDialog(pesquisarBotao," Removido do Banco de Dados!","Exito ao deletar",JOptionPane.WARNING_MESSAGE );
                }
                else{
                    JOptionPane.showMessageDialog(pesquisarBotao,"Não foi possível deletar o objeto","Erro ao deletar ",JOptionPane.ERROR_MESSAGE );
                }
            }
        });

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para continuar
                //frame_original.setContentPane(painel_anterior);
                painel_anterior.removeTabAt(painel_anterior.indexOfComponent(painel_atual));
            }
        });

        pesquisarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Operacoes op = new Operacoes();
                if(!listModel.isEmpty()){
                    listModel.removeAllElements();
                }

                if(classe_operacoes == Tutor.class || classe_operacoes == Veterinario.class || classe_operacoes == Recepcionista.class
                        || classe_operacoes == AuxiliarLimpeza.class || classe_operacoes == AuxiliarVeterinario.class || classe_operacoes == Gerente.class
                        || classe_operacoes == Funcionario.class ){

                    obj = op.pesquisaListaCPF(classe_operacoes, campoChave.getText());

                }else{

                    if(classe_operacoes == Pet.class){
                        obj = op.pesquisaListaID(classe_operacoes, Integer.parseInt(campoChave.getText()), "IDPet");
                    } else if (classe_operacoes == Consulta.class) {
                        obj = op.pesquisaListaID(classe_operacoes, Integer.parseInt(campoChave.getText()), "IDConsulta");
                    } else if (classe_operacoes == Exame.class) {
                        obj = op.pesquisaListaID(classe_operacoes, Integer.parseInt(campoChave.getText()), "IDExame");
                    } else if (classe_operacoes == Internacao.class) {
                        obj = op.pesquisaListaID(classe_operacoes, Integer.parseInt(campoChave.getText()), "IDInternacao");
                    } else if (classe_operacoes == Prontuario.class) {
                        obj = op.pesquisaListaID(classe_operacoes, Integer.parseInt(campoChave.getText()), "IDProntuario");
                    } else if (classe_operacoes == ObsProntuario.class) {
                        obj = op.pesquisaListaID(classe_operacoes, Integer.parseInt(campoChave.getText()), "prontuario.IDProntuario");
                    }

                }


                if(obj!=null){
                    //infoTextArea.setText(obj.toString());
                    for(Object object : ((List) obj) ){
                        listModel.addElement(object);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(pesquisarBotao,"Objeto não encontrado!","Erro ao Pesquisar",JOptionPane.ERROR_MESSAGE );
                }

            }
        });

        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Object selectedObjeto = lista.getSelectedValue();
                    if (selectedObjeto != null) {
                        obj = selectedObjeto;
                    }
                }
            }
        });

        lista.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                list.setFixedCellHeight(70);
                if (value != null) {
                    // Customize how the value is displayed
                    if (value instanceof Consulta) {
                        Consulta consulta = (Consulta) value;
                        setText("<html> " + consulta + "<br>Pet: " + consulta.getPet() + "<br>Tutor:"+consulta.getPet().getTutor()+
                                "<br>Veterinário: " + consulta.getVeterinario() + "</html>");
                    } else if (value instanceof Exame) {
                        Exame exame = (Exame) value;
                        setText("<html> " + exame + "<br>Consulta " + exame.getConsulta() + "<br>Pet:"+exame.getConsulta().getPet()+
                                "<br>Tutor: "+exame.getConsulta().getPet().getTutor()+"<br>Veterinário: " + exame.getConsulta().getVeterinario()+ "</html>");
                    } else if (value instanceof Internacao) {
                        Internacao internacao = (Internacao) value;
                        setText("<html> " + internacao + "<br>Consulta " + internacao.getConsulta() + "<br>Pet:"+internacao.getConsulta().getPet()+
                                "<br>Tutor: "+internacao.getConsulta().getPet().getTutor()+"<br>Veterinário: " + internacao.getConsulta().getVeterinario()+ "</html>");

                    } else if (value instanceof  Prontuario) {
                        Prontuario prontuario = (Prontuario) value;
                        setText("<html> " + prontuario + "<br>Internação " + prontuario.getInternacao() + "<br>Pet:"+prontuario.getInternacao().getConsulta().getPet()+
                                "<br>Tutor: "+prontuario.getInternacao().getConsulta().getPet().getTutor()+"<br>Veterinário: " +
                                prontuario.getInternacao().getConsulta().getVeterinario()+ "</html>");

                    } else if (value instanceof ObsProntuario) {
                        ObsProntuario obs = (ObsProntuario) value;
                        setText("<html> " + obs + "<br>Internação " + obs.getProntuario().getInternacao() + "<br>Pet:"+obs.getProntuario().getInternacao().getConsulta().getPet()+
                                "<br>Tutor: "+obs.getProntuario().getInternacao().getConsulta().getPet().getTutor()+"<br>Veterinário: " +
                                obs.getProntuario().getInternacao().getConsulta().getVeterinario()+ "</html>");

                    } else if (value instanceof Pet) {
                        Pet pet = (Pet) value;
                        setText("<html>Pet: " +pet+"<br>Tutor:"+pet.getTutor()+ "</html>");

                    } else if (value instanceof Tutor) {
                        Tutor tutor = (Tutor) value;
                        setText("<html>Tutor: " +tutor+ "</html>");

                    } else if (value instanceof Veterinario) {
                        Veterinario vet = (Veterinario) value;
                        setText("<html>Veterinário: " +vet+ "</html>");

                    } else if (value instanceof Gerente) {
                        Gerente gerente = (Gerente) value;
                        setText("<html>Gerente: " +gerente+ "</html>");

                    } else if (value instanceof AuxiliarVeterinario) {
                        AuxiliarVeterinario aux = (AuxiliarVeterinario) value;
                        setText("<html>Auxiliar Veterinário: " +aux+ "</html>");

                    } else if (value instanceof Recepcionista) {
                        Recepcionista recp = (Recepcionista) value;
                        setText("<html> Recepcionista: " +recp+ "</html>");

                    } else if (value instanceof AuxiliarLimpeza) {
                        AuxiliarLimpeza aux = (AuxiliarLimpeza) value;
                        setText("<html>Auxiliar de Limpeza: " +aux+ "</html>");
                    }
                }

                return c;
            }
        });
    }


}

