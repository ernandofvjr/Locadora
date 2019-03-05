package filter;


import java.util.Date;

import entity.SituacaoAluguel;

public class AluguelFilter {

	private Date dataAluguelMin;

	private Date dataAluguelMax;
	
	private SituacaoAluguel situacao;

	private String cpfUsuario;
	
	private Double preco;
	
	public AluguelFilter() {}


	public Date getDataAluguelMin() {
		return dataAluguelMin;
	}


	public void setDataAluguelMin(Date dataAluguelMin) {
		this.dataAluguelMin = dataAluguelMin;
	}


	public Date getDataAluguelMax() {
		return dataAluguelMax;
	}


	public void setDataAluguelMax(Date dataAluguelMax) {
		this.dataAluguelMax = dataAluguelMax;
	}


	public SituacaoAluguel getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoAluguel situacao) {
		this.situacao = situacao;
	}



	public String getCpfUsuario() {
		return cpfUsuario;
	}


	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}


	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public boolean isEmpty() {

		if (this.dataAluguelMin != null) {
			return false;
		}
		
		if (this.dataAluguelMax != null) {
			return false;
		}
		
		if (this.cpfUsuario != null) {
			return false;
		}
		
		if (this.preco != null) {
			return false;
		}
		
		if (this.situacao != null) {
			return false;
		}

		return true;
	}
}
