package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;



import entity.Grupo;
import entity.Usuario;
import filter.UsuarioFilter;


@ApplicationScoped
public class UsersDataGeneratorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3371124651147613246L;
	
	@Inject
	private UsuarioService userService;
	
	
	public void generateData() throws ServiceLocadoraException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario admin = getUsuarioAdmin();
		UsuarioFilter filter = new UsuarioFilter();


		// Admin
		filter.setCpf(admin.getCpf());
		List<Usuario> users = userService.findBy(filter);
		users = userService.findBy(filter);
		if (users.isEmpty()) {
			usuarios.add(admin);
		}
		
		// Add
		for (Usuario user : usuarios) {
			userService.salvar(user);
		}
	}

	private Usuario getUsuarioAdmin() {
		Usuario user = new Usuario();
		
		user.setCpf("111.111.111-11");
		user.setLogin("admin");
		user.setSenha("admin");
		user.setGrupo(Grupo.ADMIN);

		user.setNome("Fulano");


		user.setEmail("admin@ggmail.com");
		

		return user;
	}
	
	private Usuario getUsuarioAdmin2() {
		Usuario user = new Usuario();
		
		user.setCpf("111.111.111-12");
		user.setLogin("123");
		user.setSenha("123");
		user.setGrupo(Grupo.ADMIN);

		user.setNome("Fulano");


		user.setEmail("admin@ggmail.com");
		

		return user;
	}

	
}
