package controller;



import Repository.Repository;
import application.Message;
import application.RepositoryException;

public abstract class Controller<T> {

protected T entity = null;
	
	public Controller() {
		super();
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public abstract void limpar();

	public void adicionar() {
		Repository<T> repo = new Repository<T>();
		try {
			repo.adicionar(getEntity());
			limpar();
			Message.addInfoMessage("Salvo com sucesso.");
		} catch (RepositoryException e) {
			Message.addErrorMessage("Problemas ao executar a função!");
		}
		
	}
	
	
	public void altera() {
		Repository<T> repo = new Repository<T>();
		try {
			repo.altera(getEntity());
			limpar();
			Message.addInfoMessage("Alterado com sucesso.");
		} catch (Exception e) {
			Message.addErrorMessage("Problemas ao executar a função!");
			e.printStackTrace();
		}
		
		
		
		
	}

	public void remove() {
		Repository<T> repo = new Repository<T>();
		try {
			repo.remove(getEntity());
			limpar();
			Message.addInfoMessage("Removido com sucesso.");
		} catch (RepositoryException e) {
			Message.addErrorMessage("Problemas ao executar a função!");
		}
		
	}

	
	
	
}
