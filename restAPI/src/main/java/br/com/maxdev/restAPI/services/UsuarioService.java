package br.com.maxdev.restAPI.services;

import java.util.List;
import java.util.Optional;

import br.com.maxdev.restAPI.models.Usuario;


public interface UsuarioService 
{
	/*
	 * PODE SER ESPECIFICO A UM RESOURCE OU PRIVADO COM METODOS DE LOGICAS INTERNAS DA CLASSE
	 * NESTE CASO SERÁ LIGADO AO RESOURCE - OU SEJA DEPENDE DA ENTRADA DO CLIENTE SERÁ DIRECIONADO PARA CÁ
	 * NESTA CHAMAMOS TODOS OS METODOS DE MEU RESOURCE
	 * */
	public List<Usuario> findAll();
	public Optional<Usuario> find(Long id);
	public Usuario create(Usuario usuario);
	public Usuario update(Long id, Usuario usuario);
	public void delete(Long id);

}
