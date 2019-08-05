package br.com.gm5.loja.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idProduto;
	
	private String nomeProduto;
	private Float precoProduto;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "item_estoque",
				joinColumns = {@JoinColumn(name="id_produto")},
				inverseJoinColumns = {@JoinColumn(name="id_loja")})
	private List<Loja> lojas;
	
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Float getPrecoProduto() {
		return precoProduto;
	}
	public void setPrecoProduto(Float preco) {
		this.precoProduto = preco;
	}
	
	public List<Loja> getLojas() {
		return lojas;
	}
	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	
	
	
	
}
