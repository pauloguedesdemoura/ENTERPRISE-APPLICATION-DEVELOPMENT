package br.com.fiap.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "T_CARRINHO_COMPRA")
@SequenceGenerator(name = "carrinho", sequenceName = "SQ_T_CARRINHO_COMPRA", allocationSize = 1)
public class CarrinhoCompras {

	@Id
	@Column(name = "cd_carrinho")
	@GeneratedValue(generator = "carrinho", strategy = GenerationType.SEQUENCE)
	private int codigo;
	
	@OneToMany(mappedBy="carrinho")
	private List<ItemCarrinho> itens; 

	@OneToOne
	@JoinColumn(name = "cd_cliente", nullable = false)
	private Cliente cliente;

	@CreationTimestamp
	@Column(name = "dt_carrinho")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	@Column(name = "vl_total", nullable = false)
	private double valorTotal;

}
