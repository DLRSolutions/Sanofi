package br.com.maxdev.restAPI.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Processo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//USANDO O HIBERNATE VALIDATOR ATRAVES DO HIBERNATE - SEMPRE UTILIZAR AMBAS
	@NotEmpty(message = "Este campo Nome não pode ser vazio.")
	@NotBlank(message = "Este campo Nome não pode ser preenchido com Brancos.")
	@Size(min = 10, max = 254, message = "Este campo Deve conter mais que 10 caracteres e menos que 255.") 
	private String processoAnvisa;
	
	@ManyToOne
	@JoinColumn(name = "id_medicamento")
	private Integer idMedicamento;
	
	public Processo() {	}

	public Processo(long id, @NotEmpty(message = "Este campo Nome não pode ser vazio.") @NotBlank(message = "Este campo Nome não pode ser preenchido com Brancos.") @Size(min = 10, max = 254, message = "Este campo Deve conter mais que 10 caracteres e menos que 255.") String processoAnvisa,
	Integer idMedicamento) 
	{
		this.id = id;
		this.processoAnvisa = processoAnvisa;
		this.idMedicamento = idMedicamento;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProcessoAnvisa() {
		return processoAnvisa;
	}

	public void setProcessoAnvisa(String processoAnvisa) {
		this.processoAnvisa = processoAnvisa;
	}

	public Integer getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	@Override
	public String toString() {
		return "Procedimentos [id=" + id + ", processoAnvisa=" + processoAnvisa + ", idMedicamento=" + idMedicamento + "]";
	}
}
