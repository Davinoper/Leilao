package controller;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.RecuperaSenhaRepository;
import Repository.UsuarioRepository;
import application.Message;
import application.RepositoryException;
import modelo.RecuperaSenha;
import modelo.Usuario;



@Named
@ViewScoped
public class RecuperaSenhaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8016964725466808560L;
	private String codigo;
	private String senha;
	
	
	public void alterarSenha() {
		UsuarioRepository repoUsu = new UsuarioRepository();
		RecuperaSenhaRepository repo = new RecuperaSenhaRepository();
		
		RecuperaSenha obj = null;
		try {
			obj = repo.findByCodigo(getCodigo());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(obj == null || obj.getDataLimite().isBefore(LocalDateTime.now()) || obj.getUtilizado() == true) {
			
			Message.addErrorMessage("Esse código é invalido ou expirou.");
			limpar();
		}
		else {
			Usuario usu = obj.getUsuario();
			usu.setSenha(getSenha());
			obj.setUtilizado(true);
			try {
				repoUsu.adicionar(usu);
				repo.adicionar(obj);
				Message.addInfoMessage("Senha alterada com sucesso.");
			} catch (RepositoryException e) {
				Message.addErrorMessage("Problemas ao alterar senha.");
				e.printStackTrace();
			}
			limpar();
		}
		
		
		
	}

	public void limpar() {
		setSenha(null);
		setCodigo(null);
	}

	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
	
	

}
