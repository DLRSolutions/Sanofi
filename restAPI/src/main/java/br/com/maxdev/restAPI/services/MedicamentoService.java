package br.com.maxdev.restAPI.services;

import java.util.List;
import java.util.Optional;

import br.com.maxdev.restAPI.models.Andamentos;
import br.com.maxdev.restAPI.models.Medicamento;


public interface MedicamentoService 
{
	/*
	* PODE SER ESPECIFICO A UM RESOURCE OU PRIVADO COM METODOS DE LOGICAS INTERNAS DA CLASSE
	* ESTE CASO SER� LIGADO AO RESOURCE - OU SEJA DEPENDE DA ENTRADA DO CLIENTE SER� DIRECIONADO PARA C�
	* NESTA CHAMAMOS TODOS OS METODOS DE MEU RESOURCE
	* */
	public List<Medicamento> findAll();
	public Optional<Medicamento> find(Long id);
	public Medicamento create(Medicamento medicamento);
	public Medicamento update(Long id, Medicamento medicamento);
	public void delete(Long id);

}
