package com.petway.grafico;

import com.petway.entidades.Tutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroTutorPanel extends JPanel{
    private JPanel painel_atual = this;
    private JTextField entradaNome;
    private JTextField entradaCPF;
    private JTextField entradaEndereco;
    private JButton FinalizarCadastro;
    private JButton Fechar;
    private JPanel PainelPrincipal;
    private Tutor tutorExistente = null;

    public CadastroTutorPanel(JTabbedPane painel_anterior){

        setLayout(new BorderLayout());

        add(PainelPrincipal, BorderLayout.CENTER);

        entradaNome.setActionCommand("Entrada de Nome");
        entradaCPF.setActionCommand("Entrada de CPF");
        entradaEndereco.setActionCommand("Entrada de Endereco");


        FinalizarCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = entradaNome.getText();
                String cpf = entradaCPF.getText();
                String endereco = entradaEndereco.getText();

                if(tutorExistente == null){

                    Tutor tutor = Tutor.criaTutor(cpf, nome, endereco);

                    if(tutor!=null){
                        JOptionPane.showMessageDialog(FinalizarCadastro, "Tutor Cadastrado! ", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(FinalizarCadastro, "Erro no sistema ou Tutor já cadastrado ", "Erro ao Cadastrar", JOptionPane.ERROR_MESSAGE);
                    }

                }else{
                    tutorExistente.setNome(nome);
                    tutorExistente.setCPF(cpf);
                    tutorExistente.setEndr(endereco);

                    if(tutorExistente.salvar()){
                        JOptionPane.showMessageDialog(FinalizarCadastro, "Tutor Atualizado! ", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(FinalizarCadastro, "Erro ao Atualizar! confira os dados inseridos ", "Erro ao Atualizar!", JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });

        Fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel_anterior.removeTabAt(painel_anterior.indexOfComponent(painel_atual));
            }
        });
    }

    public void setTutorExistente(Tutor tutorExistente) {
        this.tutorExistente = tutorExistente;

        entradaCPF.setText(tutorExistente.getCPF());
        entradaNome.setText(tutorExistente.getNome());
        entradaEndereco.setText(tutorExistente.getEndr());
    }
}
