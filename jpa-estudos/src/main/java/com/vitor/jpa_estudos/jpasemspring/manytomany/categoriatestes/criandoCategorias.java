package com.vitor.jpa_estudos.jpasemspring.manytomany.categoriatestes;

import com.vitor.jpa_estudos.entidades.manytomany.Categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class criandoCategorias {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Categoria categoria = new Categoria();
        categoria.setNome("calcado");
        em.persist(categoria);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
