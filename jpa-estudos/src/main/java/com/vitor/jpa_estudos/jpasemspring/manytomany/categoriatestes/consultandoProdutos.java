package com.vitor.jpa_estudos.jpasemspring.manytomany.categoriatestes;

import com.vitor.jpa_estudos.entidades.manytomany.Categoria;
import com.vitor.jpa_estudos.entidades.manytomany.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class consultandoProdutos {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        Categoria categoria = em.find(Categoria.class, 2L);
        if(categoria == null){
            System.out.println("Categoria nao encontrada!");
            em.close();
            emf.close();
            return;
        }
        
        System.out.println("PRODUTOS DA CATEGORIA: " + categoria.getNome());

        for(Produto p: categoria.getProdutos()){
            System.out.println("PRODUTO:" + p.getNome());
            System.out.println("VALOR: " + p.getPreco() + "\n");

        }

        em.close();
        emf.close();
    }
}
