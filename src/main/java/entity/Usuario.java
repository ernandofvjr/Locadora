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
@Table(name = "TB_USUARIO",
uniqueConstraints = { @UniqueConstraint(name = "uk__tb_user__cpf", columnNames = { "cpf" }), 
					  @UniqueConstraint(name = "uk__tb_user__login", columnNames = { "login" }) })
public class Usuario implements Cloneable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String nome;
	
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
	
	@Enumerated(EnumType.STRING)
	private Grupo grupo;
	
	private String email;
	
	private String login;
	
	private String senha;
	
	
	//endereco
	private String rua;
	
	private String cep;
	
	private String num;
	//
	
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String cpf, Date dataNasc, Grupo grupo, String email, String login, String senha, String cep, String rua, String num) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.grupo = grupo;
		this. email = email;
		this.login = login;
		this.senha = senha;
		this.cep = cep;
		this.rua = rua;
		this.num = num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataNasc == null) ? 0 : dataNasc.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNasc == null) {
			if (other.dataNasc != null)
				return false;
		} else if (!dataNasc.equals(other.dataNasc))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (grupo != other.grupo)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataNasc=" + dataNasc + ", grupo=" + grupo
				+ ", email=" + email + ", login=" + login + ", senha=" + senha + ", rua=" + rua + ", cep=" + cep
				+ ", num=" + num + "]";
	}

	public void limparCamposEspecificos() {
		if (grupo == null) {
			return;
		}

		if (Grupo.ADMIN == grupo || Grupo.FUNCIONARIO == grupo) {
			 cep = null;
			 rua = null;
			 num = null;
		}

	}
	
	
	
	
}
