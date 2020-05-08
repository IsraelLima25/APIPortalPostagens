package com.portal.fotografia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.fotografia.domain.Comentario;
import com.portal.fotografia.repository.ComentarioRepository;

@Service
public class ComentarioService {
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	public void InsertComentario(Comentario comentario) {
		comentarioRepository.save(comentario);
	}

}
