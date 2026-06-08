package com.vitor.jpa_estudos.controllers;

import com.vitor.jpa_estudos.repositories.PedidoRepository;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.jpa_estudos.entidades.Pedido;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
private final PedidoRepository pedidoRepository;

    PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public Pedido inserir(@RequestBody Pedido pedido){
        return pedidoRepository.save(pedido);
    }   


    @GetMapping("/buscar/{id}")
    public Optional<Pedido> consultarPedido(@PathVariable Long id){
        return pedidoRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletarPedido(@PathVariable Long id){
        try{
            Optional<Pedido> pedido = pedidoRepository.findById(id);
            pedidoRepository.delete(pedido.get());
        }catch(RuntimeException e){
            System.out.println(e);
        }
        
    }
}
