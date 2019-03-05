package entity;

public enum Grupo {
	USUARIO("Usuário"), ADMIN("Admin"), FUNCIONARIO("Funcionário");
	
	
	private String nome;
	
	private Grupo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
