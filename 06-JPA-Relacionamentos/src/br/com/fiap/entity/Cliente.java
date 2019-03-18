package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_CLIENTE")
@SequenceGenerator(name = "cliente", sequenceName = "SQ_T_CLIENTE", allocationSize = 1)
public class Cliente {

	@Id
	@Column(name = "cd_cliente")
	@GeneratedValue(generator = "cliente", strategy = GenerationType.SEQUENCE)
	private int codigo;
	
	//mappedBy -> atributo que mapeia a FK na classe Cliente
	@OneToOne(mappedBy="cliente")
	private CarrinhoCompras carrinho;

	@Column(name = "nm_cliente", nullable = false, length = 100)
	private String nome;

	@Column(name = "nr_cpf", length = 11)
	private String cpf;

}
