package com.vitor.jpa_estudos.jpasemspring.clientestestes;

import com.vitor.jpa_estudos.entidades.Cliente;
import com.vitor.jpa_estudos.entidades.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class pedidosCliente {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        
        Cliente cliente = em.find(Cliente.class, 2L);
        if(cliente == null){
            System.out.println("Cliente não encontrado!");
            return;
        }

        for(Pedido p: cliente.getPedidos()){
            System.out.println("ID PEDIDO: " + p.getId());
            System.out.println("VALOR PEDIDO: " + p.getValor() + "\n");
        }

        em.close();
        emf.close();
        
    }
}

