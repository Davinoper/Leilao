package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.codec.digest.DigestUtils;

public class JpaUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Leilao");
	
	private JpaUtil() {	}
	
	public static String hash(String valor) {
		return DigestUtils.sha256Hex(valor);
		
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
