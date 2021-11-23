package modelo;

public enum BandeiraCartao {

	VISA(1),
	MARTERCARD(2),
	CIELO(3),
	AMERICANEXPRESS(4);
	
	private int id;
	
	

	private BandeiraCartao(int id) {
		this.id = id;
	}



	public int getId() {
		return id;
	}
	
	
	
	
	
}
