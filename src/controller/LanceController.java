package controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import Repository.LanceRepository;
import Repository.ProdutoRepository;
import Repository.UsuarioRepository;
import application.Message;
import application.RepositoryException;
import application.Session;
import modelo.Lance;
import modelo.Produto;
import modelo.Usuario;
 

@Named
@RequestScoped
public class LanceController extends Controller<Lance> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5204171013384794530L;
	private Produto produto;
	private String filtro;
	private List<Produto> listaprod;

	
	public String enviarProduto(Produto p) {
		
		ProdutoRepository repoProd = new ProdutoRepository();
		Produto prod = repoProd.obterUm(p.getId());
		Session session = Session.getInstance();
		session.set("prodTemp",prod);
		
		
		return "/faces/pages/lance.xhtml?faces-redirect=true";
	}
	
	
	public void pesquisar(){
		ProdutoRepository repo = new ProdutoRepository();
		if(filtro.isEmpty()) {
			setListaprod(repo.obterAtivos());
		}
		else {
			setListaprod(repo.findByNome(filtro));
			limpar();
		}
		
		
	}
	
	public double pegaValorMaxProd(Produto prod) {
		LanceRepository repo = new LanceRepository();
		double valor =0;
		try {
			valor =repo.obterMaiorLance(prod);
		} catch (Exception e) {
			valor = prod.getValor();
			e.printStackTrace();
		}
		return valor;
	}
	
	
	public void desativaPassado() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			List<Produto> lista =repo.obterTodos(Produto.class);
			for(int i=0; i < lista.size() ;i++) {
				if(LocalDate.now().isAfter(lista.get(i).getTempoFim())) {
					lista.get(i).setDesativo(true);
					repo.altera(lista.get(i));
				}
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		setProduto(produto);
		
	}



	public Produto getProduto() {
		if(produto == null) {
			setProduto(new Produto());
			System.out.println("produto adicionada");
		}
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	public void addOnfav(Produto prod) {
		Usuario usu = (Usuario) Session.getInstance().get("usuarioLogado");
		UsuarioRepository repo = new UsuarioRepository();
		if(!usu.getListaProduto().contains(prod)) {
			usu.getListaProduto().add(prod);
			repo.altera(usu);
			Message.addInfoMessage("Adicionado a lista de desejos!");
		}
		else {
			Message.addInfoMessage("Este item ja foi adicionado.");
		}
		
		
	}
	
	


	public List<Produto> getListaprod() {
		LanceRepository repoBid = new LanceRepository();
		ProdutoRepository repo = new ProdutoRepository();
		repoBid.confereGanhador();
		desativaPassado();
		if (listaprod == null) {
			listaprod = repo.obterAtivos();
			
			
		}
		return listaprod;
	}





	public void setListaprod(List<Produto> listaprod) {
		this.listaprod = listaprod;
	}


	public String getFiltro() {
		return filtro;
	}


	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	

}
