package com.vitor.jpa_estudos.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.jpa_estudos.entidades.manytomany.Produto;
@Repository
public interface ProdutoRepository
        extends JpaRepository<Produto, Long> {

}
