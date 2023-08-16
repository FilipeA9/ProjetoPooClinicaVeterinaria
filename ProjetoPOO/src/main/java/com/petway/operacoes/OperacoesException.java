package com.petway.operacoes;

import javax.swing.*;

public class OperacoesException extends RuntimeException {
    public OperacoesException(String mensagem) {
        super(mensagem);
        JOptionPane.showMessageDialog(null,mensagem , "ERRO!", JOptionPane.ERROR_MESSAGE);
    }

    public OperacoesException(String mensagem, Throwable causa) {
        super(mensagem, causa);
        JOptionPane.showMessageDialog(null,mensagem , "ERRO!", JOptionPane.ERROR_MESSAGE);
    }
}

