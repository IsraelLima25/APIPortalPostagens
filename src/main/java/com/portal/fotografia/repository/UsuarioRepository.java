package com.portal.fotografia.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portal.fotografia.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Transactional
	@Query("SELECT obj FROM Usuario obj JOIN obj.postagens post WHERE obj.nome = :nome")
	public Usuario findUsuarioByName(@Param("nome") String nome);
	
	Usuario findByNome(String nome);
	
}
