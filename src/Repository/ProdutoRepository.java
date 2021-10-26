package Repository;


import javax.persistence.EntityManager;

import application.JpaUtil;
import modelo.Produto;
import modelo.Usuario;

public class ProdutoRepository extends Repository<Produto> {

	private static EntityManager em = JpaUtil.getEntityManager();
	
	
	public Produto obterUm(int id) {
		Produto produto= em.find(Produto.class,id);
		
		return produto;
	}
	
	  
	
}
