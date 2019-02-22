package br.com.fiap.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 	T_FARMACIA
 * 		*cd_farmacia (NUMBER) (PK) Sequence SQ_T_FARMACIA
 * 		*nm_farmacia (VARCHAR2(50))
 * 		dt_abertura (DATE)
 * 		ds_tipo (VARCHAR2) (Enum)
 * 		ds_plantao (NUMBER)
 */
@Entity
@Table(name="T_FARMACIA")
@SequenceGenerator(name="farmacia",sequenceName="SQ_T_FARMACIA",allocationSize=1)
public class Farmacia {

	@Id
	@Column(name="cd_farmacia")
	@GeneratedValue(generator="farmacia",strategy=GenerationType.SEQUENCE)
	private int codigo;
	
	@Column(name="nm_farmacia",nullable=false,length=50)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_abertura")
	private Calendar dataAbertura;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_tipo")
	private TipoFarmacia tipo;
	
	@Column(name="ds_plantao")
	private boolean plantao;
	
	public Farmacia() {
		super();
	}

	public Farmacia(String nome, Calendar dataAbertura, TipoFarmacia tipo, boolean plantao) {
		super();
		this.nome = nome;
		this.dataAbertura = dataAbertura;
		this.tipo = tipo;
		this.plantao = plantao;
	}

	public Farmacia(int codigo, String nome, Calendar dataAbertura, TipoFarmacia tipo, boolean plantao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataAbertura = dataAbertura;
		this.tipo = tipo;
		this.plantao = plantao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Calendar dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public TipoFarmacia getTipo() {
		return tipo;
	}

	public void setTipo(TipoFarmacia tipo) {
		this.tipo = tipo;
	}

	public boolean isPlantao() {
		return plantao;
	}

	public void setPlantao(boolean plantao) {
		this.plantao = plantao;
	}
	
}



