package Repository;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Produto;
import modelo.Usuario;

public class UsuarioRepository extends Repository<Usuario>{
	
	
	private static EntityManager em = JpaUtil.getEntityManager();
	
	public Usuario adicionar(Usuario obj) throws RepositoryException {
		try {
			obj.setSenha(JpaUtil.hash(obj.getSenha()));
			em.getTransaction().begin();
			Usuario t = em.merge(obj);
			em.getTransaction().commit();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao adicionar!");
		}
	}
	
	public Usuario obterUm(int id) {
		Usuario usu = em.find(Usuario.class,id);
		
		return usu;
	}
	
	public Usuario validaLogin(Usuario usu) {
		
		Usuario usuarioLogado = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT u ");
		sql.append("FROM ");
		sql.append(Usuario.class.getName() + " u");
		sql.append(" WHERE ");
		sql.append("  u.email = :email");
		sql.append("  AND u.senha = :senha");
		
		usuarioLogado = (Usuario) em.createQuery(sql.toString(),Usuario.class).setParameter("email", usu.getEmail())
		.setParameter("senha", JpaUtil.hash(usu.getSenha())).getSingleResult();
		
		
		if(usuarioLogado == null) {
			return null;
		}
		
		return usuarioLogado;
		
		

	}



}
