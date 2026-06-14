package com.vitor.jpa_estudos.controllers;

import com.vitor.jpa_estudos.repositories.ProdutoRepository;
import com.vitor.jpa_estudos.repositories.CategoriaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.jpa_estudos.entidades.manytomany.Categoria;
import com.vitor.jpa_estudos.entidades.manytomany.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(
        ProdutoRepository produtoRepository,
        CategoriaRepository categoriaRepository) {

    this.produtoRepository = produtoRepository;
    this.categoriaRepository = categoriaRepository;
}

    @GetMapping
    public Produto inserirProduto(){
        Produto produto = new Produto();
        produto.setNome("Osklen");
        produto.setPreco(650.00);
        produtoRepository.save(produto);
        return produto;
    }


    @GetMapping("/consultar/{id}")
    public Optional<Produto> consultarProduto(@PathVariable Long id){
        return produtoRepository.findById(id);
    
    }

    @DeleteMapping("deletar/{id}")
    public void deleteProduto(@PathVariable Long id){

        try{
            Produto produto = produtoRepository.findById(id).get();
            produtoRepository.delete(produto);
        }catch(RuntimeException e){
            System.out.println(e);
        }
    }

    @PostMapping("{idProduto}/associar/categoria/{idCategoria}")
    public String associar(
        @PathVariable Long idProduto,
        @PathVariable Long idCategoria) {

    Produto produto = produtoRepository.findById(idProduto).orElseThrow(()-> new RuntimeException("Produto nao encontrado!"));
    Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(()-> new RuntimeException("categoria nao encontrada!"));

    produto.getCategorias().add(categoria);

    produtoRepository.save(produto);

    return "Relacionamento criado!";
}

    @GetMapping("/{idCategoria}/produtos")
    public List<Produto> buscarCategoriaProdutos(@PathVariable Long idCategoria) {
    Categoria categoria = categoriaRepository.findById(idCategoria)
            .orElseThrow(() -> new RuntimeException("Categoria nao encontrada!"));

    return categoria.getProdutos();
}


    @GetMapping("/categoria/{idCategoria}/valor-total")
    public String totalValorCategoria(@PathVariable Long idCategoria){
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(()-> new RuntimeException("Nenhuma categoria encontrada!"));
        
        Double total = 0.0;
        for(Produto p: categoria.getProdutos()){
            if(p.getPreco() != null){
                total += p.getPreco();
            }
        }

        return "O valor total dos produtos dessa categoria é de: R$" + total;
    }


    @GetMapping("/produtoValioso")
    public String produtoValioso(){
        Produto produto = null;
        int qtdCategorias = 0;
        for(Produto p: produtoRepository.findAll()){
            if(p.getCategorias().size() > qtdCategorias){
                produto = p;
                qtdCategorias = p.getCategorias().size();
            }
            
        }
        
        if(produto == null){
            return "Produto nao encontrado!";
        }
        return "Produto: " + produto.getNome() 
        + "|| Categoria: " + produto.getCategorias() + "|| Quantidade Categorias: " + qtdCategorias;
    }


@GetMapping("/ProdutoMaisCaroCategoria")
public String produtoMaisCaroCategoria() {

    String resposta = "";

    for (Categoria c : categoriaRepository.findAll()) {

        Produto produtoMaisCaro = null;
        Double maiorPreco = 0.0;

        for (Produto p : c.getProdutos()) {

            if (p.getPreco() != null && p.getPreco() > maiorPreco) {
                maiorPreco = p.getPreco();
                produtoMaisCaro = p;
            }
        }

        if (produtoMaisCaro != null) {
            resposta += "Categoria: " + c.getNome() + "\n";
            resposta += "Produto mais caro: " + produtoMaisCaro.getNome() + "\n";
            resposta += "Preço: R$" + produtoMaisCaro.getPreco() + "\n\n";
        }
    }

    if (resposta.isEmpty()) {
        return "Nenhuma categoria possui produtos com preço!";
    }

    return resposta;
}

@GetMapping("produtoMaisExcluisivo")
public String produtoMaisExcluisivo(){
    Produto produtoExclusivo = null;
    Integer menosCategorias = Integer.MAX_VALUE;

   for(Produto p : produtoRepository.findAll()){

    Integer qtdCategorias = 0;
        qtdCategorias = p.getCategorias().size();
        if(qtdCategorias < menosCategorias){
            menosCategorias = qtdCategorias;
            produtoExclusivo = p;
    }
   }
    if (produtoExclusivo != null) {
        return "Produto: " + produtoExclusivo.getNome()
                + " || Quantidade de Categorias: "
                + menosCategorias;

}

     return "Nenhum produto encontrado!";
}


}

