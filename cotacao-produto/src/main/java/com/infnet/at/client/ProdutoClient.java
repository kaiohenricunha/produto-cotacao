package com.infnet.at.client;

import com.infnet.at.models.Produto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProdutoClient {

    public Produto buscarProdutoPorId(Long id) {
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://localhost:8081/produtos/{id}", Produto.class, id);
         }
}
