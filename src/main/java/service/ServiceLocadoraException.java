package service;

public class ServiceLocadoraException extends Exception {

	private static final long serialVersionUID = -3082351960302866350L;

	public ServiceLocadoraException(String mensagem) {
		super(mensagem);
	}

	public ServiceLocadoraException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
