package br.com.maxdev.restAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxdev.restAPI.models.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>
{
	
}
