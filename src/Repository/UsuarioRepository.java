package Repository;

import javax.persistence.EntityManager;

import application.JpaUtil;
import application.Message;
import modelo.Usuario;

public class UsuarioRepository extends Repository<Usuario> {
	
	private static EntityManager em = JpaUtil.getEntityManager();
	
	public static Usuario obterUm(int id) {
		Usuario usu = em.find(Usuario.class,id);
		
		return usu;
	}
	
}
