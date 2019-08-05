package br.com.gm5.loja.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.gm5.loja.models.Usuario;
import br.com.gm5.loja.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@SuppressWarnings("finally")
public class UsuarioController {
	@Autowired
	private UsuarioRepository rep;
	
	@PostMapping("/cadastro")
	String cadastro(@ModelAttribute Usuario user) {
		try {
			rep.save(user);
		}catch(Exception ex) {
			return "Ocorreu um erro inesperado";
		}finally {
			return "Usuário cadastrado com sucesso";
		}
	}
	
	@PostMapping("/entrar")
	String entrar(HttpSession session,
			@RequestParam(value="login") String login,
				@RequestParam(value="senha")String senha) {
		try {
			Usuario user=rep.findOne(login);
			if(user.getSenha().equals(senha)) {
				session.setAttribute("user", user);
				return "usuario logado com sucesso";
			}else {
				throw new Exception();
			}
		}catch(Exception ex) {
			return "LOGIN OU SENHA INCORRETOS";
		}
		
		
	}
	
	@PostMapping("/validaSessao")
	boolean validaSessao(HttpSession session) {
		return session.getAttribute("user")!=null;
	}
	
}
