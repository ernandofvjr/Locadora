package entity;

public enum Tipo {
	PC("Pc"), XBOX360("Xbox 360"), PS3("Ps3"), XBOXONE("Xbox One"), PS4("Ps4");
	
	private String nome;
	
	private Tipo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
