package com.vitor.jpa_estudos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.jpa_estudos.entidades.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
