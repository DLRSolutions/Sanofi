package br.com.maxdev.restAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxdev.restAPI.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
	
}
