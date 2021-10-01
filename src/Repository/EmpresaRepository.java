package Repository;

import java.util.List;

import javax.persistence.EntityManager;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Empresa;


public class EmpresaRepository extends Repository<Empresa> {
	private static EntityManager em = JpaUtil.getEntityManager();
	
	 public Empresa obterUm(int id) {
		Empresa emp = em.find(Empresa.class,id);
		
		return emp;
	}
	 
	 public Empresa obterPorNome(String nome) {
		 	Empresa emp = null;
			List<Empresa> lista;
			try {
				lista = obterTodos(Empresa.class);
				for(int i =0;i < lista.size();i++) {
					if(lista.get(i).getNome().equals(nome)) {
						
						emp = lista.get(i);
						return emp;
					}
					
				}
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return emp;
		}
	 

	
}
