package com.infnet.at.service;

import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import com.infnet.at.repository.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CotacaoService {

    @Autowired
    CotacaoRepository cotacaoRepository;

    public List<Cotacao> getCotacoes() {
        return cotacaoRepository.findAll();
    }

    public List<Cotacao> getCotacoesByProduto(Produto produto) {
        return cotacaoRepository.findCotacaoByProduto(produto);
    }

    public Cotacao edit(Cotacao cotacao) {
        cotacaoRepository.save(cotacao);
        return cotacao;
    }

    public Cotacao register(Cotacao cotacao) {
        return cotacaoRepository.save(cotacao);
    }

    public void update(Long id, Cotacao cotacao) {
        cotacao.setId(id);
        cotacaoRepository.save(cotacao);
    }

    public void delete(Cotacao cotacao) {
        cotacaoRepository.delete(cotacao);
    }
}
