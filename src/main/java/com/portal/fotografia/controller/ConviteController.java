package com.portal.fotografia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portal.fotografia.domain.Convite;
import com.portal.fotografia.service.ConviteService;

@RestController
@RequestMapping(value="/convite")
public class ConviteController {
	
	@Autowired
	ConviteService conviteService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/enviar-convite",method = RequestMethod.POST)
	public ResponseEntity<Void> EnviarConvite(@RequestBody Convite obj) {		
		conviteService.AdminstrationSendConvite(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(0).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Convite>> RecuperarConvitesPorUsuarioLogado(@PathVariable Integer id) {
		List<Convite> conviteList = conviteService.findConvitesByUser(id);		
		return ResponseEntity.ok(conviteList);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/responder-convite", method = RequestMethod.PUT)
	public ResponseEntity<Void> RespondeConvite(@RequestParam(name="id") Integer id,
			@RequestParam(name="response") Integer reposta) {
		conviteService.ResponseByConvite(id,reposta);		
		return ResponseEntity.created(null).build();
	}

}
