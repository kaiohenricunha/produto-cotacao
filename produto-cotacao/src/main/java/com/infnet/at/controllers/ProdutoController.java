package com.infnet.at.controllers;

import com.infnet.at.clients.AmazonClient;
import com.infnet.at.clients.CotacaoClient;
import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import com.infnet.at.repository.ProdutoRepository;
import com.infnet.at.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;
    @Autowired
    CotacaoClient cotacaoClient;
    @Autowired
    AmazonClient amazonClient;
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> getProdutos() {
        return produtoService.getProdutos();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Produto> getOne(@PathVariable Long id) {
        return produtoRepository.findById(id);
    }

    @PostMapping
    public Produto register(@RequestPart(value = "nome") String nome,
                            @RequestPart(value = "mfile") MultipartFile mfile) throws IOException {

        //captura a url da imagem e coloca numa string, que vai virar o atributo "imagem" do produto
        String imagemUrl = amazonClient.uploadFile(mfile);
        //transforma o MultiPartFile num File
        File file = amazonClient.convertMultiPartToFile(mfile);
        //envia o "file" para o S3
        amazonClient.uploadFileTos3bucket(imagemUrl, file);

        return produtoService.register(nome, imagemUrl);
    }

    @RequestMapping(value = "/{idProduto}", method = RequestMethod.PUT)
    public Produto editarProduto(@RequestPart("nome") String nome,
                              @RequestPart("imagem") MultipartFile imagem,
                              @PathVariable Long idProduto) throws IOException {

        Produto produto = produtoRepository.findProdutosById(idProduto);
        produto.setNome(nome);
        //captura a url da imagem e coloca numa string, que vai virar o atributo "imagem" do produto
        String imagemUrl = amazonClient.uploadFile(imagem);
        //transforma o MultiPartFile num File
        File file = amazonClient.convertMultiPartToFile(imagem);
        //envia o "file" para o S3
        amazonClient.uploadFileTos3bucket(imagemUrl, file);
        produto.setImagem(imagemUrl);
        return produtoService.edit(produto);
    }

    @PutMapping
    public void update() {
        //produtoService.update(id, produto);
    }

    @RequestMapping(value = "/{idProduto}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idProduto) {
        Produto produto = produtoRepository.findProdutosById(idProduto);
        produtoService.delete(produto);
    }

}
