package com.infnet.at.repository;

import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {


    List<Cotacao> findCotacaoByProduto(Produto produto);

    Cotacao findCotacaoById(Long id);
}
