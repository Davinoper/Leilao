package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.LanceRepository;
import application.RepositoryException;
import modelo.Lance;

@Named
@ViewScoped
public class LancesController extends Controller<Lance> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7395420041693680827L;
	private List<Lance> listaLances;
	
	
	
	
	
	
	@Override
	public Lance getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
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

}
