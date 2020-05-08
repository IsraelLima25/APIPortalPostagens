package com.portal.fotografia;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.portal.fotografia.domain.Postagem;
import com.portal.fotografia.domain.Usuario;
import com.portal.fotografia.domain.enuns.Perfil;
import com.portal.fotografia.repository.PostagemRepository;
import com.portal.fotografia.repository.UsuarioRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PortalFotografiasApiApplication implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	BCryptPasswordEncoder pe;

	public static void main(String[] args) {
		SpringApplication.run(PortalFotografiasApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Carregar Base de dados
		
		Usuario user1 = new Usuario();
		user1.setNome("israel");
		user1.setSenha(pe.encode("1234"));
		user1.addPerfil(Perfil.ADMIN);
		
		Usuario user2 = new Usuario();
		user2.setNome("lima");
		user2.setSenha(pe.encode("12345"));
		user2.addPerfil(Perfil.CONVID);
		
		Usuario user3 = new Usuario();
		user3.setNome("eliana");
		user3.setSenha(pe.encode("32931275"));
		user3.addPerfil(Perfil.ADMIN);	
		
		usuarioRepository.saveAll(Arrays.asList(user1,user2,user3));
		
		/*
		Convite convite = new Convite();
		convite.setMensagemSolicitacao("solicitação");
		convite.setStatusSolicitacao(StatusSolicitacao.AGUARDANDO);
		convite.setTipoConvite(TipoConvite.SOLICITACAO_ENVIADA);
		convite.setUsuario(user1);
		convite.setUsuarioDestinatario(3);
		convite.setUsuarioRemetenteId(user1.getId());
		
		ConviteRepository.save(convite);
		*/
		
		Postagem p1 = new Postagem();
		p1.setUsuario(user1);		
		p1.setFotoDiretorio("././assets/caatinga.jpg");
		p1.setTitulo("Caatinga no nordeste");
		p1.setBiografia("Registro de uma viagem ao nordeste em 30/04/1998");

		
		Postagem p2 = new Postagem();
		p2.setUsuario(user1);
		p2.setFotoDiretorio("././assets/cataratas_iguacu.jpg");
		p2.setTitulo("Cataratas do Iguaçu");
		p2.setBiografia("Registro das cataras do iguaçu");
		
		Postagem p3 = new Postagem();
		p3.setUsuario(user1);
		p3.setFotoDiretorio("././assets/elevador_lacerda.jpg");
		p3.setTitulo("Elevador Lacerda");
		p3.setBiografia("Viagem Turística a cidade de Salvador-BA");
		
		Postagem p4 = new Postagem();
		p4.setUsuario(user1);
		p4.setFotoDiretorio("././assets/estatua_liberdade.jpg");
		p4.setTitulo("Estátua da Liberdade");
		p4.setBiografia("Passeio de navio nos Estados Unidos, registro de um monumento histórico");

		Postagem p5 = new Postagem();
		p5.setUsuario(user1);
		p5.setFotoDiretorio("././assets/maracana.jpg");
		p5.setTitulo("Estadio Maracanã");
		p5.setBiografia("Visita ao estadio do maracanã, o maior do mundo!!");
		
		Postagem p6 = new Postagem();
		p6.setUsuario(user2);
		p6.setFotoDiretorio("././assets/neve_nova_york.jpg");
		p6.setTitulo("Neve Cidade Nova York");
		p6.setBiografia("Nova York nevando em uma bela noite.");
		
		Postagem p7 = new Postagem();
		p7.setUsuario(user2);
		p7.setFotoDiretorio("././assets/pelourinho.jpg");
		p7.setTitulo("Pelourinho");
		p7.setBiografia("Linda rua pelourinho");
		
		Postagem p8 = new Postagem();
		p8.setUsuario(user2);
		p8.setFotoDiretorio("././assets/por_do_sol_savana.jpg");
		p8.setTitulo("Savana Africana");
		p8.setBiografia("Registro de uma viagem á Africa");
		
		Postagem p9 = new Postagem();
		p9.setUsuario(user2);
		p9.setFotoDiretorio("././assets/rocha_nordeste.jpg");
		p9.setTitulo("Rocha antiga caatinga nordestina.");
		p9.setBiografia("Viagem turística a estudo do cangaço");
		
		Postagem p10 = new Postagem();
		p10.setUsuario(user2);
		p10.setFotoDiretorio("././assets/torre_eiffel.jpg");
		p10.setTitulo("Torre Eiffel");
		p10.setBiografia("Inesquecivél viagem a França em 2010");		
		
		postagemRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10));
		
		
	}

}
