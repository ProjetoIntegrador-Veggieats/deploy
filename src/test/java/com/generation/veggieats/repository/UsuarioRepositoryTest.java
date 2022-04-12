package com.generation.veggieats.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.generation.veggieats.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.save(new Usuario(0L, "DJ Jorda","jorda@email.com","foto.jpg","jorda123","123.456.789-00","Cliente"));
		usuarioRepository.save(new Usuario(0L, "DJ Rapha","rapha@email.com","fotorapha.jpg","rapha123","123.456.789-01","Cliente"));
		
	}
	@Test
	@DisplayName("Retorna apenas um usuário")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("jorda@email.com");
		assertTrue(usuario.get().getUsuario().equals("jorda@email.com"));
	}
	
	@Test
	@DisplayName("Retorna dois usuários")
	public void deveRetornarDoisUsuario() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("DJ");
		assertEquals(2, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("DJ Jorda"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("DJ Rapha"));
	}
}
