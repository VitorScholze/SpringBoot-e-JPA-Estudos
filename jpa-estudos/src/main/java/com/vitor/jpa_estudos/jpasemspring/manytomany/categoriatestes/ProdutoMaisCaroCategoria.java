package com.vitor.jpa_estudos.jpasemspring.manytomany.categoriatestes;

import com.vitor.jpa_estudos.entidades.manytomany.Categoria;
import com.vitor.jpa_estudos.entidades.manytomany.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProdutoMaisCaroCategoria {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-estudos");
        EntityManager em = emf.createEntityManager();

        try {

            Categoria categoria = em.find(Categoria.class, 2L);

            if (categoria == null) {
                System.out.println("Categoria não encontrada!");
                return;
            }

            System.out.println("CATEGORIA: " + categoria.getId() +
                    " // " + categoria.getNome());

            Produto produtoMaisCaro = null;

            for (Produto p : categoria.getProdutos()) {

                if (produtoMaisCaro == null ||
                        p.getPreco() > produtoMaisCaro.getPreco()) {

                    produtoMaisCaro = p;
                }
            }

            if (produtoMaisCaro != null) {

                System.out.println("\nPRODUTO MAIS CARO:");

                System.out.println("Nome: " + produtoMaisCaro.getNome());

                System.out.println("Preço: " + produtoMaisCaro.getPreco());

            } else {

                System.out.println("A categoria não possui produtos.");
            }

        } finally {

            em.close();
            emf.close();
        }
    }
}