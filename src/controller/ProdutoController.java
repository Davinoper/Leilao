package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import Repository.EmpresaRepository;
import Repository.ProdutoRepository;
import application.JpaUtil;
import application.RepositoryException;
import modelo.Categoria;
import modelo.Empresa;
import modelo.Produto;
import modelo.StatusProduto;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5283872523256589679L;
	private List<Produto> listaProd;
	private Categoria listaCategoria[];
	private List<Empresa> listaEmpresa;


	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
		}
		return entity;
	}

	@Override
	public void limpar() {
		setEntity(null);

	}

	public String aviso(Produto product) {
		if (product.getStatus() == StatusProduto.INSTOCK) {
			return "success";
		} else {
			return "danger";
		}

	}


	public Categoria[] getListaCategoria() {
		if (listaCategoria == null) {
			listaCategoria = Categoria.values();
		}
		return listaCategoria;
	}

	public void editar(int id) {
		EntityManager em = JpaUtil.getEntityManager();
		setEntity(em.find(Produto.class, id));
	}

	public List<Produto> getListaProd() {
		ProdutoRepository repo = new ProdutoRepository();
		if (listaProd == null) {
			try {
				listaProd = repo.obterTodos(Produto.class);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return listaProd;
	}

	public void setListaProd(List<Produto> listaProd) {
		this.listaProd = listaProd;
	}

	public List<Empresa> getListaEmpresa() {
		EmpresaRepository repo = new EmpresaRepository();
		if(listaEmpresa == null) {
			try {
				listaEmpresa= repo.obterTodos(Empresa.class);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaEmpresa;
	}

}
