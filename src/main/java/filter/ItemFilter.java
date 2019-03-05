package filter;

import java.util.Date;

import entity.SituacaoItem;
import entity.Tipo;


public class ItemFilter {
		
	private String nome;
	
	private String cod;
	
	private String categoria;
	
	private Date dataLancamentoMin;
	
	private Date dataLancamentoMax;
		
	private Tipo tipo;
	
	private SituacaoItem situacao;
	
	public SituacaoItem getSituacao() {
		return situacao;
	}




	public void setSituacao(SituacaoItem situacao) {
		this.situacao = situacao;
	}




	public ItemFilter() {
		
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




	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}




	public Date getDataLancamentoMin() {
		return dataLancamentoMin;
	}




	public void setDataLancamentoMin(Date dataLancamentoMin) {
		this.dataLancamentoMin = dataLancamentoMin;
	}




	public Date getDataLancamentoMax() {
		return dataLancamentoMax;
	}




	public void setDataLancamentoMax(Date dataLancamentoMax) {
		this.dataLancamentoMax = dataLancamentoMax;
	}




	public Tipo getTipo() {
		return tipo;
	}




	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}




	public boolean isEmpty() {
		if (this.categoria != null && !this.categoria.trim().isEmpty()) {
			return false;
		}
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		
		if (this.cod != null && !this.cod.trim().isEmpty()) {
			return false;
		}

		if (this.dataLancamentoMin != null) {
			return false;
		}
		
		if (this.dataLancamentoMax != null) {
			return false;
		}
		
		if (this.tipo != null) {
			return false;
		}
		
		if (this.situacao != null) {
			return false;
		}

		return true;
	}
	
}
