package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean ganhador;
	private Date tempo;
	private double valor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isGanhador() {
		return ganhador;
	}
	public void setGanhador(boolean ganhador) {
		this.ganhador = ganhador;
	}
	
	public Date getTempo() {
		return tempo;
	}
	public void setTempo(Date tempo) {
		this.tempo = tempo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
