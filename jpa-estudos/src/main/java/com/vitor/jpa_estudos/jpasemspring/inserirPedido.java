package com.vitor.jpa_estudos.jpasemspring;

import com.vitor.jpa_estudos.entidades.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class inserirPedido {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Pedido pedido = new Pedido();
        pedido.setValor(150.0);
        em.persist(pedido);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
