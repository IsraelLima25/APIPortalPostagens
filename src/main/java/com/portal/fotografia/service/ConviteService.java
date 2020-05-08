package com.portal.fotografia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.fotografia.domain.Convite;
import com.portal.fotografia.domain.Usuario;
import com.portal.fotografia.domain.enuns.StatusSolicitacao;
import com.portal.fotografia.repository.ConviteRepository;
import com.portal.fotografia.repository.UsuarioRepository;
import com.portal.fotografia.security.UserSS;

@Service
public class ConviteService {

	@Autowired
	UsuarioRepository repoUsuario;

	@Autowired
	UsuarioService repoUsuarioService;

	@Autowired
	ConviteRepository convRepository;

	@SuppressWarnings("unused")
	public void AdminstrationSendConvite(Convite convite) {
		Convite convitePreparado = prepareConvite(convite);
		sendConvite(convitePreparado);
	}

	private Convite prepareConvite(Convite conviteRecebido) {

		Convite convite = new Convite();
		convite.setMensagemSolicitacao(conviteRecebido.getMensagemSolicitacao());
		convite.setStatusSolicitacao(StatusSolicitacao.AGUARDANDO);
		
		UserSS userAutenticathed = UserService.authenticated();
		Usuario usuarioLogadoAndRemetente = repoUsuarioService
				.findByNome(userAutenticathed.getUsername());
		convite.setUsuario(usuarioLogadoAndRemetente);
		convite.setUsuarioDestinatarioId(conviteRecebido.getUsuarioDestinatarioId());
		convite.setUsuarioRemetenteId(usuarioLogadoAndRemetente.getId());
		return convite;
	}

	private void sendConvite(Convite convite) {
		convRepository.save(convite);
	}


	public List<Convite> findConvitesByUser(Integer id) {		
		List<Convite> retornoConvites = convRepository.findConvitePendenteUsuario(id);
		return retornoConvites;
	}

	public void ResponseByConvite(Integer id, Integer resposta) {
		List<Convite> retornoConvites = convRepository.findConvitePendenteUsuario(id);		
		for(Convite c : retornoConvites) {
			if(resposta==0) {				
				c.setStatusSolicitacao(StatusSolicitacao.RECUSADO);			
				convRepository.save(c);
			}else {
				c.setStatusSolicitacao(StatusSolicitacao.ACEITO);			
				convRepository.save(c);
			}
			
			if(c.getStatusSolicitacao() == StatusSolicitacao.ACEITO) {
				repoUsuarioService.atualizarListaDeAmigos(c);
			}
		}
	}

}
