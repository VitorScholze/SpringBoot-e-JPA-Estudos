package com.vitor.jpa_estudos.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.jpa_estudos.entidades.manytomany.Categoria;

@Repository
public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {

}
