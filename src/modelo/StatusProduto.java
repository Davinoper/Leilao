package modelo;

public enum StatusProduto {
	
	    INSTOCK(1,"Em estoque"),
	    OUTOFSTOCK(2,"Vendido");

	    private String text;
	    private int id;

	    StatusProduto(int id,String text) {
	    	this.text = text;
	    	this.id = id;
		}

	    public String getText() {
	        return text;
	    }
	    
	    public int getId() {
	        return id;
	    }
	}

