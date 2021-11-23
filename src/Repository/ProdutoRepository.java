package Repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JpaUtil;
import modelo.Produto;

public class ProdutoRepository extends Repository<Produto> {

	private static EntityManager em = JpaUtil.getEntityManager();
	
	
	public Produto obterUm(int id) {
		Produto produto= em.find(Produto.class,id);
		
		return produto;
	}
	
	public Produto desativar(Produto produto) {
		Produto prod = null;
		if(produto.isDesativo() == false) {
			produto.setDesativo(true);
		}
		else {
			
			produto.setDesativo(false);
		}
		
		em.getTransaction().begin();
		prod = em.merge(produto);
		em.getTransaction().commit();
		
		return prod;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> findByNome(String nome){
		
		Query query = em.createQuery("SELECT p FROM Produto p WHERE p.nome LIKE '" + nome +"%'");
		return (List<Produto>) query.getResultList();
		
		
		
	}
	
	
	
}
