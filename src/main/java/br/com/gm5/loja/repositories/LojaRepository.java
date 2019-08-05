package br.com.gm5.loja.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import br.com.gm5.loja.models.Loja;

public interface LojaRepository extends CrudRepository<Loja, Long> {
	
}
