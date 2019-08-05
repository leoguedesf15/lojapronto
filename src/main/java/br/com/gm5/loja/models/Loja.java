package br.com.gm5.loja.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Loja implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_loja")
	private long idLoja;
	private String nomeLoja;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(	name = "item_estoque",
				joinColumns = {@JoinColumn(name="id_loja")},
				inverseJoinColumns = {@JoinColumn(name="id_produto")})
	private List<Produto> itens;

	public long getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(long idLoja) {
		this.idLoja = idLoja;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	
	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Loja [idLoja=" + idLoja + ", nomeLoja=" + nomeLoja + ", itens=" + itens + "]";
	}
	
	
	
	
	
}
