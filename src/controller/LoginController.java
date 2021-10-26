package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import Repository.UsuarioRepository;
import application.Message;
import application.Session;
import modelo.Usuario;

@Named 
@RequestScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6983839857205389929L;
	private Usuario usu;
	private UsuarioRepository repo;
	
	
	public String logar() {
		repo = new UsuarioRepository();
		Usuario usuarioLogado = repo.validaLogin(usu);
		if(usuarioLogado != null) {
			Session.getInstance().set("usuarioLogado", usuarioLogado);
			return "/faces/template.xhtml?faces-redirect=true";
			
		}
		limpar();
		Message.addErrorMessage("Senha ou usuario invalidos");
		return null;
		
		
	}
	
	public void limpar() {
		setUsu(null);
	}


	public Usuario getUsu() {
		if(usu == null) {
			usu = new Usuario();
		}
		return usu;
	}


	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	
	
	
		
}
