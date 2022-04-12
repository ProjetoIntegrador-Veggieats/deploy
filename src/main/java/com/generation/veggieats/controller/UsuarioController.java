package com.generation.veggieats.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.veggieats.model.Usuario;
import com.generation.veggieats.model.UsuarioLogin;
import com.generation.veggieats.repository.UsuarioRepository;
import com.generation.veggieats.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins="*", allowedHeaders="*")
public class UsuarioController {

	@Autowired
	public UsuarioRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> GetByNome (@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<Usuario>> GetByCpf (@PathVariable String cpf){
		return ResponseEntity.ok(repository.findAllByCpfContainingIgnoreCase(cpf));
	}
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<List<Usuario>> GetByEmail (@PathVariable String usuario){
		return ResponseEntity.ok(repository.findAllByUsuarioContainingIgnoreCase(usuario));
	}
	@GetMapping("/tipoUsuario/{tipoUsuario}")
	public ResponseEntity<List<Usuario>> GetByTipoUsuario (@PathVariable String tipoUsuario){
		return ResponseEntity.ok(repository.findAllByTipoUsuarioContainingIgnoreCase(tipoUsuario));
	}
	
	//---------------MÉTODO POST--------------
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> post (@Valid @RequestBody Usuario usuario){
		return usuarioService.cadastrarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> logaUsuario(@Valid @RequestBody Optional<UsuarioLogin> user){
		return usuarioService.autenticarUsuario(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	
	//---------------MÉTODO PUT--------------
	
	@PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.atualizarUsuario(usuario).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
	
	//---------------MÉTODO DELETE------------
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
