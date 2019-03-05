package entity;

public enum SituacaoItem {
	DISPONIVEL("Disponivel"), ALUGADO("Alugado");
	
	private String nome;
	
	private SituacaoItem(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
