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
public class Medicamento 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//USANDO O HIBERNATE VALIDATOR ATRAVES DO HIBERNATE - SEMPRE UTILIZAR AMBAS
	@NotEmpty(message = "Este campo Nome não pode ser vazio.")
	@NotBlank(message = "Este campo Nome não pode ser preenchido com Brancos.")
	@Size(min = 10, max = 254, message = "Este campo Deve conter mais que 10 caracteres e menos que 255.") 
	private String descricao;
	private Date dataInclusao;

	public Medicamento() {	}
	
	public Medicamento(int id, String descricao, Date dataInclusao) 
	{
		this.id = id;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
	}
	
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

	public long getId() 
	{
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	@Override
	public String toString() 
	{
		return "Produto [ id=" + id + ", descricao=" + descricao + ", dataInclusao=" + dataInclusao + "]";
	}
}
