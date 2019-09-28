package br.com.maxdev.restAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxdev.restAPI.models.Andamentos;

public interface AndamentoRepository extends JpaRepository<Andamentos, Long>
{
	
}
