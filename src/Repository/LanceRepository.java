package Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
		Lance lan = null;
			lan  = new Lance();
			lance.setUsuario((Usuario) Session.getInstance().get("usuarioLogado"));
			lance.setTempo(LocalDate.now());
			lance.setProd(produto);
			if(!em.getTransaction().isActive()) { 
		        em.getTransaction().begin();
			}    
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
			if(lances2.get(i).getUsuario().getId() == usu.getId()) {
				lances1.add(lances2.get(i));
			}
			
		}
		
		
		return lances1;
	}
	
	@SuppressWarnings("unchecked")
	public List<Lance> obterLancesGanhadores(int id){
		Query query = em.createQuery("SELECT l FROM Lance l WHERE l.usuario.id = :idusu AND l.ganhador = true AND l.prod.vendido = false");
		query.setParameter("idusu", id);
		
		return (List<Lance>) query.getResultList();
	}
 	
	
	public double obterMaiorLance(Produto produto) {
		Query query = em.createNativeQuery("SELECT MAX(valor) FROM lance WHERE lance.prod_id = :idprod ");
		query.setParameter("idprod", produto.getId());
		
		return (double) query.getSingleResult();
		
	}
	
	
	public void remover(Lance lance) {
		Query query = em.createNativeQuery("DELETE FROM lance WHERE id = :idlance");
		query.setParameter("idlance", lance.getId());
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	

	public void confereGanhador() {
		System.out.println("entrou no metodo teste");
		Query query = em.createQuery("SELECT l FROM Lance l WHERE prod_id = :idprod AND valor = :max");
		List<Lance> listaLances =null;
		try {
			listaLances = obterTodos(Lance.class);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDate dataNow = LocalDate.now();
		
		for(int i=0; i < listaLances.size(); i++) {
			
			if(dataNow.isAfter(listaLances.get(i).getProd().getTempoFim())) {
			
				query.setParameter("idprod", listaLances.get(i).getProd().getId());
				query.setParameter("max",obterMaiorLance(listaLances.get(i).getProd()));
				Lance lance = (Lance) query.getSingleResult();
				System.out.println(lance);
				lance.setGanhador(true);
				em.getTransaction().begin();
				em.merge(lance);
				em.getTransaction().commit();
			}
			
			
			
		    
		}
	
		
		
		
		
	}
	
	
	
	
	
	

}
