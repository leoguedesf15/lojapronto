package br.com.gm5.loja.repositories;

import org.springframework.data.repository.CrudRepository;



import br.com.gm5.loja.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	
	
	
}
