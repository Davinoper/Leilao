package modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private double valor;
	private String descricao;
	private Categoria categoria;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Empresa empresa;
	private LocalDate tempoInit;
	private LocalDate tempoFim;
	private boolean vendido;
	
	
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
	
	public Empresa getEmpresa() {
		if(empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	

	public LocalDate getTempoInit() {
		return tempoInit;
	}
	public void setTempoInit(LocalDate tempoInit) {
		this.tempoInit = tempoInit;
	}
	public LocalDate getTempoFim() {
		return tempoFim;
	}
	public void setTempoFim(LocalDate tempoFim) {
		this.tempoFim = tempoFim;
	}
	public boolean isVendido() {
		return vendido;
	}
	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", descricao=" + descricao + ", categoria="
				+ categoria + ", empresa=" + empresa + ", tempoInit=" + tempoInit + ", tempoFim=" + tempoFim
				+ ", vendido=" + vendido + "]";
	}
	
	
	
	
	
}
