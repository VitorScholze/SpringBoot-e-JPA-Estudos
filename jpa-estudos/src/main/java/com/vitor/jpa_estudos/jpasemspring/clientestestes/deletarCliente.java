package com.vitor.jpa_estudos.jpasemspring.clientestestes;

import com.vitor.jpa_estudos.entidades.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class deletarCliente {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Cliente cliente = em .find(Cliente.class, 3L);
        em.remove(cliente);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
