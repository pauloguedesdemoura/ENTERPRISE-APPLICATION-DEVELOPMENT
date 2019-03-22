package br.com.fiap.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToMany(mappedBy="carrinho", cascade = CascadeType.PERSIST)
	private List<ItemCarrinho> itens = new ArrayList<>(); 
	
	public void addItem(ItemCarrinho item) {
		//adiciona o item na lista
		itens.add(item);
		
		//adiciona o carrinho no item
		item.setCarrinho(this);
		
	}

	//fetch -> configura o momento que será carregado a relação
	//cascade -> executa a ação configurada na relação
	@OneToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_cliente", nullable = false)
	private Cliente cliente;

	@CreationTimestamp
	@Column(name = "dt_carrinho")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	@Column(name = "vl_total", nullable = false)
	private double valorTotal;
	
	public CarrinhoCompras(Cliente cliente, double valorTotal) {
		super();
		this.cliente = cliente;
		this.valorTotal = valorTotal;
	}

	public CarrinhoCompras() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	
}
