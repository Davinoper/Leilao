package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import Repository.LanceRepository;
import application.Message;
import application.Session;
import modelo.Lance;
import modelo.Usuario;

@Named
@RequestScoped
public class MeusLancesController extends Controller<Lance> implements Serializable{

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
	
	public void cancelar(Lance lance) {
		LanceRepository repo = new LanceRepository();
		repo.cancelar(lance);
		Message.addInfoMessage("Lance Cancelado.");
	}

	public List<Lance> getListaLances() {
		LanceRepository repo = new LanceRepository();
		Usuario usu = (Usuario) Session.getInstance().get("usuarioLogado");
		if(listaLances == null) {
			listaLances = repo.obterLancesUsu(usu.getId());
		}
		System.out.println(usu);
		return listaLances;
	}

	public void setListaLances(List<Lance> listaLances) {
		this.listaLances = listaLances;
	}

}
