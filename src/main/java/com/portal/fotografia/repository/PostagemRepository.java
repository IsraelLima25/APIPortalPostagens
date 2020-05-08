package com.portal.fotografia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.fotografia.domain.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem,Integer> {

}
