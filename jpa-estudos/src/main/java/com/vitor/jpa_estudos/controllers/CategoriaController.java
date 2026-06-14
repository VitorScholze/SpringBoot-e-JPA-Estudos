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


@GetMapping("/categoriaMaisProdutos")
public Categoria categoriaCampea(){
    Integer maisProdutos = 0;
    Categoria categoria =  null;

    for(Categoria c: categoriaRepository.findAll()){
        if(c.getProdutos().size() > maisProdutos){
             maisProdutos = c.getProdutos().size();
             categoria = c;
        }
    }

    return categoria;
}


@GetMapping("/produtoMaisCaroCategoria")
public String produtoMaisCaroCategoria(){
    Double maiorValor = 0.0;
    Double precoProduto = 0.0;
    Produto produto = null;
    Categoria categoria = null;

    for(Categoria c: categoriaRepository.findAll()){
        for(Produto p : c.getProdutos()){
            
            if(p.getPreco() == null){
                continue;
            }

            precoProduto =  p.getPreco();
                
            
            if(precoProduto > maiorValor){
            produto = p;
            categoria = c;
            maiorValor = precoProduto;
            }
        }
    }
    if(produto == null){
        return "Produto mais caro nao encontrado!";
    }

    return "Categoria: "+ categoria.getNome() + " || Produto: " + produto.getNome() + " || Preco: R$" + produto.getPreco();
}


@GetMapping("/precoCategorias")
public String precoCategorias(){

    String resultado = "";
    for(Categoria c: categoriaRepository.findAll()){
        Double valorTotalProduto =0.0; 
        for(Produto p: c.getProdutos()){
            if(p.getPreco() != null){
                 valorTotalProduto += p.getPreco();
            }
        }
        resultado +=  "Categoria: " + c.getNome() + "  || Valor total dos produtos: R$" + valorTotalProduto + "\n";
    }

    return resultado;
}


@GetMapping("/categoriaMaisControlada")
public String categoriaMaisControlada() {

    Categoria categoriaCampea = null;
    Double menorDiferenca = Double.MAX_VALUE;

    for (Categoria c : categoriaRepository.findAll()) {

        Double maiorValor = Double.MIN_VALUE;
        Double menorValor = Double.MAX_VALUE;
        int produtosValidos = 0;

        for (Produto p : c.getProdutos()) {

            if (p.getPreco() != null) {

                produtosValidos++;

                if (p.getPreco() > maiorValor) {
                    maiorValor = p.getPreco();
                }

                if (p.getPreco() < menorValor) {
                    menorValor = p.getPreco();
                }
            }
        }

        if (produtosValidos < 2) {
            continue;
        }

        Double diferenca = maiorValor - menorValor;

        if (diferenca < menorDiferenca) {
            menorDiferenca = diferenca;
            categoriaCampea = c;
        }
    }

    if (categoriaCampea == null) {
        return "Nenhuma categoria com pelo menos 2 produtos válidos foi encontrada!";
    }

    return "Categoria mais controlada: "
            + categoriaCampea.getNome()
            + " || Diferença de preços: R$"
            + menorDiferenca;
}


@GetMapping("/categoriaMaisLucrativa")
public String categoriaMaisLucrativa(){
    Double valorReferencia = Double.MIN_VALUE;
    Categoria categoria = null;

    for(Categoria c: categoriaRepository.findAll()){
        Double somaValorProdutos = 0.0;
        for(Produto p: c.getProdutos()){
            if(p.getPreco() != null){
                somaValorProdutos += p.getPreco();
            }
        }
        if(somaValorProdutos > valorReferencia){
            valorReferencia = somaValorProdutos;
            categoria = c;
        }
    }

    if(categoria != null){
        return "Categoria mais Lucrativa: " + categoria.getNome() + " || Soma valores dos produtos: " + valorReferencia;
    }
    
    return "Nenhuma categoria mais lucrativa foi encontrada!";


}

@GetMapping("/categoriaVIP")
public String categoriaVIP(){
    Double valorTotalCampeao = 0.0;    
    Double maiorMedia = 0.0;
    Double mediaCampea = 0.0;
    Categoria categoria = null;

    for(Categoria c : categoriaRepository.findAll()){

        if(c.getProdutos().isEmpty()){
            continue;
        }
        Double valorTotalProdutos = 0.0;
        for(Produto p : c.getProdutos()){
            if(p.getPreco() != null){
                valorTotalProdutos += p.getPreco();
            }

        }
        Double precoMedio = valorTotalProdutos / c.getProdutos().size();
        if(precoMedio > maiorMedia){
            maiorMedia = precoMedio;
            mediaCampea = precoMedio;
            valorTotalCampeao = valorTotalProdutos;
            categoria = c;
        }
    }
    if(categoria == null){
        return "Nenhuma categoria encontrada!";
    }   


    return "Categoria: "+ categoria.getNome() + " || Quantidade de produtos:" + categoria.getProdutos().size() + 
    " || Valor total produtos: R$" + valorTotalCampeao + " || Preco medio: R$" + mediaCampea;

    

}
}
