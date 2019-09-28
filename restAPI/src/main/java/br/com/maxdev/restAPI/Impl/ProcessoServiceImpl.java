package br.com.maxdev.restAPI.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maxdev.restAPI.models.Processo;
import br.com.maxdev.restAPI.repository.ProcessoRepository;
import br.com.maxdev.restAPI.services.ProcessoService;

@Service
public class ProcessoServiceImpl implements ProcessoService{

	//IMPLEMENTAMOS TODOS OS MÉTODOS DE NOSSA INTERFACE
	//DESTA FORMA TEMOS A COPIA DOS METODOS DA RESOURCE ONDE FICA A ENTRADA 
	//E ATRAVES DE UMA CHAMADA VIA INJEÇÃO DE DEPENDENCIA NOS COMUNICAREMOS,
	//CADA METODO SERÁ RESPONSAVEL POR UM RESPECTIVAMENTE
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	//CONSTRUTOR QUE INDICA QUE ESTOU TRABALHANDO COM UM REPOSITORIO
	public ProcessoServiceImpl(ProcessoRepository prodRep) 
	{
		this.processoRepository = prodRep;
	}

	@Override
	public List<Processo> findAll() {
		
		return this.processoRepository.findAll();
	}
	
	@Override
	public Optional<Processo> find(Long id) 
	{
		return this.processoRepository.findById(id);
	}

	@Override
	public Processo create(Processo Processo) 
	{
		return this.processoRepository.save(Processo);
	}

	@Override
	public Processo update(Long id, Processo processo) 
	{
		Processo processoBd = this.processoRepository.findById(id).orElse(null);
		if(processoBd != null) 
		{
			processo.setId(processoBd.getId());;
			this.processoRepository.save(processo);
			return processo;
		}

		return null;
	}

	@Override
	public void delete(Long id) 
	{
		Optional<Processo> processo = this.processoRepository.findById(id);
		if(processo.isPresent()) 
		{
			processoRepository.delete(processo.get()); 
		}
	}
}
