package com.portal.fotografia.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portal.fotografia.domain.Convite;

@Repository
public interface ConviteRepository extends JpaRepository<Convite, Integer> {
	
	@Transactional
	@Query("SELECT obj FROM Convite obj WHERE obj.usuarioDestinatarioId = :id")
	List<Convite> findConvitePendenteUsuario(Integer id);
}
