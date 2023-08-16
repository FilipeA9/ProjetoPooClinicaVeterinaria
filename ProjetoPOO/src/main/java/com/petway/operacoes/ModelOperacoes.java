package com.petway.operacoes;

//estabelece as operações básicas com o banco de dados

public interface ModelOperacoes {
    boolean salvar(Object objeto);
    boolean inserir(Object objeto);
    boolean remove(Object chave, Class classe);
    Object pesquisa(Object chave, Class classe);
}
