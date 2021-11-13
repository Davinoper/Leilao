package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.FormaPagamentoRepository;
import application.RepositoryException;
import modelo.BandeiraCartao;
import modelo.Cartao;
import modelo.FormaPagamento;

@Named
@ViewScoped
public class CartaoController extends Controller<Cartao> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7240382739549849105L;
	private BandeiraCartao[] listaBandeira;
	private List<FormaPagamento> listaCartao;
	
	
	public BandeiraCartao[] getListaBandeira() {
		if(listaBandeira == null) {
			listaBandeira = BandeiraCartao.values();
			
		}
		return listaBandeira;
		
		
	}
	
	
	
	
	
	@Override
	public Cartao getEntity() {
		if(entity == null) {
			setEntity(new Cartao());
		}
		return entity;
	}

	@Override
	public void limpar() {
		setEntity(null);
		
	}






	public List<FormaPagamento> getListaCartao() {
		FormaPagamentoRepository repo = new FormaPagamentoRepository();
		if(listaCartao == null) {
			try {
				setListaCartao(repo.obterTodos(FormaPagamento.class));
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaCartao;
	}




	public void setListaCartao(List<FormaPagamento> list) {
		this.listaCartao = list;
	}

}
