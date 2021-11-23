package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.ProdutoRepository;
import Repository.UsuarioRepository;
import application.Message;
import application.Session;
import modelo.Produto;
import modelo.Usuario;

@Named
@ViewScoped
public class DesejosController extends Controller<Usuario> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3219101282094400713L;
	private List<Produto> listaProduto;

	public String enviarProduto(Produto p) {
		ProdutoRepository repoProd = new ProdutoRepository();
		Produto prod = repoProd.obterUm(p.getId());

		Session session = Session.getInstance();
		session.set("prodTemp",prod);

		return "/faces/pages/lance.xhtml?faces-redirect=true";
	}
	
	
	public List<Produto> getListaProduto(){
		setListaProduto(getEntity().getListaProduto());
		
		
		return listaProduto;
	}
	
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
		
	}
	
	public void removeLista(Produto produto) {
		UsuarioRepository repo = new UsuarioRepository();
		getEntity().getListaProduto().remove(produto);
		repo.altera(getEntity());
		
		Message.addInfoMessage("Produto removido com sucesso");
		
	}
	
	
	@Override
	public Usuario getEntity() {
		if(entity == null) {
			setEntity((Usuario) Session.getInstance().get("usuarioLogado"));
		}
		return entity;
	}

	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
	}

}
