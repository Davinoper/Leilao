 package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import Repository.EnderecoRepository;
import Repository.UsuarioRepository;
import application.JpaUtil;
import application.Message;
import application.RepositoryException;
import modelo.Empresa;
import modelo.Estado;
import modelo.Perfil;
import modelo.Usuario;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6774623335617419574L;
	private List<Usuario> listaUsu;
	private Perfil[] listaPerfil;


	public void limpar() {
		setEntity(null);
		listaUsu = null;
	}

	public void editar(int id) {
		EntityManager em = JpaUtil.getEntityManager();
		setEntity(em.find(Usuario.class, id));
	}

	public Perfil[] getListaPerfil() {
		if(listaPerfil == null) {
			listaPerfil = Perfil.values();
		}
		return listaPerfil;
		
		
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
	
	public void adicionar() {
		UsuarioRepository repo = new UsuarioRepository();
		EnderecoRepository repo2 = new EnderecoRepository();
		try {
			if(getEntity().getPerfil() != Perfil.ADM) {
				getEntity().setPerfil(Perfil.COMUM);
			}
			Usuario usu =repo.adicionar(getEntity());
			repo2.adicionarEnd(usu.getEndereco().getId(),getEntity().getEndereco());
			limpar();
			Message.addInfoMessage("Salvo com sucesso.");
		} catch (RepositoryException e) {
			Message.addErrorMessage("Problemas ao executar a função!");
		}
		
	}

	@Override
	public Usuario getEntity() {
		if(entity == null) {
			entity = new Usuario();
		}
		return entity;
	}
	
	public void desativar() {
		UsuarioRepository repo = new UsuarioRepository();
		Usuario usu =repo.desativar(getEntity());
		if(usu.isDesativo() == true) {
			Message.addInfoMessage("Usuario desativado com sucesso.");
		}
		else if(usu.isDesativo() == false) {
			Message.addInfoMessage("Usuario reativado com sucesso");
		}
		limpar();
		
	
		
	}

}
