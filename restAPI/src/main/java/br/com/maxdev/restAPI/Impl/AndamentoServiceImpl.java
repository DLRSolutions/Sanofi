package br.com.maxdev.restAPI.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maxdev.restAPI.models.Andamentos;
import br.com.maxdev.restAPI.repository.AndamentoRepository;
import br.com.maxdev.restAPI.services.AndamentoService;

@Service
public class AndamentoServiceImpl implements AndamentoService{

	//IMPLEMENTAMOS TODOS OS MÉTODOS DE NOSSA INTERFACE
	//DESTA FORMA TEMOS A COPIA DOS METODOS DA RESOURCE ONDE FICA A ENTRADA 
	//E ATRAVES DE UMA CHAMADA VIA INJEÇÃO DE DEPENDENCIA NOS COMUNICAREMOS,
	//CADA METODO SERÁ RESPONSAVEL POR UM RESPECTIVAMENTE
	
	@Autowired
	private AndamentoRepository AndamentoRepository;
	
	//CONSTRUTOR QUE INDICA QUE ESTOU TRABALHANDO COM UM REPOSITORIO
	public AndamentoServiceImpl(AndamentoRepository prodRep) 
	{
		this.AndamentoRepository = prodRep;
	}

	@Override
	public List<Andamentos> findAll() {
		
		return this.AndamentoRepository.findAll();
	}
	
	@Override
	public Optional<Andamentos> find(Long id) 
	{
		return this.AndamentoRepository.findById(id);
	}

	@Override
	public Andamentos create(Andamentos Andamento) 
	{
		return this.AndamentoRepository.save(Andamento);
	}

	@Override
	public Andamentos update(Long id, Andamentos Andamento) 
	{
		Andamentos AndamentoBd = this.AndamentoRepository.findById(id).orElse(null);
		if(AndamentoBd != null) 
		{
			Andamento.setId(AndamentoBd.getId());;
			this.AndamentoRepository.save(Andamento);
			return Andamento;
		}
		
		/*TRABALHANDO COM O OPTIONAL
		 * Optional<Produto> produtoBd = this.produtoRepository.findById(id);
		if(!produtoBd.isEmpty() && produtoBd != null) 
		{
			produtoRecebido.setId(produtoBd.get().getId);
			return this.produtoRepository.save(produtoRecebido);
		}*/
		
		return null;
	}

	@Override
	public void delete(Long id) 
	{
		Optional<Andamentos> andamento = this.AndamentoRepository.findById(id);
		if(andamento.isPresent()) 
		{
			AndamentoRepository.delete(andamento.get()); 
		}
	}
}
