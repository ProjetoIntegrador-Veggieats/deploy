package com.generation.veggieats.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.veggieats.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public List<Usuario> findAllByNomeContainingIgnoreCase (String nome);
	
	public List<Usuario> findAllByCpfContainingIgnoreCase (String cpf);
	
	public List<Usuario> findAllByUsuarioContainingIgnoreCase (String usuario);
	
	public List<Usuario> findAllByTipoUsuarioContainingIgnoreCase (String tipoUsuario);
	
	public Optional<Usuario> findByUsuario (String usuario);
}
