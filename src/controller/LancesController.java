package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.LanceRepository;
import Repository.UsuarioRepository;
import application.RepositoryException;
import modelo.Lance;
import modelo.Usuario;

@Named
@ViewScoped
public class LancesController extends Controller<Lance> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7395420041693680827L;
	private List<Lance> listaLances;
	private String filtro;
	
	
	
	public void pesquisar(){
		LanceRepository repoBid = new LanceRepository();
		UsuarioRepository repoUsu = new UsuarioRepository();
		try {
			if(filtro.isEmpty()) {
				setListaLances(repoBid.obterTodos(Lance.class));
			}
			else {
				Usuario usu = repoUsu.findByNome(filtro);
				setListaLances(repoBid.obterLancesUsu(usu.getId()));
				limpar();
			}
			
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	
	@Override
	public Lance getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limpar() {
		setFiltro(null);
		
	}

	public List<Lance> getListaLances(){
		LanceRepository repo = new LanceRepository();
		if(listaLances == null) {
			try {
				listaLances = repo.obterTodos(Lance.class);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaLances;
	}

	public void setListaLances(List<Lance> listaLances) {
		this.listaLances = listaLances;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
