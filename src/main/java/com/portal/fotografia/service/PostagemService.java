package com.portal.fotografia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.fotografia.domain.Comentario;
import com.portal.fotografia.domain.Postagem;
import com.portal.fotografia.domain.Usuario;
import com.portal.fotografia.repository.PostagemRepository;
import com.portal.fotografia.service.exception.ObjectNotFoundException;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private ComentarioService comentarioService;

	public List<Postagem> findAllPostagem() {
		List<Postagem> listaPostagem = 
				postagemRepository.findAll();
		return listaPostagem;
	}

	public Postagem findPostagensById(Integer id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		return postagem.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " 
		+ Usuario.class.getName()));
	}

	public Postagem AddComent(Integer id, Comentario comentario) {
		Postagem postagemUpdate = postagemRepository.findById(id).get();
		comentario.setPostagem(postagemUpdate);
		comentarioService.InsertComentario(comentario);		
		postagemUpdate.getComentarios().add(comentario);		
		return postagemRepository.save(postagemUpdate);
	}
}
