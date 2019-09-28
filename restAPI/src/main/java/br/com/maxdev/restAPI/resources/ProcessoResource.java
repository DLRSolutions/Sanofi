package br.com.maxdev.restAPI.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.maxdev.restAPI.models.Processo;
import br.com.maxdev.restAPI.services.ProcessoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST - Model Processo")
@RestController
@RequestMapping("/processo")
public class ProcessoResource 
{
	@Autowired
	private ProcessoService processoService;

	//CONSTRUTOR PARA CHAMADA E CRIAÇÃO DO MEU SERVIÇO
	public ProcessoResource(ProcessoService processoService) 
	{
		this.processoService = processoService;
	}

	@ApiOperation(value = "Realizará a busca por todos os Processos")
	@GetMapping(produces = "aplication/Json")
	@ResponseBody
	public ResponseEntity<List> findAll()
	{
		List<Processo> processos = processoService.findAll();
		return new ResponseEntity<List>(processos, HttpStatus.FOUND);
	}

	@ApiOperation(value = "Realizará a busca especifica pelo Id (Código do Processo)")
	//MARCANDO O VALOR RECEBIDO COMO UMA PATH VARIABLE - ONDE VAMOS RECEBR  ID
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Processo> find(@PathVariable(value = "id") long id)
	{
		Processo processo = processoService.find(id).orElse(null);
		return new ResponseEntity<Processo>(processo, HttpStatus.FOUND);
	}

	//COMO RECEBEMOS O Processo DE NOSSA PAGINA, SABEMOS QUE ELE VIRÁ VIA POST E PASSARÁ O OBJETO VIA CORPO
	@ApiOperation(value = "Criará um novo Processo")
	@PostMapping
	@ResponseBody
	//@ResponseStatus(code = HttpStatus.CREATED) - ANOTATION PARA INFORMAR UM RETORNO
	//ERRORS - ME PERMITE VERIFICAR E QUAL SERÀ O REDIRECIONAMENTO DO SERVIDOR - COM ISSO PRECISAMOS TRABALHAR COM O RESPONSE ENTITY
	//AO ATRIBUIRMOS ESTE, TEMOS QUE VALIDAR UMA LOGICA DENTRO DO MEU CHAMADO - COLOCANDO DE UMA FORMA GERENICA ATRAVES DO ?
	//DESTA FORMA CONSIGO AVALIAR SE CONSIGO REALIZAR ALGO OU DEVO PAUSAR MINHA APLICAÇÃO E DEVOLVER O PROBLEMA AO CLIENTE
	public ResponseEntity<?> create(@Valid @RequestBody Processo Processo, Errors errors)
	{
		if(!errors.hasErrors()) 
		{
			Processo processoCriado = processoService.create(Processo);
			//DESTA FORMA NÃO PEGARÁ O MEU RETORNO QUE COLOQUEI EM ANOTATION
			//return ResponseEntity.ok(ProcessoCriado);
			//CRIAMOS UM NOVA RESPOSTA DO TIPO Processo PARA CUSTOMIZARMOS, PASSANDO O Processo QUE FOI CRIADO E O RETORNO PERSONALIZADO 
			return new ResponseEntity<Processo>(processoCriado, HttpStatus.CREATED);
		}
		 
		//COLOCAMOS O COMANDO DE RETORNO DENTRO DE NOSSA RESPONSE ENTITY - RESULTADO SÂO AS MENSAGENS DEFINIDAS NA CAMADA ENTITY
		return ResponseEntity.badRequest().body
		(
			//ERRO RECEBIDO APOS A VALIDAÇÂO OU QUALQUER EVENTO INESPERADO
			errors.getAllErrors().stream()
			//TRATANDO TODA A MENSAGEM CONTIDA NESTE ERRO
			.map(
				//USANDO O LAMBDA PARA BUSCAR UM PEDAÇO DA MINHA MENSAGEM
				msg -> msg.getDefaultMessage()
			)
			//INFORMANDO O FINAL DE CADA TERMO USADO, OU SEJA CARACTER DELIMITADOR
			.collect(Collectors.joining(","))
		);
	}
	
	//PARA ATUALIZARMOS TEMOS QUE PRIMEIRO CARREGAR O ESTADO ATUAL, RECEBENDO O ID E PEGAR O VALOR COM AS ALTERAÇÕES VIA BODY
	@ApiOperation(value = "Metodo utilizado para atualizar um Processo")
	@PutMapping(value = "/{id}")
	@ResponseBody
	//FORMA DE UTILIZAR PELA SERVLET PODE IR UM NOCONTENT OU UM OK(200) 
	//public Processo update(@PathVariable(value = "id") long id, @RequestBody Processo Processo, HttpServletResponse response)
	public ResponseEntity<?> update(@PathVariable(value = "id") long id, @Valid @RequestBody Processo Processo, Errors errors)
	{
		if(!errors.hasErrors()) 
		{
			Processo processoAlterado =  processoService.update(id, Processo);
			//DESTA FORMA NÃO PEGARÁ O MEU RETORNO QUE COLOQUEI EM ANOTATION
			//return ResponseEntity.ok(ProcessoCriado);
			//CRIAMOS UM NOVA RESPOSTA DO TIPO Processo PARA CUSTOMIZARMOS, PASSANDO O Processo QUE FOI CRIADO E O RETORNO PERSONALIZADO 
			return new ResponseEntity<Processo>(processoAlterado, HttpStatus.OK);
		}
		
		//COLOCAMOS O COMANDO DE RETORNO DENTRO DE NOSSA RESPONSE ENTITY - RESULTADO SÂO AS MENSAGENS DEFINIDAS NA CAMADA ENTITY
		return ResponseEntity.badRequest().body
		(
			//ERRO RECEBIDO APOS A VALIDAÇÂO OU QUALQUER EVENTO INESPERADO
			errors.getAllErrors().stream()
			//TRATANDO TODA A MENSAGEM CONTIDA NESTE ERRO
			.map(
				//USANDO O LAMBDA PARA BUSCAR UM PEDAÇO DA MINHA MENSAGEM
				msg -> msg.getDefaultMessage()
			)
			//INFORMANDO O FINAL DE CADA TERMO USADO, OU SEJA CARACTER DELIMITADOR
			.collect(Collectors.joining(","))
		);
	}
	
	@ApiOperation(value = "Metodo utilizado para excluir um Processo")
	@DeleteMapping(value = "/{id}")
	@ResponseBody
	//EXEMPLO DE RESPOSTA PELO SERVILET - NÂO USADA
	public void delete(@PathVariable(value = "id") long id, HttpServletResponse response)
	{
		//NESTE CASO PODEMOS RETORNAR UM BOOLEANO OU UMA MODEL
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		processoService.delete(id);
	}
	
}
