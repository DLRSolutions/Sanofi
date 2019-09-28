package br.com.maxdev.restAPI.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Andamentos 
{
	//CONFIGURAÇÂO PARA BASE DE DADOS SQL
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANDA_SEQ")
    @SequenceGenerator(sequenceName = "andamento_seq", allocationSize = 1, name = "ANDA_SEQ")
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private long idUsuario;
	
	@ManyToOne
	@JoinColumn(name = "id_´processo")
	private long idProcesso;

	//USANDO O HIBERNATE VALIDATOR ATRAVES DO HIBERNATE - SEMPRE UTILIZAR AMBAS
	@NotEmpty(message = "Este campo Nome não pode ser vazio.")
	@NotBlank(message = "Este campo Nome não pode ser preenchido com Brancos.")
	@Size(min = 10, max = 254, message = "Este campo Deve conter mais que 10 caracteres e menos que 255.")
	private String descricao;
	private String obeservacao;

	private Date dataInclusao;

	public Andamentos() {
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObeservacao() {
		return obeservacao;
	}

	public void setObeservacao(String obeservacao) {
		this.obeservacao = obeservacao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	// INSERINDO VALORES COMO DEFAULT EM MEUS ATRIBUTOS - AGE ANTES DA AÇÂO DA
	// ANOTATION
	// AGE DE ACORDO COM A OPERAÇÂO DO BANCO EX @PrePersist = Save - @PreUpdate -
	// PreRemove e etc.
	@PrePersist
	public void onPrePersist() 
	{
		// TODAS AS VEZES QUE PLICAR UM SAVE ELE VAI PASSAR POR AQUI
		if (dataInclusao == null) {
			this.dataInclusao = new Date();
		}
	}

	@Override
	public String toString() {
		return "Andamentos [id=" + id + ", idUsuario=" + idUsuario + ", idProcesso=" + idProcesso + ", descricao="
				+ descricao + ", obeservacao=" + obeservacao + ", dataInclusao=" + dataInclusao + "]";
	}
}
