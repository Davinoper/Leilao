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
	private Produto produto;
	private double valorMaxProd;
	private LanceRepository repo = new LanceRepository();
	private List<Lance> listalance;
	
	public PagLanceController() {
		Session session = Session.getInstance();
		setProduto((Produto) session.get("prodTemp"));
		

	}
	
	
	
	public void adicionar() {
		    double valor;
			try {
				valor = repo.obterMaiorLance(produto);
			} catch (Exception e) {
				valor =0;
				e.printStackTrace();
			}
			if(getEntity().getValor() > valor) {
				repo.adicionar(getEntity(),produto);
				Message.addInfoMessage("Lance registrado");
				limpar();
			}
			else {
				Message.addErrorMessage("O valor do lance deve ser maior que " + valor);
				limpar();
			}
			
			
		
		
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



	public double getValorMaxProd() {
		double valor =0;
		try {
			valor =repo.obterMaiorLance(getProduto());
		} catch (Exception e) {
			valor = getProduto().getValor();
			e.printStackTrace();
		}
		return valor;
	}



	public void setValorMaxProd(double valorMaxProd) {
		this.valorMaxProd = valorMaxProd;
	}
	
	
	
	
	
	
	
	
	
}
