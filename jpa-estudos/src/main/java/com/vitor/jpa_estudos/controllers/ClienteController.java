package com.vitor.jpa_estudos.controllers;


import com.vitor.jpa_estudos.repositories.PedidoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.jpa_estudos.entidades.Cliente;
import com.vitor.jpa_estudos.entidades.Pedido;
import com.vitor.jpa_estudos.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
 private final PedidoRepository pedidoRepository;
 private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping()
    public Cliente inserirCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping
    public String teste() {
        return "Funcionando!";
    }

    @GetMapping("/{id}")
    public Optional<Cliente> buscarCliente(@PathVariable Long id){
        return clienteRepository.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deletarCliente(@PathVariable Long id){
        try{
            Optional<Cliente> clienteDelete = clienteRepository.findById(id);
            clienteRepository.delete(clienteDelete.get());
        }catch(RuntimeException e){
            System.out.println(e);
        }
    }

    @GetMapping("listarPedidos/{id}")
    public List<Pedido> listarPedidos(@PathVariable Long id){
         Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
        return cliente.getPedidos();
    }
}
