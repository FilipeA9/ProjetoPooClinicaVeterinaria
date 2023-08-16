package com.petway.grafico;

import com.petway.entidades.Internacao;
import com.petway.entidades.ObsProntuario;
import com.petway.entidades.Prontuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Painel de cadastro e edição de observações de prontuário.
 * Permite cadastrar ou editar observações associadas a um prontuário.
 */
public class CadastroObsPanel extends JPanel {
    private JPanel paginaAtual = this;
    private JTextField campoID;
    private JTextArea campoObs;
    private JButton finalizarCadastro;
    private JButton fecharPagina;
    private JPanel painelPrincipal;
    private ObsProntuario obsExistente = null;

    /**
     * Construtor da classe.
     *
     * @param pagina_anterior Painel anterior no qual este painel será exibido como aba.
     */
    public CadastroObsPanel(JTabbedPane pagina_anterior) {

        setLayout(new BorderLayout());
        add(painelPrincipal, BorderLayout.CENTER);

        // Configuração do botão de finalizar cadastro
    finalizarCadastro.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Prontuario prontuario = Prontuario.pesquisaProntuario(Integer.parseInt(campoID.getText()));

            if(prontuario == null){
                JOptionPane.showMessageDialog(null, "Prontuário não encontrado! Insira um ID válido! ", "Erro ao pesquisar", JOptionPane.ERROR_MESSAGE);
            }else{
                    if(obsExistente == null){
                        ObsProntuario obs = ObsProntuario.criaObservacao(campoObs.getText(), prontuario);

                        if(obs!=null){
                            JOptionPane.showMessageDialog(null, "Observação cadastrata! ", "Exito ao cadastrar", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Observação não cadastrada ", "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
                        }

                    }else{
                        obsExistente.setObservacao(campoObs.getText());
                        obsExistente.setProntuario(prontuario);

                        if(obsExistente.salvar()){
                            JOptionPane.showMessageDialog(null, "Observação Atualizada! ", "Exito ao Atualizar", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Observação não Atualizada! ", "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
                        }

                    }

            }


        }
    });
        // Configuração do botão de fechar página
    fecharPagina.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pagina_anterior.removeTabAt(pagina_anterior.indexOfComponent(paginaAtual));
        }
    });
}
    /**
     * Define a observação existente a ser editada.
     *
     * @param obsExistente A observação de prontuário existente a ser editada.
     */
    public void setObsExistente(ObsProntuario obsExistente) {
        this.obsExistente = obsExistente;
        campoObs.setText(obsExistente.getObservacao());
        campoID.setText(String.valueOf(obsExistente.getIDObservacao()));
    }
}
