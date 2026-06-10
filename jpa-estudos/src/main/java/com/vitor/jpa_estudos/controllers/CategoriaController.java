package com.vitor.jpa_estudos.controllers;

import com.vitor.jpa_estudos.repositories.CategoriaRepository;
import com.vitor.jpa_estudos.repositories.ProdutoRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.jpa_estudos.entidades.manytomany.Categoria;
import com.vitor.jpa_estudos.entidades.manytomany.Produto;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    
    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    CategoriaController(CategoriaRepository categoriaRepository,ProdutoRepository produtoRepository ) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public Categoria insertCaregoria(){
        Categoria categoria = new Categoria();
        categoria.setNome("Formal");
        categoriaRepository.save(categoria);
        return categoria;
    }
    
    @GetMapping("/consultar/{id}")
    public String consultarCategoria(@PathVariable Long id){    
    Categoria categoria = categoriaRepository.findById(id).orElse(null);

    if(categoria == null){
        return "Categoria não encontrada";
    }

    return categoria.getNome();
}
    
    @DeleteMapping("deletar/{id}")
    public void deletarCategoria(@PathVariable Long id){
        try{
            Categoria categoria = categoriaRepository.findById(id).get();
            categoriaRepository.delete(categoria);

        }catch(RuntimeException e){
            System.out.println(e);
        }
        
    }


@GetMapping("/{idProduto}/categorias")
public List<Categoria> consultarCategoriasProduto(@PathVariable Long idProduto) {
    Produto produto = produtoRepository.findById(idProduto)
            .orElseThrow(() -> new RuntimeException("Produto nao encontrado!"));

    return produto.getCategorias();
}


}
