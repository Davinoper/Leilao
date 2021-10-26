package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Repository.LanceRepository;
import application.RepositoryException;
import modelo.Lance;

public class teste {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Leilao");
		
		EntityManager em = emf.createEntityManager();
		
		LanceRepository repo = new LanceRepository();
		
		
		List<Lance> lista = null;
		try {
			lista = repo.obterTodos(Lance.class);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i =0; i < lista.size(); i++ ) {
			System.out.println(lista.get(i));
		}
		

	}

}
