package com.infnet.at.clients;

import com.infnet.at.models.Cotacao;
import com.infnet.at.models.Produto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CotacaoClient {
    public Cotacao buscaCotacaoPorId(Long id) {
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://localhost:8082/cotacoes/{id}", Cotacao.class, id);    }
}
