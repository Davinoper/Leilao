package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Repository.EmpresaRepository;
import Repository.EnderecoRepository;
import application.Message;
import application.RepositoryException;
import modelo.Empresa;
import modelo.Estado;

@Named
@ViewScoped
public class EmpresaController extends Controller<Empresa> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2430920407443528283L;
	private List<Empresa> listaEmpresa;
	private Estado[] listaEstados;
	
	
	
	
	@Override
	public Empresa getEntity() {
		if (entity == null) {
			entity = new Empresa();
		}
		return entity;
	}

	@Override
	public void limpar() {
		setEntity(null);
		
	}
	
	public void adicionar() {
		EmpresaRepository repo = new EmpresaRepository();
		EnderecoRepository repo2 = new EnderecoRepository();
		try {
			Empresa emp =repo.adicionar(getEntity());
			repo2.adicionarEnd(emp.getEndereco().getId(),getEntity().getEndereco());
			limpar();
			Message.addInfoMessage("Salvo com sucesso.");
		} catch (RepositoryException e) {
			Message.addErrorMessage("Problemas ao executar a fun��o!");
		}
		
	}
	

	public List<Empresa> getListaEmpresa() {
		EmpresaRepository repo = new EmpresaRepository();
		if(listaEmpresa == null) {
			try {
				listaEmpresa = repo.obterTodos(Empresa.class);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaEmpresa;
	}

	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}
	
	public Estado[] getListaEstados() {
		if(listaEstados == null) {
			listaEstados = Estado.values();
		}
		return listaEstados;
		
		
	}
	
	
	
	
	
	
	
}