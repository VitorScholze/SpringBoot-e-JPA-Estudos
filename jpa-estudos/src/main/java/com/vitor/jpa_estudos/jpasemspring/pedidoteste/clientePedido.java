package com.vitor.jpa_estudos.jpasemspring.pedidoteste;

import com.vitor.jpa_estudos.entidades.Cliente;
import com.vitor.jpa_estudos.entidades.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class clientePedido {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();

        Cliente cliente = em.find(Cliente.class, 8L);
        if(cliente == null){
            System.out.println("Cliente nao encontrado!");
            em.close();
            emf.close();
            return;
        }

        Pedido pedido = new Pedido();
        pedido.setValor(1500.00);
        pedido.setCliente(cliente);
        em.persist(pedido);
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        
    }
}
