package controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.CartaoRepository;
import Repository.FormaPagamentoRepository;
import Repository.LanceRepository;
import Repository.PagamentoRepository;
import Repository.ProdutoRepository;
import Repository.UsuarioRepository;
import application.Message;
import application.RepositoryException;
import application.Session;
import modelo.BandeiraCartao;
import modelo.Cartao;
import modelo.FormaPagamento;
import modelo.Lance;
import modelo.Pagamento;
import modelo.Produto;
import modelo.Usuario;

@Named
@ViewScoped
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
	
	public void removeCard(Cartao cartao) {
		FormaPagamentoRepository repoForma = new FormaPagamentoRepository();
		UsuarioRepository repo = new UsuarioRepository();
		Usuario usuario =(Usuario) Session.getInstance().get("usuarioLogado");
		try {
			Usuario usu = repo.findByNome(usuario.getNome());
			usu.getListaFormasPagamento().remove(cartao);
			repo.altera(usu);
			repoForma.remove(cartao);
			Message.addInfoMessage("Cartão removido!");
		} catch (RepositoryException e) {
			Message.addInfoMessage("Problemas ao remover o cartão.");
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
			UsuarioRepository repo = new UsuarioRepository();
			Usuario usuario =(Usuario) Session.getInstance().get("usuarioLogado");
			Usuario usu;
			try {
				usu = repo.findByNome(usuario.getNome());
				setListaCartao(usu.getListaFormasPagamento());
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

	
	public void finalizarPedido() {
		ProdutoRepository repoProd = new ProdutoRepository();
		PagamentoRepository repo = new PagamentoRepository();
		if(getValorTot() != 0) {
			if(getEntity().getNumero() == null) {
				Message.addErrorMessage("Selecione um cartão para finalizar a compra.");
			}
			else {
				Pagamento pag = new Pagamento();
				pag.setData(LocalDateTime.now());
				pag.setValor(getValorTot());
				pag.setUsu((Usuario) Session.getInstance().get("usuarioLogado"));
				try {
					repo.adicionar(pag);
					List<Lance> lista = getListaProduto();
					for(int i =0; i < lista.size(); i++) {
						lista.get(i).getProd().setDesativo(true);
						lista.get(i).getProd().setVendido(true);
						repoProd.altera(lista.get(i).getProd());

					}
					
					Message.addInfoMessage("Compra realizada com sucesso.");
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else {
			Message.addErrorMessage("Você não tem nada no carrinho.");
		}
	}



	public List<Lance> getListaProduto() {
		if(listaProduto == null) {
			
			LanceRepository repo = new LanceRepository();
			Usuario usu = (Usuario) Session.getInstance().get("usuarioLogado");
			setListaProduto(repo.obterLancesGanhadores(usu.getId()));
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
