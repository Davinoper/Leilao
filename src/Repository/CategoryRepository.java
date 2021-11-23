package Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Category;

public class CategoryRepository extends Repository<Category>{

	private static EntityManager em = JpaUtil.getEntityManager();

	@SuppressWarnings("unchecked")
	public List<Category> findByNome(String nome) throws RepositoryException {
		try { 
			//JPQL ou SQL
			Query query = em.createQuery("SELECT c FROM Category c WHERE upper(c.categoria) LIKE upper(:nome)");
			query.setParameter("nome",nome) ;
			
			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar categorias.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
