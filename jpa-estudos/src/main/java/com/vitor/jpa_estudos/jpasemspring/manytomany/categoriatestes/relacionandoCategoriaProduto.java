package com.vitor.jpa_estudos.jpasemspring.manytomany.categoriatestes;

import java.util.Arrays;

import com.vitor.jpa_estudos.entidades.manytomany.Categoria;
import com.vitor.jpa_estudos.entidades.manytomany.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class relacionandoCategoriaProduto {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        Produto produto1 = em.find(Produto.class, 2L);
        Produto produto2 = em.find(Produto.class, 3L);

        Categoria categoria = em.find(Categoria.class, 2);

        em.getTransaction().begin();

        produto1.setCategorias(Arrays.asList(categoria));
        produto2.setCategorias(Arrays.asList(categoria));
        
        em.getTransaction().commit();
        em.close();
        emf.close();

        
    }
}
