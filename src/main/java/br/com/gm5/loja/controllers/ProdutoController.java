package br.com.gm5.loja.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.gm5.loja.models.Produto;
import br.com.gm5.loja.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private UsuarioController ucontroller;

	@Autowired
	private ProdutoRepository repository;

	@PostMapping("/cadastrar")
	String cadastrar(@ModelAttribute Produto produto, HttpSession session) {
		try {

			if (ucontroller.validaSessao(session)) {
				repository.save(produto);
				return "Produto cadastrado com sucesso!";
			} else
				throw new Exception("Usuário não foi logado");
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	@PostMapping("/alterar")
	String alterar(@ModelAttribute Produto produto, 
					@RequestParam(value="id")Long id, HttpSession session) {
		try {

			if (ucontroller.validaSessao(session)) {
				Produto prod = repository.findOne(id);
				prod.setNomeProduto(produto.getNomeProduto());
				prod.setPrecoProduto(produto.getPrecoProduto());
				repository.save(prod);
				return "Produto alterado com sucesso!";
			} else
				throw new Exception("Usuário não foi logado");
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}
	
	@PostMapping("/apagar")
	String deletar(@RequestParam(value="id")Long id, HttpSession session) {
		try {

			if (ucontroller.validaSessao(session)) {
				repository.delete(id);
				return "Produto deletado com sucesso!";
			} else
				throw new Exception("Usuário não foi logado");
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}
	@PostMapping("/listar")
	String findAll(HttpSession session) {
		try {

			if (ucontroller.validaSessao(session)) {
				Gson g = new Gson();
				return g.toJson(repository.findAll());
			} else
				throw new Exception("Usuário não foi logado");
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}
	
	

}
