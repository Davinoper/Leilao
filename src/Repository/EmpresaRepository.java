package Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Empresa;
import modelo.Produto;

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
	 
		
		
		public Empresa desativar(Empresa empresa) {
			Empresa emp = null;
			if(empresa.isDesativo() == false) {
				empresa.setDesativo(true);
			}
			else {
				
				empresa.setDesativo(false);
			}
			
			em.getTransaction().begin();
			emp = em.merge(empresa);
			em.getTransaction().commit();
			
			return emp;
			
			
		}
	 
}
