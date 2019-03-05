package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TB_ITEM",
uniqueConstraints = { @UniqueConstraint(name = "uk__tb_item__cod", columnNames = { "cod" }) })

public class Item implements Cloneable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String cod;
	
	private String nome;
	
	private String categoria;
	
	@Temporal(TemporalType.DATE)
	private Date dataLancamento;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@Enumerated(EnumType.STRING)
	private SituacaoItem situacao;
	

	public Item() {
		
	}
	
	public Item(String nome, String cod, String categoria, Date dataLancamento, String descricao, Tipo tipo, SituacaoItem situacao) {
		this.nome = nome;
		this.cod = cod;
		this.categoria = categoria;
		this.dataLancamento = dataLancamento;
		this.descricao = descricao;
		this.tipo = tipo;
		this.situacao = situacao;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public SituacaoItem getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoItem situacao) {
		this.situacao = situacao;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categorias) {
		this.categoria = categorias;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item outro = (Item) obj;
		if (dataLancamento == null) {
			if (outro.dataLancamento != null)
				return false;
		} else if (!dataLancamento.equals(outro.dataLancamento))
			return false;
		if (categoria == null) {
			if (outro.categoria != null)
				return false;
		} else if (!categoria.equals(outro.categoria))
			return false;
		if (descricao == null) {
			if (outro.descricao != null)
				return false;
		} else if (!descricao.equals(outro.descricao))
			return false;
		if (id == null) {
			if (outro.id != null)
				return false;
		} else if (!id.equals(outro.id))
			return false;
		if (tipo != outro.tipo)
			return false;
		if (nome == null) {
			if (outro.nome != null)
				return false;
		} else if (!nome.equals(outro.nome))
			return false;
		if (cod == null) {
			if (outro.cod != null)
				return false;
		} else if (!cod.equals(outro.cod))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", cod=" + cod + ", nome=" + nome + ", categoria=" + categoria + ", dataLancamento="
				+ dataLancamento + ", descricao=" + descricao + ", descricao=" + descricao + ", tipo=" + tipo + ", situacao=" + situacao + "]";
	}
	
	
}
