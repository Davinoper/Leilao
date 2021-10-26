package Repository;

import javax.persistence.EntityManager;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Endereco;

public class EnderecoRepository extends Repository<Endereco>{
	

	
	public Endereco adicionarEnd(Integer id,Endereco end) throws RepositoryException {
		try {
			Endereco e = em.find(Endereco.class, id);
			Integer index = e.getId();
			e = end;
			e.setId(index);
			em.getTransaction().begin();
			altera(e);
			em.getTransaction().commit();
			return e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao adicionar!");
		}
	}
	
	
	private static EntityManager em = JpaUtil.getEntityManager();
}
