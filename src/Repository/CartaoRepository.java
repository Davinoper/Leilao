package Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Cartao;
import modelo.FormaPagamento;
import modelo.Usuario;

public class CartaoRepository extends Repository<Cartao>{
	
	private static EntityManager em = JpaUtil.getEntityManager();

	public Cartao obterUm(int id) {
		Cartao car = em.find(Cartao.class,id);
		
		return car;
	}
	 
	 public List<Cartao> obterPorNome(String nome) {
		 	List<Cartao> car = new ArrayList<Cartao>();
			List<Cartao> lista = null;
			try {
				lista = obterTodos(Cartao.class);
				for(int i =0;i < lista.size();i++) {
					if(lista.get(i).getNome().equals(nome)) {
					
						car.add(lista.get(i));
						
						return car;
					}
					
				}
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return car;
		}
	 
	
	 public Cartao obterPorNumero(String numero) throws RepositoryException {
			List<Cartao> lista = obterTodos(Cartao.class);
			
			for(int i=0; i < lista.size(); i++) {
				if(lista.get(i).getNumero().equals(numero)) {
					return lista.get(i);
				}
			}
			return null;
		}
		
		
		
		
		
		
		
		
	}
	

