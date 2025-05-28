package com.study.haircut2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.haircut2.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByNomeContainingIgnoreCase(String nome);

}
