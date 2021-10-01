package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Usuario;

public class teste {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Leilao");
		
		EntityManager em = emf.createEntityManager();
		
		Usuario usu = new Usuario();
		usu.setCpf(null);
		usu.setEmail(null);
		usu.setNome(null);
		usu.setSenha(null);
		
		em.getTransaction().begin();
		Usuario teste = em.merge(usu);
		em.getTransaction().commit();
		
		System.out.println(teste.toString());
		

	}

}
