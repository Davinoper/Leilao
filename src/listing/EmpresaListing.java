package listing;

import java.util.ArrayList;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.EmpresaRepository;
import application.RepositoryException;
import modelo.Empresa;

@Named
@ViewScoped
public class EmpresaListing extends Listing<Empresa>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7196229739008081964L;
	private String filtro;
	
	public EmpresaListing() {
		super("empresalisting", new EmpresaRepository());
	}
	

	
	
	@Override
	public void pesquisar() {
		
		EmpresaRepository repo = new EmpresaRepository();
		try {
			if(filtro.isEmpty()) {
				setList(repo.obterTodos(Empresa.class));
			}
			else {
				setList(repo.obterPorNome(filtro));
			}
			
		} catch (Exception e) {
			setList(new ArrayList<Empresa>());
		}
		
	}
	
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	

}
