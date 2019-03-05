package filter;

import java.util.Date;

import entity.Grupo;

public class UsuarioFilter {

	private String nome;
	
	private String login;
	
	private String cpf;
	
	private Date dataNascMax;
	
	private Date dataNascMin;
	
	private Grupo grupo;
	
	
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	public Date getDataNascMax() {
		return dataNascMax;
	}

	public void setDataNascMax(Date dataNascMax) {
		this.dataNascMax = dataNascMax;
	}

	public Date getDataNascMin() {
		return dataNascMin;
	}

	public void setDataNascMin(Date dataNascMin) {
		this.dataNascMin = dataNascMin;
	}

	public boolean isEmpty() {

		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		
		if (this.cpf != null && !this.cpf.trim().isEmpty()) {
			return false;
		}

		if (this.dataNascMin != null) {
			return false;
		}
		
		if (this.dataNascMax != null) {
			return false;
		}
		
		if (this.grupo != null) {
			return false;
		}

		return true;
	}

}
