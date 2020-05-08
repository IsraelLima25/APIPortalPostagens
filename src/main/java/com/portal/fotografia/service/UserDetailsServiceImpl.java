package com.portal.fotografia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portal.fotografia.domain.Usuario;
import com.portal.fotografia.repository.UsuarioRepository;
import com.portal.fotografia.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repo.findByNome(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserSS(user.getId(),user.getNome(),user.getSenha(),user.getPerfis());
	}

}
