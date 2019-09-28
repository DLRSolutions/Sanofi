package br.com.maxdev.restAPI.services;

import java.util.List;
import java.util.Optional;

import br.com.maxdev.restAPI.models.Andamentos;
import br.com.maxdev.restAPI.models.Processo;


public interface ProcessoService 
{
	/*
	 * PODE SER ESPECIFICO A UM RESOURCE OU PRIVADO COM METODOS DE LOGICAS INTERNAS DA CLASSE
	 * NESTE CASO SER� LIGADO AO RESOURCE - OU SEJA DEPENDE DA ENTRADA DO CLIENTE SER� DIRECIONADO PARA C�
	 * NESTA CHAMAMOS TODOS OS METODOS DE MEU RESOURCE
	 * */
	public List<Processo> findAll();
	public Optional<Processo> find(Long id);
	public Processo create(Processo processo);
	public Processo update(Long id, Processo processo);
	public void delete(Long id);

}
