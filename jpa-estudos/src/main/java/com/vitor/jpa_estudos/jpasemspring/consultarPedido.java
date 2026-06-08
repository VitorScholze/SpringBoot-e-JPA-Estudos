package com.vitor.jpa_estudos.jpasemspring;

import com.vitor.jpa_estudos.entidades.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class consultarPedido {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        Pedido pedido = em.find(Pedido.class, 4L);

        if(pedido != null){
            System.out.println("ID: " + pedido.getId());
            System.out.println("VALOR: " + pedido.getValor());
        }else{
            System.out.println("Nenhum pedido encontrado!");
        }
      
        
        em.close();
        emf.close();
    }
}
