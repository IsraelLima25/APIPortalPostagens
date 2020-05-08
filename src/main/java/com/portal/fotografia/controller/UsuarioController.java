package com.portal.fotografia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portal.fotografia.domain.Usuario;
import com.portal.fotografia.service.UsuarioService;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	//Cadastro de Perfil
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> CadastrarUsuario(@RequestBody Usuario objDto) {		
		Usuario obj = usuarioService.insertUser(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//Lista todos perfis e suas postagens
	@PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> BuscarTodasPostagensPorUsuarios() {
		List<Usuario> list = usuarioService.findUsuarioAndPostagensAll();
		return ResponseEntity.ok().body(list);
	}	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> BuscarTodasPostagensPorUsuariosId(@PathVariable Integer id) {
		Usuario obj = usuarioService.findUsuarioAndPostagensById(id);		
		return ResponseEntity.ok(obj);
	}	
	
	@RequestMapping(value = "{nome}/postagens", method = RequestMethod.GET)
	public ResponseEntity<Usuario> BuscarTodasPostagensPorUsuariosNome(@PathVariable String nome){
		Usuario obj = usuarioService.findUsuarioAndPostagemByName(nome);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "atualizar-usuario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> AtualizarUsuario(@RequestBody Usuario newObj, @PathVariable Integer id) {	
		newObj.setId(id);
		usuarioService.update(newObj);
		return ResponseEntity.noContent().build();
	}
}
