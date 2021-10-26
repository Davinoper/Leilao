package Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import application.JpaUtil;
import application.RepositoryException;
import application.Session;
import modelo.Lance;
import modelo.Produto;
import modelo.Usuario;

public class LanceRepository extends Repository<Lance>{

	private static EntityManager em = JpaUtil.getEntityManager();
	
	
	public Lance obterUm(int id) {
		Lance lance= em.find(Lance.class,id);
		
		return lance;
	}
	
	
	public Lance adicionar(Lance lance,Produto produto) {
		Lance lan = new Lance();
		lance.setUsuario((Usuario) Session.getInstance().get("usuarioLogado"));
		lance.setTempo(LocalDate.now());
		lance.setProd(produto);
		em.getTransaction().begin();
		lan = em.merge(lance);
		em.getTransaction().commit();
		
		return lan;
	}
	
	public List<Lance> obterLancesUsu(int id) {
		LanceRepository repo = new LanceRepository();
		List<Lance> lances1 = new ArrayList<Lance>();
		List<Lance> lances2 = null;
		Usuario usu = em.find(Usuario.class,id);
		
		try {
			lances2 = repo.obterTodos(Lance.class);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(int i =0; i < lances2.size(); i++) {
			if(lances2.get(i).getUsuario().getId() == usu.getId() && lances2.get(i).isCancelado() == false) {
				lances1.add(lances2.get(i));
			}
			
		}
		
		
		
		return lances1;
	}
	
	public void cancelar(Lance lance) {
		em.getTransaction().begin();
		lance.setCancelado(true);
		em.merge(lance);
		em.getTransaction().commit();
		
	}

}
