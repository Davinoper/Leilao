package modelo;

public enum TipoChave {

	CPF(1),
	ALEATORIA(2);
	
		private int id;

		private TipoChave(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
		
		
}
