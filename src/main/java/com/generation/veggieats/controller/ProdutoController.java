	package com.generation.veggieats.controller;

import java.math.BigDecimal;
import java.util.List;

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

import com.generation.veggieats.model.Produto;
import com.generation.veggieats.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	public ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByNome (@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
    public ResponseEntity<List<Produto>> getByPrecoBetween(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
        return ResponseEntity.ok(repository.findByPrecoBetween(inicio, fim));
    }
	
	@GetMapping("/estoque_inicial/{inicio}/estoque_final/{fim}")
    public ResponseEntity<List<Produto>> getByEstoqueBetween(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
        return ResponseEntity.ok(repository.findByEstoqueBetween(inicio, fim));
    }
	@GetMapping("/tipoProduto/{tipoProduto}")
	public ResponseEntity<List<Produto>> GetByTipo (@PathVariable String tipoProduto){
		return ResponseEntity.ok(repository.findAllByTipoProdutoContainingIgnoreCase(tipoProduto));
	}
	@GetMapping("/precoMaior/{x}")
    public ResponseEntity<List<Produto>> getByPrecoMaior(@PathVariable BigDecimal x){
        return ResponseEntity.ok(repository.findByPrecoMaior(x));
	}
	@GetMapping("/precoMenor/{y}")
    public ResponseEntity<List<Produto>> getByPrecoMenor(@PathVariable BigDecimal y){
        return ResponseEntity.ok(repository.findByPrecoMenor(y));
	}
	//---------------MÉTODO POST--------------
	
	@PostMapping
	public ResponseEntity<Produto> post (@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	//---------------MÉTODO PUT--------------
	
	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	//---------------MÉTODO DELETE------------
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
