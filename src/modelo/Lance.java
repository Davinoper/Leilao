package modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate tempo;
	private double valor;
	@OneToOne(cascade = CascadeType.ALL)
	private Produto prod;
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	private boolean ganhador;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getTempo() {
		return tempo;
	}
	public void setTempo(LocalDate tempo) {
		this.tempo = tempo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Produto getProd() {
		if(prod == null) {
			prod = new Produto();
		}
		return prod;
	}
	public void setProd(Produto prod) {
		this.prod = prod;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Lance [id=" + id + ", tempo=" + tempo + ", valor=" + valor + ", prod=" + prod + ", usuario=" + usuario
				 + "]";
	}
	public boolean isGanhador() {
		return ganhador;
	}
	public void setGanhador(boolean ganhador) {
		this.ganhador = ganhador;
	}
	
	
}
