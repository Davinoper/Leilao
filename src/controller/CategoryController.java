package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.CategoryRepository;
import application.RepositoryException;
import modelo.Category;
import modelo.Estado;

@Named
@ViewScoped
public class CategoryController extends Controller<Category> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8012501110321747325L;
	private List<Category> listacategory;

	
	

	
	@Override
	public Category getEntity() {
		if(entity == null) {
			setEntity(new Category());
		}
		return entity;
	}

	@Override
	public void limpar() {
		setEntity(null);
		
	}

	public List<Category> getListacategory() {
		CategoryRepository repo = new CategoryRepository();
		if(listacategory == null) {
			try {
				setListacategory(repo.obterTodos(Category.class));
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listacategory;
	}

	public void setListacategory(List<Category> listacategory) {
		this.listacategory = listacategory;
	}
	
	
	  public List<Category> complete(String query) {
		  List<Category> lista= null;
	        CategoryRepository repo = new CategoryRepository();
	        try {
				lista= repo.findByNome(query);
				return lista;
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return lista;
	    }

}
