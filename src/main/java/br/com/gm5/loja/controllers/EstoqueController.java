package br.com.gm5.loja.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gm5.loja.models.ItemEstoque;
import br.com.gm5.loja.models.Loja;
import br.com.gm5.loja.models.Produto;
import br.com.gm5.loja.repositories.EstoqueRepository;
import br.com.gm5.loja.repositories.LojaRepository;
import br.com.gm5.loja.repositories.ProdutoRepository;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueRepository repository;
	
	@Autowired
	private LojaRepository loja_rep;
	
	@Autowired
	private ProdutoRepository prod_rep;
	
	
	@Autowired
	private UsuarioController user;
	
	@PostMapping("/adicionar")
	String add(@RequestParam(value="idProduto") Long idProd, @RequestParam(value = "idLoja") Long idLoja, @RequestParam(value="qtd") int quantidade,HttpSession session) {
		try {
			if(user.validaSessao(session)) {
				ItemEstoque item = new ItemEstoque();
				Produto produto = prod_rep.findOne(idProd);
				item.setProduto(produto);
				Loja loja = loja_rep.findOne(idLoja);
				item.setLoja(loja);
				item.setQuantidade(quantidade);
				repository.save(item);
				return "salvo com sucesso";
				
			}else {
				throw new Exception("Usuário não logado");
			}
		}catch(Exception ex) {
			return ex.getMessage();
		}
		
	}
}
