package modelo;

import java.util.ArrayList;
import java.util.List;

public enum Perfil {
	
	ADM(1,"Administrador"),
	COMUM(2,"Comum");
	
	private int id;
	private String label;
	private List<String> paginasComPermissao;
	
	private Perfil(int id, String label) {
		this.id = id;
		this.label = label;
		paginasComPermissao = new ArrayList<String>();
	
	
	paginasComPermissao.add("/Leilao/faces/pages/produtos.xhtml");
	paginasComPermissao.add("/Leilao/faces/pages/meuslances.xhtml");
	paginasComPermissao.add("/Leilao/faces/pages/perfil.xhtml");
	paginasComPermissao.add("/Leilao/faces/pages/listadesejos.xhtml");
	paginasComPermissao.add("/Leilao/faces/pages/pagamento.xhtml");
	paginasComPermissao.add("/Leilao/faces/pages/lance.xhtml");
	paginasComPermissao.add("/Leilao/faces/pages/pagamento.xhtml");
	
	if (id == 1) { 
		paginasComPermissao.add("/Leilao/faces/pages/cadprod.xhtml");
		paginasComPermissao.add("/Leilao/faces/pages/cadempresa.xhtml");
		paginasComPermissao.add("/Leilao/faces/pages/cadusu.xhtml");
		paginasComPermissao.add("/Leilao/faces/pages/cadcategoria.xhtml");
		paginasComPermissao.add("/Leilao/faces/pages/cadempresa.xhtml");
		paginasComPermissao.add("/Leilao/faces/pages/lances.xhtml");
		paginasComPermissao.add("/Leilao/faces/pages/empresalisting.xhtml");
	} 
	
	

	
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public List<String> getPaginasComPermissao() {
		return paginasComPermissao;
	}
	
	public static Perfil valueOf(Integer id) {
		if (id == null)
			return null;
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getId() == id)
				return perfil;
		}
		return null;
	}
}
