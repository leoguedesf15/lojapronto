package br.com.gm5.loja.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.gm5.loja.models.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
}
