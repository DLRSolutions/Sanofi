package br.com.maxdev.restAPI.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maxdev.restAPI.models.Medicamento;
import br.com.maxdev.restAPI.repository.MedicamentoRepository;
import br.com.maxdev.restAPI.services.MedicamentoService;

@Service
public class UsuarioServiceImpl implements MedicamentoService{

	//IMPLEMENTAMOS TODOS OS MÉTODOS DE NOSSA INTERFACE
	//DESTA FORMA TEMOS A COPIA DOS METODOS DA RESOURCE ONDE FICA A ENTRADA 
	//E ATRAVES DE UMA CHAMADA VIA INJEÇÃO DE DEPENDENCIA NOS COMUNICAREMOS,
	//CADA METODO SERÁ RESPONSAVEL POR UM RESPECTIVAMENTE
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	//CONSTRUTOR QUE INDICA QUE ESTOU TRABALHANDO COM UM REPOSITORIO
	public UsuarioServiceImpl(MedicamentoRepository prodRep) 
	{
		this.medicamentoRepository = prodRep;
	}

	@Override
	public List<Medicamento> findAll() {
		
		return this.medicamentoRepository.findAll();
	}
	
	@Override
	public Optional<Medicamento> find(Long id) 
	{
		return this.medicamentoRepository.findById(id);
	}

	@Override
	public Medicamento create(Medicamento medicamento) 
	{
		return this.medicamentoRepository.save(medicamento);
	}

	@Override
	public Medicamento update(Long id, Medicamento medicamento) 
	{
		Medicamento medicamentoBd = this.medicamentoRepository.findById(id).orElse(null);
		if(medicamentoBd != null) 
		{
			medicamento.setId(medicamentoBd.getId());;
			this.medicamentoRepository.save(medicamento);
			return medicamento;
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
		Optional<Medicamento> medicamento = this.medicamentoRepository.findById(id);
		if(medicamento.isPresent()) 
		{
			medicamentoRepository.delete(medicamento.get()); 
		}
	}
}
