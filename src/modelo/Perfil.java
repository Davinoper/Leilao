package modelo;

public enum Perfil {
	
	ADM(1,"Administrador"),
	COMUM(2,"Comum");
	
	private int id;
	private String label;
	
	private Perfil(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	
}
