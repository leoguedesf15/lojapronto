package br.com.gm5.loja.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="item_estoque")
public class ItemEstoque implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_item_estoque")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idItemEstoque;
	
	@ManyToOne
	@JoinColumn(name="id_loja")
	private Loja loja;
	

	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	private int quantidade;

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "ItemEstoque [idItemEstoque=" + idItemEstoque + ", loja=" + loja + ", produto=" + produto
				+ ", quantidade=" + quantidade + "]";
	}
	
	
	

}
