package br.com.maxdev.restAPI.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Usuario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//USANDO O HIBERNATE VALIDATOR ATRAVES DO HIBERNATE - SEMPRE UTILIZAR AMBAS
	@NotEmpty(message = "Este campo Nome não pode ser vazio.")
	@NotBlank(message = "Este campo Nome não pode ser preenchido com Brancos.")
	@Size(min = 10, max = 254, message = "Este campo Deve conter mais que 10 caracteres e menos que 255.") 
	private String nome;
	private String email;
	private String senha;
	private String idGerente;
	private Date dataInclusao;

	public Usuario() {	}
	
	//INSERINDO VALORES COMO DEFAULT EM MEUS ATRIBUTOS - AGE ANTES DA AÇÂO DA ANOTATION
	//AGE DE ACORDO COM A OPERAÇÂO DO BANCO EX @PrePersist = Save - @PreUpdate - PreRemove e etc.
	@PrePersist
	public void onPrePersist() 
	{
		//TODAS AS VEZES QUE PLICAR UM SAVE ELE VAI PASSAR POR AQUI
		if(dataInclusao == null) 
		{
			this.dataInclusao = new Date();
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(String idGerente) {
		this.idGerente = idGerente;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}
