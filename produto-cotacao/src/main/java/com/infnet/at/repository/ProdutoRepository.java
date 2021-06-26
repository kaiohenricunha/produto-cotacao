package com.infnet.at.repository;

import com.infnet.at.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Produto findProdutosById(Long id);
}
