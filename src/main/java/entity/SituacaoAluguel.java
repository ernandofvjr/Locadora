package entity;

public enum SituacaoAluguel {
	
	ENTREGUE("Entregue"), NAO_ENTRGUE("Não Entregue");
	
	private String nome;
	
	private SituacaoAluguel(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
