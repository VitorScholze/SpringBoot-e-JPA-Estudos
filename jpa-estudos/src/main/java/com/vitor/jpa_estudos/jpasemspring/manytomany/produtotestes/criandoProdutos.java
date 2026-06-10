package com.vitor.jpa_estudos.jpasemspring.manytomany.produtotestes;

import com.vitor.jpa_estudos.entidades.manytomany.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class criandoProdutos {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();
    
        em.getTransaction().begin();
        Produto produto = new Produto();
        produto.setNome("Nike Zoom");
        produto.setPreco(1500.00);
        em.persist(produto);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
