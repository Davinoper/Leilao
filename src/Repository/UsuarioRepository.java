package Repository;



import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Usuario;

public class UsuarioRepository extends Repository<Usuario>{
	
	
	private static EntityManager em = JpaUtil.getEntityManager();
	
	public Usuario adicionar(Usuario obj) throws RepositoryException {
		try {
			obj.setDesativo(false);
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
	
	public Usuario desativar(Usuario usuario) {
		Usuario usu = null;
		if(usuario.isDesativo() == false) {
			usuario.setDesativo(true);
		}
		else {
			
			usuario.setDesativo(false);
		}
		
		em.getTransaction().begin();
		usu = em.merge(usuario);
		em.getTransaction().commit();
		
		return usu;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public Usuario findByEmail(String email) throws RepositoryException {
		try { 
			//JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email ");
			query.setParameter("email", email);
			
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usuários.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Usuario findByNome(String nome) throws RepositoryException {
		try { 
			//JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome ");
			query.setParameter("nome", nome);
			
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usuários.");
		}
	}

	


}
