package dao;

public class LocadoraException extends Exception {

	private static final long serialVersionUID = -7669751088704144947L;

	public LocadoraException(String mensagem) {
		super(mensagem);
	}

	public LocadoraException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
