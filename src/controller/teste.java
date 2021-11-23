package controller;

import Repository.ProdutoRepository;
import Repository.UsuarioRepository;
import application.Message;
import application.RepositoryException;
import application.Session;
import modelo.Produto;
import modelo.Usuario;

public class teste {

	public static void main(String[] args) {
		ProdutoRepository repoP = new ProdutoRepository();
		Usuario usu = (Usuario) Session.getInstance().get("usuarioLogado");
		Produto prod = null;
		try {
			prod = repoP.findById(21);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UsuarioRepository repo = new UsuarioRepository();
		if(!usu.getListaProduto().contains(prod)) {
			usu.getListaProduto().add(prod);
			repo.altera(usu);
			System.out.println("Adicionado a lista de desejos!");
		}
		else {
			System.out.println("Este item ja foi adicionado.");
		}
	}

}
