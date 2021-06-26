package com.infnet.at.controllers;

import com.infnet.at.client.ProdutoClient;
import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import com.infnet.at.repository.CotacaoRepository;
import com.infnet.at.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cotacoes")
public class CotacaoController {

    @Autowired
    CotacaoService cotacaoService;
    @Autowired
    ProdutoClient produtoClient;
    @Autowired
    CotacaoRepository cotacaoRepository;

    @GetMapping
    public List<Cotacao> getCotacoes() {
        return cotacaoService.getCotacoes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Cotacao> getOne(@PathVariable Long id) {
        return cotacaoRepository.findById(id);
    }

    @GetMapping(path = "/{produtoId}")
    public List<Cotacao> getCotacoesByProduto(@PathVariable Long produtoId) {
        Produto produto = produtoClient.buscarProdutoPorId(produtoId);
        return cotacaoService.getCotacoesByProduto(produto);
    }

    @PostMapping( "/{idProduto}")
    public Cotacao register(@RequestBody Cotacao cotacao,
                            @PathVariable Long idProduto) {

        Produto produto = produtoClient.buscarProdutoPorId(idProduto);
        cotacao.setProduto(produto);
        Cotacao cotacao1 = cotacaoService.register(cotacao);
        return cotacao1;
    }

    @PutMapping("/{idCotacao}")
    public Cotacao editarCotacao(@RequestPart(value = "fonteCotacao") String fonteCotacao,
                                 @RequestPart(value = "valor") String valor,
                                @PathVariable Long idCotacao) {
        Cotacao cotacao = cotacaoRepository.findCotacaoById(idCotacao);
        cotacao.setFonteCotacao(fonteCotacao);
        cotacao.setValor(valor);
        return cotacaoService.edit(cotacao);
    }

    @RequestMapping(value = "/{idCotacao}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idCotacao) {
        Cotacao cotacao = cotacaoRepository.findCotacaoById(idCotacao);
        cotacaoService.delete(cotacao);
    }

}
