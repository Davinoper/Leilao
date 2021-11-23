package Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JpaUtil;
import modelo.RecuperaSenha;

public class RecuperaSenhaRepository extends Repository<RecuperaSenha>{
	
	private static EntityManager em = JpaUtil.getEntityManager();

	public RecuperaSenha findByCodigo(String codigo) {
		
		RecuperaSenha obj = null;
		Query query = em.createQuery("SELECT r FROM RecuperaSenha r WHERE r.codigo = :codigo");
		query.setParameter("codigo", codigo);
		
		obj = (RecuperaSenha) query.getSingleResult();
		
		return obj;
		
	}
}
 