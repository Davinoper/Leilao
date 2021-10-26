package Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Empresa;

public class EmpresaRepository extends Repository<Empresa>{

	private static EntityManager em = JpaUtil.getEntityManager();
	
	 public Empresa obterUm(int id) {
		Empresa emp = em.find(Empresa.class,id);
		
		return emp;
	}
	 
	 public List<Empresa> obterPorNome(String nome) {
		 	List<Empresa> emp = new ArrayList<Empresa>();
			List<Empresa> lista = null;
			try {
				lista = obterTodos(Empresa.class);
				for(int i =0;i < lista.size();i++) {
					if(lista.get(i).getNome().equals(nome)) {
					
						emp.add(lista.get(i));
						
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
