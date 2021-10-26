package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Session;
import modelo.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6910075204821943982L;
	private Usuario usuarioLogado;
	
			
	public String encerrarSessao() {
		Session.getInstance().invalidateSession();
		return "/faces/login.xhtml?faces-redirect=true";
	}

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		}
		return usuarioLogado;
	}		
			
			
			
			

	public void setUsuarioLogado(Usuario usuariologado) {
		this.usuarioLogado = usuariologado;
	}
	
	
	
}
