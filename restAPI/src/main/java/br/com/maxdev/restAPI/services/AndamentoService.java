package br.com.maxdev.restAPI.services;

import java.util.List;
import java.util.Optional;

import br.com.maxdev.restAPI.models.Andamentos;


public interface AndamentoService 
{
	/*
	 * PODE SER ESPECIFICO A UM RESOURCE OU PRIVADO COM METODOS DE LOGICAS INTERNAS DA CLASSE
	 * NESTE CASO SERÁ LIGADO AO RESOURCE - OU SEJA DEPENDE DA ENTRADA DO CLIENTE SERÁ DIRECIONADO PARA CÁ
	 * NESTA CHAMAMOS TODOS OS METODOS DE MEU RESOURCE
	 * */
	public List<Andamentos> findAll();
	public Optional<Andamentos> find(Long id);
	public Andamentos create(Andamentos andamento);
	public Andamentos update(Long id, Andamentos andamento);
	public void delete(Long id);

}
