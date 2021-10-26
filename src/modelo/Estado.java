package modelo;

public enum Estado {

            AC(1,"acre"),
 
            AL(2,"Alagoas"),
         
            AP(3,"Amapá"),
           
            AM(4,"Amazonas"),
           
            BA(5,"Bahia"),
           
            CE(6,"Ceará"),
           
            DF(7,"Distrito Federal"),
            
            ES(8,"Espirito Santo"),
            
            GO(9,"Goiás"),
       
            MA(10,"Maranhão"),
           
            MT(11,"Mato Grosso"),
            
            MS(12,"Mato Grosso do Sul"),
           
            MG(13,"Minas Gerais"),
           
            PA(14,"Pará"),
          
            PB(15,"Paraiba"),
         
            PR(16,"Paraná"),

            PE(17,"Pernambuco"),
          
            PI(18,"Piauí"),
         
            RJ(19,"Rio de Janeiro"),
            
            RN(20,"Rio Grande do Norte"),
          
            RS(21,"Rio Grande do Sul"),
           
            RO(22,"Rondônia"),
        
            RR(23,"Roraima"),
          
            SC(24,"Santa Catarina"),
           
            SP(25,"São Paulo"),
          
            SE(26,"Sergipe"),
           
            TO(27,"Tocantis");
            
    private int id;
	private String label;
	
	private Estado(int id, String label) {
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
