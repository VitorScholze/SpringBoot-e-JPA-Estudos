package com.vitor.jpa_estudos.jpasemspring.manytomany.produtotestes;

import java.util.Arrays;

import com.vitor.jpa_estudos.entidades.manytomany.Categoria;
import com.vitor.jpa_estudos.entidades.manytomany.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class relacionandoProdutoCategoria {
    public static void main(String[] args) {
    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();


        Categoria esporte = em.find(Categoria.class, 2L);
        Categoria calcado = em.find(Categoria.class, 3L);
        em.getTransaction().begin();
        Produto produto = new Produto();
        produto.setNome("Nike Dunk");
        produto.setCategorias(Arrays.asList(esporte, calcado));
        em.persist(produto);
        em.getTransaction().commit();
        em.close();
        emf.close();
    
    }
}
