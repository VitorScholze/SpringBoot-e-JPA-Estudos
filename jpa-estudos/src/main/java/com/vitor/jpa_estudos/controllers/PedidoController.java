package com.vitor.jpa_estudos.controllers;

import com.vitor.jpa_estudos.repositories.ClienteRepository;
import com.vitor.jpa_estudos.repositories.PedidoRepository;

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

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
private final ClienteRepository clienteRepository;
private final PedidoRepository pedidoRepository;

    PedidoController(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
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

   @PostMapping("clientePedido/{clienteId}")
    public Pedido pedidoCliente(@PathVariable Long clienteId){
        Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente nao encontrado!"));

        Pedido pedido = new Pedido();
        pedido.setValor(250.0);
        pedido.setCliente(cliente);

        Pedido pedidoNovo = pedidoRepository.save(pedido);

        return pedidoNovo;
    }

    @GetMapping("obterClientePedido/{id}")
    public String obterClientePedido(@PathVariable Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido nao encontrado!"));
        return "Cliente: " + pedido.getCliente().getNome()
           + " | Valor: " + pedido.getValor();
    }





}
