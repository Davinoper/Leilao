package controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.RecuperaSenhaRepository;
import Repository.UsuarioRepository;
import application.Email;
import application.Message;
import application.RepositoryException;
import modelo.RecuperaSenha;
import modelo.Usuario;

@Named
@ViewScoped
public class EsqueceuSenhaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4692214072746885801L;

private String email;
	
	public void enviarEmail() {
		
		UsuarioRepository repo = new UsuarioRepository(); 
		Usuario usuario = null;
		try {
			usuario = repo.findByEmail(email);
		} catch (RepositoryException e) {
			Message.addErrorMessage("Email não encontrado.");
			return;
		}
		// gerando codigo aleatorio
		Random r = new Random();
		DecimalFormat format = new DecimalFormat("BC-000000");
		String codigo = format.format(r.nextInt(1000000));
		
		RecuperaSenha entity = new RecuperaSenha();
		entity.setCodigo(codigo);
		entity.setUsuario(usuario);
		entity.setUtilizado(false);
		// definindo 24 horas de tempo limite
		entity.setDataLimite(LocalDateTime.now().plusDays(1));
		
		RecuperaSenhaRepository repoRecuperar = new RecuperaSenhaRepository();
		try {
			repoRecuperar.adicionar(entity);
			Email email = new Email(usuario.getEmail(), 
									"Esqueceu a Senha",
									codigo+" é seu código de recuperação de senha.");
			email.enviar();
			Message.addInfoMessage("O código foi enviado para o seu email.");
		} catch (RepositoryException e) {
			e.printStackTrace();
			Message.addErrorMessage("Falha ao enviar o código.");
		}
		
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
