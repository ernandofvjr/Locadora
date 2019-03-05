package beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Usuario;
import service.ServiceLocadoraException;
import service.UsuarioService;



@Named
@ViewScoped
public class EditarSenhaUsuario extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4244039822180927305L;

	private Usuario usuario;

	private String confirmacaoPasswordAtual;

	private String passwordAtualHash;

	@Inject
	private UsuarioService userService;

	public void armazenarSenhaAtualDoUsuario() {
		passwordAtualHash = this.usuario.getSenha();
	}

	public String alterarSenha(boolean senhaUsuarioLogado) {

		try {
			// Confirmar que senha atual equivale a armazenada
			if (!senhaAtualConfere()) {
				reportarMensagemDeErro("A senha atual está incorreta!");
				return null; // Permanecer na mesma página
			}

			userService.editar(usuario, true);
		} catch (ServiceLocadoraException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		
		if(senhaUsuarioLogado) {
			reportarMensagemDeSucesso("Sua senha foi alterada com sucesso!");
			return "/paginas/protegido/index.xhtml?faces-redirect=true";
		}
		else {
			reportarMensagemDeSucesso("A senha do usuário '" + usuario.getNome() + "' foi alterada com sucesso!");
			return "/paginas/protegido/funcionario/filtrar_usuario?faces-redirect=true";
		}
	}

	private boolean senhaAtualConfere() throws ServiceLocadoraException {
		
		return userService.senhaAtualConfere(passwordAtualHash, getConfirmacaoPasswordAtual());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmacaoPasswordAtual() {
		return confirmacaoPasswordAtual;
	}

	public void setConfirmacaoPasswordAtual(String confirmacaoPasswordAtual) {
		this.confirmacaoPasswordAtual = confirmacaoPasswordAtual;
	}

}
