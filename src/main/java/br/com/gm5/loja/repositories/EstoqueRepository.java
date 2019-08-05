package br.com.gm5.loja.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.gm5.loja.models.ItemEstoque;

public interface EstoqueRepository extends CrudRepository<ItemEstoque, String> {

}
