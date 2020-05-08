package com.portal.fotografia.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.portal.fotografia.domain.Convite;
import com.portal.fotografia.domain.Usuario;
import com.portal.fotografia.domain.enuns.Perfil;
import com.portal.fotografia.repository.UsuarioRepository;
import com.portal.fotografia.security.UserSS;
import com.portal.fotografia.service.exception.AuthorizationException;
import com.portal.fotografia.service.exception.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PostagemService postagemService;
	
	@Autowired
	BCryptPasswordEncoder pe;

	public List<Usuario> findUsuarioAndPostagensAll() {
		List<Usuario> listUsuario = usuarioRepository.findAll();
		return listUsuario;
	}

	public Usuario insertUser(Usuario obj) {
		obj.addPerfil(Perfil.ADMIN);
		obj.setSenha(pe.encode(obj.getSenha()));
		usuarioRepository.save(obj);
		return obj;
	}

	public Usuario findByNome(String nome) {
		return usuarioRepository.findByNome(nome);
	}

	public Usuario findUsuarioAndPostagensById(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public Usuario findUsuarioAndPostagemByName(String nome) {

		/*------------- Regra de autorização Spring Security ----------------------------*/

		UserSS userAuthenticated = UserService.authenticated();
		if (userAuthenticated == null || !userAuthenticated.hasRole(Perfil.ADMIN)
				&& !nome.equals(userAuthenticated.getUsername().toString())) {
			throw new AuthorizationException("O usuário: " + userAuthenticated.getUsername() + " não tem permissões"
					+ " de acesso para visualizar postagens de outros usuários.");
		}
		/*-------------------------------------------------------------------------------*/

		Usuario user = usuarioRepository.findUsuarioByName(nome);
		Optional<Usuario> objUsuario = Optional.ofNullable(user);

		return objUsuario.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Nome: " + nome + ", Tipo: " + Usuario.class.getName()));
	}

	public void update(Usuario newObj) {
		Usuario oldObj = usuarioRepository.findById(newObj.getId()).get();
		
		UserSS userAuthenticated = UserService.authenticated();
		
		if(!userAuthenticated.getUsername().equals(oldObj.getNome())) {
			throw new AuthorizationException("O usuário: " + userAuthenticated.getUsername() + " não tem permissão"
					+ " para alterar uma senha de outro usuário");
		}
		
		updateData(newObj, oldObj);
		
		usuarioRepository.save(oldObj);
	}

	private void updateData(Usuario newObj, Usuario oldObj) {
		oldObj.setNome(newObj.getNome());
		oldObj.setSenha(pe.encode(newObj.getSenha()));
	}

	public void atualizarListaDeAmigos(Convite c) {
		Usuario usuarioRemetente = usuarioRepository.findById(c.getUsuarioRemetenteId()).get();
		Usuario usuarioDestinatario = usuarioRepository.findById(c.getUsuarioDestinatarioId()).get();
		usuarioRemetente.getAmigos().add(usuarioDestinatario);
		usuarioDestinatario.getAmigos().add(usuarioRemetente);		
		usuarioRepository.saveAll(Arrays.asList(usuarioDestinatario,usuarioRemetente));		
	}
}
