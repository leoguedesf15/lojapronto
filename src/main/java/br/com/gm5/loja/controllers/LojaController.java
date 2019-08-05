package br.com.gm5.loja.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.gm5.loja.models.Loja;
import br.com.gm5.loja.repositories.LojaDAO;
import br.com.gm5.loja.repositories.LojaRepository;

@RestController
@RequestMapping("/loja")
public class LojaController {
	@Autowired
	private UsuarioController ucontroller;

	@Autowired
	private LojaDAO lojaDAO;
	@Autowired
	private LojaRepository repository;

	@PostMapping("/cadastrar")
	String cadastrar(@ModelAttribute Loja loja, HttpSession session) {
		try {

			if (ucontroller.validaSessao(session)) {
				repository.save(loja);
				return "Loja cadastrada com sucesso!";
			} else
				throw new Exception("Usuário não foi logado");
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	@PostMapping("/alterar")
	String alterar(@RequestParam(value="nomeLoja")String nomeLoja, 
					@RequestParam(value="id")Long id, HttpSession session) {
		try {

			if (ucontroller.validaSessao(session)) {
				Loja loja1 = repository.findOne(id);
				loja1.setNomeLoja(nomeLoja);				
				repository.save(loja1);
				return "Loja alterada com sucesso!";
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
				return "Loja deletado com sucesso!";
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
				Iterable<Loja> lojas = repository.findAll();
				lojas.forEach(loja->{
					loja.getItens().forEach(prod->{
						prod.setLojas(null);
					});
				});
				
				return g.toJson(lojas);
			} else
				throw new Exception("Usuário não foi logado");
		} catch (Exception ex) {
			 return ex.getMessage();
			 
		}
	}
	
	
	@PostMapping("/byName")
	String findByName(@RequestParam(value="nome")String nome, HttpSession session) {
		try {

			if (ucontroller.validaSessao(session)) {
				Gson g = new Gson();
				Iterable<Loja> lojas = lojaDAO.findByName(nome);
				lojas.forEach(loja->{
					loja.getItens().forEach(prod->{
						prod.setLojas(null);
					});
				});
				
				return g.toJson(lojas);
			} else
				throw new Exception("Usuário não foi logado");
		} catch (Exception ex) {
			 return ex.getMessage();
			 
		}
	}
	
	
}
