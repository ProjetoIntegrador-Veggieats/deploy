package com.generation.veggieats.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.generation.veggieats.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByNomeContainingIgnoreCase (String nome);
	public List<Produto> findAllByTipoProdutoContainingIgnoreCase (String tipoProduto);
	
	@Query
	(value = "select * from tb_produto where preco between :inicio and :fim", nativeQuery = true)
		public List<Produto> findByPrecoBetween(BigDecimal inicio, BigDecimal fim);
	@Query
	(value = "select * from tb_produto where estoque between :inicio and :fim", nativeQuery = true)
		public List<Produto> findByEstoqueBetween(BigDecimal inicio, BigDecimal fim);
	@Query
	(value = "select * from tb_produto where preco > :x", nativeQuery = true)
		public List<Produto> findByPrecoMaior(BigDecimal x);
	@Query
	(value = "select * from tb_produto where preco < :y", nativeQuery = true)
		public List<Produto> findByPrecoMenor(BigDecimal y);
}
