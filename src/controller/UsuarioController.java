package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import Repository.UsuarioRepository;
import application.JpaUtil;
import application.Message;
import application.RepositoryException;
import modelo.Usuario;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6774623335617419574L;
	private List<Usuario> listaUsu;


	public void limpar() {
		setEntity(null);
		listaUsu = null;
	}

	public void editar(int id) {
		EntityManager em = JpaUtil.getEntityManager();
		setEntity(em.find(Usuario.class, id));
	}


	public List<Usuario> getListaUsu() {
		if (listaUsu == null) {
			UsuarioRepository repo = new UsuarioRepository();
			try {
				listaUsu = repo.obterTodos(Usuario.class);
			} catch (RepositoryException e) {
				listaUsu = new ArrayList<Usuario>();
				
			}
		}
		return listaUsu;
	}

	public void setListaUsu(List<Usuario> listaUsu) {
		this.listaUsu = listaUsu;
	}

	@Override
	public Usuario getEntity() {
		if(entity == null) {
			entity = new Usuario();
		}
		return entity;
	}

}
