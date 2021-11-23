package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.event.SelectEvent;

import Repository.EmpresaRepository;
import Repository.EnderecoRepository;
import application.JpaUtil;
import application.Message;
import application.RepositoryException;
import listing.EmpresaListing;
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
	private List<Estado> listaEstados;
	
	
	
	
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
			Message.addErrorMessage("Problemas ao executar a função!");
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
	
	public List<Estado> getListaEstados() {
		
		if(listaEstados == null) {
			listaEstados = Arrays.asList(Estado.values());
		}
		return listaEstados;
		
		
	}
	
	
	public void desativar() {
		EmpresaRepository repo = new EmpresaRepository();
		Empresa emp =repo.desativar(getEntity());
		if(emp.isDesativo() == true) {
			Message.addInfoMessage("Empresa desativada com sucesso.");
		}
		else if(emp.isDesativo() == false) {
			Message.addInfoMessage("Empresa reativada com sucesso");
		}
		limpar();
		
		
	}
	
	public void editar(int id) {
		EntityManager em = JpaUtil.getEntityManager();
		setEntity(em.find(Empresa.class, id));
	}
	
	
	public void abrirEmpresaListing() {
		EmpresaListing listing = new EmpresaListing();
		listing.open();
	}
	
	public void obterEmpresaListing(SelectEvent<Empresa> event) {
		setEntity(event.getObject());
	}


	public List<Estado> complete(String query){
		
        List<Estado> lista = getListaEstados();
      
      
        

        return lista;
	}
		
	

	
}
