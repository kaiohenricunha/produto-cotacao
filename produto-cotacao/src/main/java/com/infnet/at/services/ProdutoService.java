package com.infnet.at.services;

import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import com.infnet.at.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Produto register(String nome, String imagem) {

        return produtoRepository.save(new Produto(nome, imagem));
    }

    public void update(Long id, Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    public Produto edit(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }
}
