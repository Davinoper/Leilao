package modelo;

public enum Categoria {

	ELETRONICO(1,"Eletr�nico"),
	CASA(2,"Casa"),
	VEICULO(3,"Ve�culo"),
	INFORMATICA(4,"Inform�tica"),
	LIVRO(5,"Livro"),
	ANIMAl(6,"Animal"),
	JOGO(7,"Jogo"),
	MUSICA(8,"M�sica"),
	INFANTIL(9,"Infantil");
	
	

	private int id;
	private String label;
	
	private Categoria(int id, String label) {
		this.id =id;
		this.label =label;
		
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	
}
