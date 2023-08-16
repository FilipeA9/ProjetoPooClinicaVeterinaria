package com.petway.operacoes;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Destinada a estabelecer as operações básicas com o Banco de dados, inserir dados, recuperar, alterar e remover.
 * @author Filipe Augusto.
 * */

public class Operacoes implements ModelOperacoes {
    private EntityManager entityManager = JPAUtil.getEntityManager();

    /**
     * Salva ou atualiza um objeto no banco de dados.
     *
     * @param objeto O objeto a ser salvo ou atualizado.
     * @return true se o objeto foi salvo ou atualizado com sucesso.
     * @throws OperacoesException se ocorrer um erro ao salvar ou atualizar o objeto.
     */

    @Override
    public boolean salvar(Object objeto) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(objeto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            /*if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }*/
            throw new OperacoesException("Erro ao salvar objeto", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Insere um novo objeto no banco de dados.
     *
     * @param objeto O objeto a ser inserido.
     * @return true se o objeto foi inserido com sucesso.
     * @throws OperacoesException se a chave primária já estiver cadastrada no banco de dados.
     */


    @Override
    public boolean inserir(Object objeto){

        try{

            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        }catch (PersistenceException e){
            throw new OperacoesException("A chave primária (CPF/ID) já está cadastrada no Banco de Dados", e);
        }


    }

    /**
     * Remove um objeto do banco de dados.
     *
     * @param chave  A chave primária do objeto a ser removido.
     * @param classe A classe do objeto a ser removido.
     * @return true se o objeto foi removido com sucesso.
     * @throws OperacoesException se ocorrer um erro ao remover o objeto.
     */



    @Override
    public boolean remove(Object chave, Class classe){



        try {
            Object objeto = entityManager.find(classe, chave);
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();

            return true;
        }catch (NoResultException e){
            throw new OperacoesException("Não foi possivel remover o objeto do Banco", e);
        } finally {
            entityManager.close();
        }

    }

    /**
     * Pesquisa um objeto no banco de dados pelo valor da sua chave primária.
     *
     * @param chave  O valor da chave primária do objeto.
     * @param classe A classe do objeto a ser pesquisado.
     * @return O objeto pesquisado, ou null se não for encontrado.
     * @throws OperacoesException se ocorrer um erro ao pesquisar o objeto.
     */


    @Override
    public Object pesquisa(Object chave, Class classe) {

        try {
            Object objeto = entityManager.find(classe, chave);
            return objeto;
        } catch (NoResultException e) {
            throw new OperacoesException("Não foi possível pesquisar o objeto", e);
            //return null;
        } finally {
            entityManager.close();
        }

    }

    /**
     * Retorna uma lista de todos os objetos de uma determinada classe.
     *
     * @param classe A classe dos objetos a serem pesquisados.
     * @return Uma lista contendo todos os objetos da classe.
     * @throws OperacoesException se ocorrer um erro ao pesquisar os objetos.
     */
    public <T> List<T> pesquisaLista(Class<T> classe) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + classe.getName() + " e", classe);
            List<T> listaEntidades = query.getResultList();
            return listaEntidades;
        } catch (NoResultException e) {
            throw new OperacoesException("Não foi possível pesquisar o objeto", e);
            //return new ArrayList<>(); // Retorna uma lista vazia se nenhum resultado for encontrado
        } finally {
            em.close();
        }
    }

    /**
     * Retorna uma lista de objetos de uma determinada classe filtrados pelo CPF.
     *
     * @param classe A classe dos objetos a serem pesquisados.
     * @param CPF O CPF a ser utilizado como filtro.
     * @return Uma lista contendo os objetos da classe com o CPF especificado.
     * @throws OperacoesException se ocorrer um erro ao pesquisar os objetos.
     */

    public <T> List<T> pesquisaListaCPF(Class<T> classe, String CPF) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + classe.getName() + " e WHERE e.CPF = :CPF", classe);
            query.setParameter("CPF", CPF);
            List<T> listaEntidades = query.getResultList();
            return listaEntidades;
        } catch (NoResultException e) {
            throw new OperacoesException("Não foi possível pesquisar o objeto", e);
        } finally {
            em.close();
        }
    }

    /**
     * Retorna uma lista de objetos de uma determinada classe filtrados pelo ID.
     *
     * @param classe A classe dos objetos a serem pesquisados.
     * @param ID O ID a ser utilizado como filtro.
     * @param nomeColuna O nome da coluna que representa o ID na tabela.
     * @return Uma lista contendo os objetos da classe com o ID especificado.
     * @throws OperacoesException se ocorrer um erro ao pesquisar os objetos.
     */

    public <T> List<T> pesquisaListaID(Class<T> classe, int ID, String nomeColuna) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<T> query = em.createQuery("SELECT p FROM " + classe.getName() + " p WHERE p."+nomeColuna+" = :ID", classe);
            query.setParameter("ID", ID);
            List<T> listaEntidades = query.getResultList();
            return listaEntidades;
        } catch (NoResultException e) {
            throw new OperacoesException("Não foi possível pesquisar o objeto", e);
        } finally {
            em.close();
        }
    }


}
