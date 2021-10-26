package Repository;


import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JpaUtil;
import application.Message;
import application.RepositoryException;



public class Repository<T> {
	
	
	
	private EntityManager em = null;
	
	public Repository() {
		
		em =JpaUtil.getEntityManager();
	}
	
	public T adicionar(T obj) throws RepositoryException {
		try {
			em.getTransaction().begin();
			T t = em.merge(obj);
			em.getTransaction().commit();
			System.out.println(t.toString());
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao adicionar!");
		}
	}
	
	public T remove(T obj) throws RepositoryException {
		try {
			em.getTransaction().begin();
			T t = em.merge(obj);
			em.remove(t);
			em.getTransaction().commit();
			return t;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RepositoryException("Erro ao remover!");
		}
		
	}
	
	public  T altera(T obj) {
		try {
			
			em.getTransaction().begin();
			T objeto = em.merge(obj);
			em.getTransaction().commit();
			
			return objeto;
		} catch (Exception e) {
			Message.addErrorMessage("Problemas ao executar a fun��o!");
			e.printStackTrace();
			return null;
		}
		
	}

	
	
	public List<T> obterTodos(Class clazz) throws RepositoryException {
		try { 
			String entName = clazz.getSimpleName();
	
			Query query = em.createQuery("SELECT u FROM "+entName+" u");
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o getAll");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Consultar no banco.");
		}

	}
	
	public T findById(int id) throws RepositoryException {
		try {
			// obtendo o tipo da classe de forma generica (a classe deve ser publica)
			final ParameterizedType type = 	(ParameterizedType) getClass().getGenericSuperclass();
			Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];
			
			T t = (T) getEntityManager().find(tClass, id);
			return t;
		} catch (Exception e) {
			System.out.println("Erro ao executar o m�todo find do Repository");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os dados");
		}
	}
	
	
	@SuppressWarnings("unused")
	private EntityManager getEntityManager() {
		return em;
	}
	
	
	
	
}
