package controller;

import java.io.Serializable;
import java.util.ArrayList;

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
	LanceRepository repo = new LanceRepository();
	private Usuario usu ;
	
	
	
	public String enviarProduto(Produto p) {
		ProdutoRepository repoProd = new ProdutoRepository();
		Produto prod = repoProd.obterUm(p.getId());

		Session session = Session.getInstance();
		session.set("prodTemp",prod);

		return "/faces/pages/lance.xhtml?faces-redirect=true";
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
		UsuarioRepository repo = new UsuarioRepository();
		getUsu().getListaProduto().add(prod);
		try {
			repo.adicionar(getUsu());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message.addInfoMessage("Adicionado a lista de desejos!");
		
	}
	
	
	
	public Usuario getUsu() {
		if(usu == null) {
			setUsu((Usuario) Session.getInstance().get("usuarioLogado"));
		}
	
		if(usu.getListaProduto() == null) {
			usu.setListaProduto(new ArrayList<Produto>());
		}
		
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
	
	

}
