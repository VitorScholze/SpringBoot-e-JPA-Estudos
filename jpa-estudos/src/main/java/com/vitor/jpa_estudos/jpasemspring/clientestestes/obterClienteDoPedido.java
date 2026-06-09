package com.vitor.jpa_estudos.jpasemspring.clientestestes;

import com.vitor.jpa_estudos.entidades.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class obterClienteDoPedido {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        Pedido pedido = em.find(Pedido.class, 12L);
        if(pedido == null){
            System.out.println("Pedido nao encontrado!");
            em.close();
            emf.close();
            return;
        }
        
        System.out.println("Pedido ID: " + pedido.getId());
        System.out.println("Valor Pedido: "+ pedido.getValor());
        System.out.println(pedido.getCliente().getNome());

        em.close();
        emf.close();
    }
}
