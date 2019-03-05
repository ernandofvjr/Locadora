package service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.PersistenciaLocadoraException;
import dao.UsuarioDAO;
import entity.Usuario;
import filter.UsuarioFilter;
import util.TransacionalCdi;

@ApplicationScoped
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -7803325791425670859L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	
	@TransacionalCdi
	public void salvar(Usuario usuario) throws ServiceLocadoraException {
		try {
			
			validarLogin(usuario);
			validarCpf(usuario);
			usuario.limparCamposEspecificos();
			calcularHashDaSenha(usuario);
			usuarioDAO.salvar(usuario);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public void editar(Usuario usuario, boolean senhaAlterada) throws ServiceLocadoraException {
		
		try {
			// Verificar se login já existe
			validarLogin(usuario);
			validarCpf(usuario);
			usuario.limparCamposEspecificos();
			if (senhaAlterada) {
				calcularHashDaSenha(usuario);
			}
			usuarioDAO.editar(usuario);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public void deletar(Usuario usuario) throws ServiceLocadoraException {
		try {
			usuarioDAO.deletar(usuario);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public Usuario buscarPorId(int id) throws ServiceLocadoraException {
		try {
			return usuarioDAO.buscarPorId(id);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public List<Usuario> buscarTodos() throws ServiceLocadoraException {
		try {
			return usuarioDAO.buscarTodos();
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public List<Usuario> findBy(UsuarioFilter filter) throws ServiceLocadoraException {
		try {
			return usuarioDAO.findBy(filter);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	
	private String calcularHashDaSenha(Usuario user) throws ServiceLocadoraException {
		user.setSenha(hash(user.getSenha()));
		return user.getSenha();
	}

	public boolean senhaAtualConfere(String passwordAtualHash, String confirmacaoPasswordAtual) throws ServiceLocadoraException {
		
		if (passwordAtualHash == null && confirmacaoPasswordAtual == null) {
			return true;
		}

		if (passwordAtualHash == null || confirmacaoPasswordAtual == null) {
			return false;
		}
		
		String confirmacaoPasswordAtualHash = hash(confirmacaoPasswordAtual);
		
		return passwordAtualHash.equals(confirmacaoPasswordAtualHash);
	}

	/**
	 * Método que calcula o hash de uma dada senha usando o algoritmo SHA-256.
	 * 
	 * @param password
	 *            senha a ser calculada o hash
	 * @return hash da senha
	 * @throws ServiceDacException
	 *             lançada caso ocorra algum erro durante o processo
	 */
	private String hash(String password) throws ServiceLocadoraException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			return output;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new ServiceLocadoraException("Could not calculate hash!", e);
		}
		
	}

	public void validarLogin(Usuario user) throws ServiceLocadoraException {
		boolean jahExiste = usuarioDAO.existeUsuarioComLogin(user);
		if (jahExiste) {
			throw new ServiceLocadoraException("Login já existe: " + user.getLogin()); 
		}
	}
	
	public void validarCpf(Usuario user) throws ServiceLocadoraException {
		boolean jahExiste = usuarioDAO.existeUsuarioComCPF(user);
		if (jahExiste) {
			throw new ServiceLocadoraException("CPF já existe: " + user.getCpf()); 
		}
	}
	
}
