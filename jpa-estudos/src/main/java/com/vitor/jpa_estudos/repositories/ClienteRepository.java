package com.vitor.jpa_estudos.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.jpa_estudos.entidades.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}