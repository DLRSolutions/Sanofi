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

import br.com.maxdev.restAPI.models.Medicamento;
import br.com.maxdev.restAPI.services.MedicamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST - Model Medicamento")
@RestController
@RequestMapping("/medicamento")
public class MedicamentoResource 
{
	@Autowired
	private MedicamentoService medicamentoService;

	//CONSTRUTOR PARA CHAMADA E CRIA��O DO MEU SERVI�O
	public MedicamentoResource(MedicamentoService medicamentoService) 
	{
		this.medicamentoService = medicamentoService;
	}

	@ApiOperation(value = "Realizar� a busca por todos os Medicamentos")
	@GetMapping(produces = "aplication/Json")
	@ResponseBody
	public ResponseEntity<List> findAll()
	{
		List<Medicamento> medicamentos = medicamentoService.findAll();
		return new ResponseEntity<List>(medicamentos, HttpStatus.FOUND);
	}

	@ApiOperation(value = "Realizar� a busca especifica pelo Id (C�digo do medicamento)")
	//MARCANDO O VALOR RECEBIDO COMO UMA PATH VARIABLE - ONDE VAMOS RECEBR  ID
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Medicamento> find(@PathVariable(value = "id") long id)
	{
		Medicamento medicamento = medicamentoService.find(id).orElse(null);
		return new ResponseEntity<Medicamento>(medicamento, HttpStatus.FOUND);
	}

	//COMO RECEBEMOS O medicamento DE NOSSA PAGINA, SABEMOS QUE ELE VIR� VIA POST E PASSAR� O OBJETO VIA CORPO
	@ApiOperation(value = "Criar� um novo medicamento")
	@PostMapping
	@ResponseBody
	//@ResponseStatus(code = HttpStatus.CREATED) - ANOTATION PARA INFORMAR UM RETORNO
	//ERRORS - ME PERMITE VERIFICAR E QUAL SER� O REDIRECIONAMENTO DO SERVIDOR - COM ISSO PRECISAMOS TRABALHAR COM O RESPONSE ENTITY
	//AO ATRIBUIRMOS ESTE, TEMOS QUE VALIDAR UMA LOGICA DENTRO DO MEU CHAMADO - COLOCANDO DE UMA FORMA GERENICA ATRAVES DO ?
	//DESTA FORMA CONSIGO AVALIAR SE CONSIGO REALIZAR ALGO OU DEVO PAUSAR MINHA APLICA��O E DEVOLVER O PROBLEMA AO CLIENTE
	public ResponseEntity<?> create(@Valid @RequestBody Medicamento medicamento, Errors errors)
	{
		if(!errors.hasErrors()) 
		{
			Medicamento medicamentoCriado = medicamentoService.create(medicamento);
			//DESTA FORMA N�O PEGAR� O MEU RETORNO QUE COLOQUEI EM ANOTATION
			//return ResponseEntity.ok(medicamentoCriado);
			//CRIAMOS UM NOVA RESPOSTA DO TIPO medicamento PARA CUSTOMIZARMOS, PASSANDO O medicamento QUE FOI CRIADO E O RETORNO PERSONALIZADO 
			return new ResponseEntity<Medicamento>(medicamentoCriado, HttpStatus.CREATED);
		}
		 
		//COLOCAMOS O COMANDO DE RETORNO DENTRO DE NOSSA RESPONSE ENTITY - RESULTADO S�O AS MENSAGENS DEFINIDAS NA CAMADA ENTITY
		return ResponseEntity.badRequest().body
		(
			//ERRO RECEBIDO APOS A VALIDA��O OU QUALQUER EVENTO INESPERADO
			errors.getAllErrors().stream()
			//TRATANDO TODA A MENSAGEM CONTIDA NESTE ERRO
			.map(
				//USANDO O LAMBDA PARA BUSCAR UM PEDA�O DA MINHA MENSAGEM
				msg -> msg.getDefaultMessage()
			)
			//INFORMANDO O FINAL DE CADA TERMO USADO, OU SEJA CARACTER DELIMITADOR
			.collect(Collectors.joining(","))
		);
	}
	
	//PARA ATUALIZARMOS TEMOS QUE PRIMEIRO CARREGAR O ESTADO ATUAL, RECEBENDO O ID E PEGAR O VALOR COM AS ALTERA��ES VIA BODY
	@ApiOperation(value = "Metodo utilizado para atualizar um medicamento")
	@PutMapping(value = "/{id}")
	@ResponseBody
	//FORMA DE UTILIZAR PELA SERVLET PODE IR UM NOCONTENT OU UM OK(200) 
	//public medicamento update(@PathVariable(value = "id") long id, @RequestBody medicamento medicamento, HttpServletResponse response)
	public ResponseEntity<?> update(@PathVariable(value = "id") long id, @Valid @RequestBody Medicamento medicamento, Errors errors)
	{
		if(!errors.hasErrors()) 
		{
			Medicamento medicamentoAlterado =  medicamentoService.update(id, medicamento);
			//DESTA FORMA N�O PEGAR� O MEU RETORNO QUE COLOQUEI EM ANOTATION
			//return ResponseEntity.ok(medicamentoCriado);
			//CRIAMOS UM NOVA RESPOSTA DO TIPO medicamento PARA CUSTOMIZARMOS, PASSANDO O medicamento QUE FOI CRIADO E O RETORNO PERSONALIZADO 
			return new ResponseEntity<Medicamento>(medicamentoAlterado, HttpStatus.OK);
		}
		
		//COLOCAMOS O COMANDO DE RETORNO DENTRO DE NOSSA RESPONSE ENTITY - RESULTADO S�O AS MENSAGENS DEFINIDAS NA CAMADA ENTITY
		return ResponseEntity.badRequest().body
		(
			//ERRO RECEBIDO APOS A VALIDA��O OU QUALQUER EVENTO INESPERADO
			errors.getAllErrors().stream()
			//TRATANDO TODA A MENSAGEM CONTIDA NESTE ERRO
			.map(
				//USANDO O LAMBDA PARA BUSCAR UM PEDA�O DA MINHA MENSAGEM
				msg -> msg.getDefaultMessage()
			)
			//INFORMANDO O FINAL DE CADA TERMO USADO, OU SEJA CARACTER DELIMITADOR
			.collect(Collectors.joining(","))
		);
	}
	
	@ApiOperation(value = "Metodo utilizado para excluir um medicamento")
	@DeleteMapping(value = "/{id}")
	@ResponseBody
	//EXEMPLO DE RESPOSTA PELO SERVILET - N�O USADA
	public void delete(@PathVariable(value = "id") long id, HttpServletResponse response)
	{
		//NESTE CASO PODEMOS RETORNAR UM BOOLEANO OU UMA MODEL
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		medicamentoService.delete(id);
	}
	
}
