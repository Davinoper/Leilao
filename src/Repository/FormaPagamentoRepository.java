package Repository;


import java.util.List;

import javax.persistence.EntityManager;

import application.JpaUtil;
import application.RepositoryException;
import modelo.Cartao;
import modelo.FormaPagamento;


public class FormaPagamentoRepository extends Repository<FormaPagamento>{
	
	private static EntityManager em = JpaUtil.getEntityManager();

	public FormaPagamento obterUm(int id) {
		FormaPagamento car = em.find(FormaPagamento.class,id);
		
		return car;
	}
	 
	



}
