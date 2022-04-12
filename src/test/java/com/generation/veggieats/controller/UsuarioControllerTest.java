package com.generation.veggieats.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.generation.veggieats.model.Usuario;
import com.generation.veggieats.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TestRestTemplate testRestTemplate;//usado apenas no controller, puxando @RestController - métodos http

    @Test
    @Order(1)
    @DisplayName("Cadastrar apenas um usuário")
    public void deveCadastrarUmUsuario() {

        HttpEntity<Usuario>requisicao = new HttpEntity<Usuario>(new Usuario(0L, "DJ Jorda","jorda@email.com","foto.jpg","jorda123","123.456.789-00","Cliente"));

        ResponseEntity<Usuario>resposta=testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome()); 
        assertEquals(requisicao.getBody().getUsuario(),resposta.getBody().getUsuario());
    }
}