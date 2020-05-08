package com.portal.fotografia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portal.fotografia.domain.Comentario;
import com.portal.fotografia.domain.Postagem;
import com.portal.fotografia.service.PostagemService;

@RestController
@RequestMapping(value="/postagem")
public class PostagemController {
	
	@Autowired
	private PostagemService servicePostagem;	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> BuscarTodasPostagens() {
		List<Postagem> list = servicePostagem.findAllPostagem();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Postagem> BuscarPostagensUsuarioPorId(@PathVariable Integer id) {
		Postagem obj = servicePostagem.findPostagensById(id);		
		return ResponseEntity.ok(obj);
	}	

	@RequestMapping(value = "/comentar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> AdicionarComentarioPostagem(@RequestBody Comentario comentario, 
			@PathVariable Integer id) {				
		servicePostagem.AddComent(id, comentario);
		return ResponseEntity.noContent().build();
	}	
	
}
