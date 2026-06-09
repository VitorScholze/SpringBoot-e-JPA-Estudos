package com.vitor.jpa_estudos.jpasemspring.clientestestes;

import com.vitor.jpa_estudos.entidades.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class inserirCliente {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Cliente cliente = new Cliente();
        cliente.setNome("Helo");

        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
        emf.close();
        
    }

   
}
