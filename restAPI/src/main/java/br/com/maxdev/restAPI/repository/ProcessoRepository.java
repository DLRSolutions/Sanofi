package br.com.maxdev.restAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxdev.restAPI.models.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long>
{
	
}
