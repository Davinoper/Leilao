package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private double valor;
	private String descricao;
	private Categoria categoria;
	private StatusProduto status = StatusProduto.INSTOCK;
	@ManyToOne
	private Empresa empresa;
	private Date tempo;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public StatusProduto getStatus() {
		return status;
	}
	public void setStatus(StatusProduto status) {
		this.status = status;
	}
	public Empresa getEmpresa() {
		if(empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Date getTempo() {
		return tempo;
	}
	public void setTempo(Date tempo) {
		this.tempo = tempo;
	}
	
	
	
	
	
}
