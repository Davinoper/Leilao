package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import Repository.CartaoRepository;
import Repository.FormaPagamentoRepository;
import Repository.LanceRepository;
import Repository.UsuarioRepository;
import application.Message;
import application.RepositoryException;
import application.Session;
import modelo.BandeiraCartao;
import modelo.Cartao;
import modelo.FormaPagamento;
import modelo.Lance;
import modelo.Produto;
import modelo.Usuario;

@Named
@RequestScoped
public class CartaoController extends Controller<Cartao> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7240382739549849105L;
	private BandeiraCartao[] listaBandeira;
	private List<FormaPagamento> listaCartao;
	private List<Lance> listaProduto;
	private double valorTot;
	
	
	
	
	public BandeiraCartao[] getListaBandeira() {
		if(listaBandeira == null) {
			listaBandeira = BandeiraCartao.values();
			
		}
		return listaBandeira;
		
		
	}
	
	
	public void adicionarCard() {
		FormaPagamentoRepository repoForma = new FormaPagamentoRepository();
		CartaoRepository repoCard = new CartaoRepository();
		UsuarioRepository repo = new UsuarioRepository();
		Usuario usu =(Usuario) Session.getInstance().get("usuarioLogado");
		
	    try {
	    	repoForma.adicionar(getEntity());
	    	usu.getListaFormasPagamento().add(repoCard.obterPorNumero(getEntity().getNumero()));
	    	repo.altera(usu);
			limpar();
			Message.addInfoMessage("Cartão adicionado com sucesso.");
		} catch (RepositoryException e) {
			Message.addErrorMessage("Problemas ao adicionar cartão.");
			e.printStackTrace();
		}
		
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
		if(listaCartao == null) {
			Usuario usu =(Usuario) Session.getInstance().get("usuarioLogado");
			setListaCartao(usu.getListaFormasPagamento());
		}
		return listaCartao;
	}




	public void setListaCartao(List<FormaPagamento> list) {
		this.listaCartao = list;
	}





	public List<Lance> getListaProduto() {
		if(listaProduto == null) {
		
			LanceRepository repo = new LanceRepository();
			Usuario usu = (Usuario) Session.getInstance().get("usuarioLogado");
			setListaProduto(repo.obterLancesUsu(usu.getId()));
			
			for(int i=0; i < getListaProduto().size();i++) {
				if(listaProduto.get(i).isGanhador() == false) {
					listaProduto.remove(i);
				}
			}
		
		
				
		}
		return listaProduto;
	}

	public void removeCarrinho(Lance lance) {
		listaProduto.remove(lance);
		Message.addInfoMessage("Item removido com sucesso");
		getValorTot();
		
	}
	
	
	public void selecionar(Cartao cartao) {
		
		setEntity(cartao);
		Message.addInfoMessage("Cartão selecionado.");
		System.out.println(getEntity().getNome());
		
	}
	
	public double getValorTot() {
		double valor = 0;
		for(int i=0; i < listaProduto.size();i++) {
			valor += listaProduto.get(i).getValor();
		}
		valorTot = valor;
		
		return valorTot;
	}



	public void setListaProduto(List<Lance> listaproduto) {
		this.listaProduto = listaproduto;
	}




}
