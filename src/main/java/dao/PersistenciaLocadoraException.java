package dao;


public class PersistenciaLocadoraException extends LocadoraException {

	private static final long serialVersionUID = 1780243892137781599L;

	public PersistenciaLocadoraException(String mensagem) {
		super(mensagem);
	}

	public PersistenciaLocadoraException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
