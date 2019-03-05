package entity;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_ALUGUEL")
public class Aluguel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Date dataAluguel;
	@Temporal(TemporalType.DATE)
	private Date dataEntrega;
	
	@Enumerated(EnumType.STRING)
	private SituacaoAluguel situacao;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Usuario usuario;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private List<Item> itens = new ArrayList<Item>();
	
	private Double preco;
	
	public Aluguel() {}
	
	public Aluguel(Date dataAluguel, Date dataEntrega, SituacaoAluguel situacao, Usuario usuario, List<Item> itens, double preco) {
		this.dataAluguel = dataAluguel;
		this.dataEntrega = dataEntrega;
//		this.situacao = situacao;
		this.usuario = usuario;
		this.itens = itens;
		this.preco = preco;
		this.situacao = SituacaoAluguel.NAO_ENTRGUE;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(Date dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public SituacaoAluguel getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoAluguel situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAluguel == null) ? 0 : dataAluguel.hashCode());
		result = prime * result + ((dataEntrega == null) ? 0 : dataEntrega.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
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
		Aluguel outro = (Aluguel) obj;
		if (dataAluguel == null) {
			if (outro.dataAluguel != null)
				return false;
		} else if (!dataAluguel.equals(outro.dataAluguel))
			return false;
		if (dataEntrega == null) {
			if (outro.dataEntrega != null)
				return false;
		} else if (!dataEntrega.equals(outro.dataEntrega))
			return false;
		if (id == null) {
			if (outro.id != null)
				return false;
		} else if (!id.equals(outro.id))
			return false;
		if (usuario == null) {
			if (outro.usuario != null)
				return false;
		} else if (!usuario.equals(outro.usuario))
			return false;
		if (itens == null) {
			if (outro.itens != null)
				return false;
		} else if (!itens.equals(outro.itens))
			return false;
		if (preco == null) {
			if (outro.preco != null)
				return false;
		} else if (!preco.equals(outro.preco))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Aluguel [id=" + id + ", dataAluguel=" + dataAluguel + ", dataEntrega=" + dataEntrega + ", situacao=" + situacao + ", preco="
				+ preco + ", usuario=" + usuario + ", itens=" + itens + "]";
	}
	

}
