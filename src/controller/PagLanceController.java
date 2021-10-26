package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.LanceRepository;
import application.Message;
import application.RepositoryException;
import application.Session;
import modelo.Lance;
import modelo.Produto;

@Named
@ViewScoped
public class PagLanceController extends Controller<Lance> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3715769704159182318L;
	Produto produto;
	LanceRepository repo = new LanceRepository();
	List<Lance> listalance;
	
	public PagLanceController() {
		Session session = Session.getInstance();
		setProduto((Produto) session.get("prodTemp"));
		

	}
	
	
	
	public void adicionar() {
		
			repo.adicionar(getEntity(),produto);
			limpar();
			Message.addInfoMessage("Lance registrado");
		
		
	}


	public Produto getProduto() {
		if(produto == null) {
			setProduto(new Produto());
		}
		return produto;
		
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}



	@Override
	public Lance getEntity() {
		if(entity == null) {
			setEntity(new Lance());
		}
		return entity;
	}



	@Override
	public void limpar() {
		setEntity(null);
		
	}



	public List<Lance> getListalance() {
		if(listalance == null) {
			try {
				setListalance(repo.obterTodos(Lance.class));
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listalance;
	}



	public void setListalance(List<Lance> listalance) {
		this.listalance = listalance;
	}
	
	
	
	
	
	
	
	
	
}
