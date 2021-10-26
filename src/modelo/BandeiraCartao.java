package modelo;

public enum BandeiraCartao {

	VISA(1),
	MARTERCARD(2);
	
	private int id;
	
	

	private BandeiraCartao(int id) {
		this.id = id;
	}



	public int getId() {
		return id;
	}
	
	
	
	
	
}
