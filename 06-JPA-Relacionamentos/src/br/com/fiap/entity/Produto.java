package br.com.fiap.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_PRODUTO")
@SequenceGenerator(name = "produto", sequenceName = "SQ_T_PRODUTO", allocationSize = 1)
public class Produto {

	@Id
	@Column(name = "cd_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto")
	private int codigo;

	@OneToMany(mappedBy="produto")
	private List<ItemCarrinho> itens; 
	
	@Column(name = "nm_produto", nullable = false, length = 100)
	private String nome;

	@Column(name = "vl_produto", nullable = false)
	private double valor;

}
