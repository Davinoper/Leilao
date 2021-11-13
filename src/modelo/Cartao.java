package modelo;

import java.time.LocalDate;

import javax.persistence.Entity;


@Entity
public class Cartao extends FormaPagamento{

	
	private String numero;
	private LocalDate vencimento;
	private String ccv;
	private String nome;
	private BandeiraCartao tipo;
	
	
	
	
	public BandeiraCartao getTipo() {
		return tipo;
	}
	public void setTipo(BandeiraCartao tipo) {
		this.tipo = tipo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LocalDate getVencimento() {
		return vencimento;
	}
	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}
	public String getCcv() {
		return ccv;
	}
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
