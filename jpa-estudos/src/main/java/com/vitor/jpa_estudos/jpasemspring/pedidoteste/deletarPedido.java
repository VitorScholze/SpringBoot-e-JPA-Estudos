package com.vitor.jpa_estudos.jpasemspring.pedidoteste;

import com.vitor.jpa_estudos.entidades.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class deletarPedido {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Pedido pedido = em.find(Pedido.class, 3L);
        em.remove(pedido);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
