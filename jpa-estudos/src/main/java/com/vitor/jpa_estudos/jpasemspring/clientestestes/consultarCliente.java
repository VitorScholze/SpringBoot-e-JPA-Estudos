package com.vitor.jpa_estudos.jpasemspring.clientestestes;

import com.vitor.jpa_estudos.entidades.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class consultarCliente {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
    EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();
        
        Cliente cliente = em.find(Cliente.class, 1L);
        if(cliente !=null){
            System.out.println(cliente.getNome());
        }else{
            System.out.println("cliente nao encontrado!");
        }
       

        em.close();
        emf.close();
    }
      

      
}
