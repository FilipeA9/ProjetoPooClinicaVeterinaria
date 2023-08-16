package com.petway.grafico;

import com.petway.operacoes.JPAUtil;
import com.petway.entidades.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe que representa a interface gráfica do sistema PetWay.
 * Gerencia as abas de pesquisa e cadastro para diferentes entidades.
 */

public class InterfaceGrafica extends JFrame {

    private JTabbedPane tabbedPane ;

    //JFrame principal = this;
    private JPanel panel1;
    private JPanel panelAdicionado;
    private JButton Pesquisar;
    private JButton Cadastrar;

    /**
     * Construtor da classe InterfaceGrafica.
     * Configura a interface gráfica e inicia a conexão com o banco de dados em uma Thread separada.
     */
    public InterfaceGrafica() {

    tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Aba Principal", panel1);
    getContentPane().add(tabbedPane, BorderLayout.CENTER);

    //setContentPane(panel1);
    setTitle("PetWay");
    setSize(900,800);
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // inicia a conexão com o Bnaco de dados em outra Thread
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(() -> {
        // Inicialize a conexão com o banco de dados e o mapeamento das entidades aqui
        JPAUtil.getEntityManager(); // Inicializa a conexão com o banco de dados usando o JPAUtil

        // Depois que a inicialização estiver completa, mostre a interface gráfica
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    });

        // Configuração dos botões de Cadastrar e Pesquisar
    Cadastrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Object selectedValue= EscolheEntidade();

            if(selectedValue == "Tutor"){
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new CadastroTutorPanel(tabbedPane);
                tabbedPane.addTab("Cadastro de Tutor", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);
            } else if (selectedValue == "Pet") {

                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new CadastroPetPanel(tabbedPane);
                tabbedPane.addTab("Cadastro de Pet", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            } else if (selectedValue == "Consulta") {

                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new CadastroConsultaPanel(tabbedPane);
                tabbedPane.addTab("Cadastro de Consulta", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);


            } else if (selectedValue == "Funcionario") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new CadastroFuncionarioPanel(tabbedPane);
                tabbedPane.addTab("Cadastro de Funcionario", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);
            } else if (selectedValue == "Exame") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new CadastroExamePanel(tabbedPane);
                tabbedPane.addTab("Cadastro de Exame", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);
            } else if (selectedValue == "Internacao") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new CadastroInternacaoPanel(tabbedPane);
                tabbedPane.addTab("Cadastro de Internação", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);
            } else if (selectedValue == "Prontuario") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new CadastroProntuarioPanel(tabbedPane);
                tabbedPane.addTab("Cadastro de Prontuario", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);
            } else if (selectedValue == "Observacao de Prontuario") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new CadastroObsPanel(tabbedPane);
                tabbedPane.addTab("Cadastro de Obs-Prontuário", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);
            }

        }
    });
    Pesquisar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Object selectedValue= EscolheEntidade();

            if(selectedValue == "Consulta"){
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new InfoPanel("ID da Consulta", Consulta.class, tabbedPane);
                tabbedPane.addTab("Informacoes Consulta", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            }else if(selectedValue == "Exame"){
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new InfoPanel("ID do Exame", Exame.class, tabbedPane);
                tabbedPane.addTab("Informações Exame", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            } else if (selectedValue == "Internacao") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new InfoPanel("ID da Internação", Internacao.class, tabbedPane);
                tabbedPane.addTab("Informacoes Internação", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            } else if (selectedValue == "Prontuario") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new InfoPanel("ID do Prontuario", Prontuario.class,tabbedPane);
                tabbedPane.addTab("Informacoes Prontuario", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            }else if (selectedValue == "Observacao de Prontuario"){
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new InfoPanel("ID do Prontuario", ObsProntuario.class,tabbedPane);
                tabbedPane.addTab("Informacoes ObsProntuario", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            } else if (selectedValue == "Pet") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new InfoPanel("ID do Pet", Pet.class, tabbedPane);
                tabbedPane.addTab("Informacoes Pet", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            } else if (selectedValue == "Tutor") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new InfoPanel("CPF", Tutor.class, tabbedPane);
                tabbedPane.addTab("Informacoes Tutor", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            } else if (selectedValue == "Funcionario") {
                int quantPaineis = tabbedPane.getTabCount();
                panelAdicionado = new InfoPanel("CPF do Funcionario", Funcionario.class, tabbedPane);
                tabbedPane.addTab("Informacoes Funcionario", panelAdicionado);
                tabbedPane.setSelectedIndex(quantPaineis);

            }

        }
    });
}

    /**
     * Exibe uma caixa de diálogo para o usuário escolher a entidade para pesquisa ou cadastro.
     *
     * @return A entidade selecionada pelo usuário.
     */

    private static Object EscolheEntidade(){
        Object[] Entidades= {"Consulta", "Exame", "Funcionario", "Internacao", "Observacao de Prontuario"
                , "Prontuario", "Pet","Tutor"};

        Object selectedValue = JOptionPane.showInputDialog(null,
                "Escolha o que deseja Pesquisar/Cadastrar ", "Selecionando",JOptionPane.INFORMATION_MESSAGE, null,
                Entidades,Entidades[0]);

        return selectedValue;
    }


}
