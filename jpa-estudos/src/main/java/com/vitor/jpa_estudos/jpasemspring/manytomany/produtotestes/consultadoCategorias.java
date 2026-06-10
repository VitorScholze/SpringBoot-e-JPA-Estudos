package com.vitor.jpa_estudos.jpasemspring.manytomany.produtotestes;

import com.vitor.jpa_estudos.entidades.manytomany.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class consultadoCategorias {
   public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
    EntityManager em = emf.createEntityManager();

    Produto produto1 = em.find(Produto.class, 2L);
    Produto produto2 = em.find(Produto.class, 3L);

    System.out.println("Categoria produto 1: " + produto1.getCategorias());
    System.out.println("Categoria produto 2: " + produto2.getCategorias());
   }

    

}
