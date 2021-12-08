package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import Repository.EmpresaRepository;
import Repository.ProdutoRepository;
import application.JpaUtil;
import application.Message;
import application.RepositoryException;
import application.Upload;
import listing.EmpresaListing;
import modelo.Categoria;
import modelo.Empresa;
import modelo.Produto;


@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5283872523256589679L;
	private List<Produto> listaProd;
	private Categoria listaCategoria[];

	private List<Empresa> listaEmpresa;
	private InputStream fotoInputStream = null;


	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
		}
		return entity;
	}

	@Override
	public void limpar() {
		setEntity(null);

	}

	public void desativar() {
		ProdutoRepository repo = new ProdutoRepository();
		Produto prod =repo.desativar(getEntity());
		if(prod.isDesativo() == true) {
			Message.addInfoMessage("Produto desativado com sucesso.");
		}
		else if(prod.isDesativo() == false) {
			Message.addInfoMessage("Produto reativado com sucesso");
		}
		limpar();
	}
	
	public void adiciona() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			Produto p = repo.adicionar(getEntity());
			
			if (getFotoInputStream() != null) {
				// salvando a imagem
				if (! Upload.saveImageProduto(fotoInputStream, "png", p.getId())) {
					Message.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do produto.");
					return;
				}
			}
			limpar();
			Message.addInfoMessage("Produto adicionado com sucesso!");
		} catch (RepositoryException e) {
			Message.addInfoMessage("Erro ao adicionar produto.");
			e.printStackTrace();
		}
		
	}


	public Categoria[] getListaCategoria() {
		if (listaCategoria == null) {
			listaCategoria = Categoria.values();
		}
		return listaCategoria;
	}

	public void editar(int id) {
		EntityManager em = JpaUtil.getEntityManager();
		setEntity(em.find(Produto.class, id));
	}

	public List<Produto> getListaProd() {
		ProdutoRepository repo = new ProdutoRepository();
		if (listaProd == null) {
			try {
				listaProd = repo.obterTodos(Produto.class);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return listaProd;
	}

	public void setListaProd(List<Produto> listaProd) {
		this.listaProd = listaProd;
	}

	public List<Empresa> getListaEmpresa() {
		EmpresaRepository repo = new EmpresaRepository();
		if(listaEmpresa == null) {
			try {
				listaEmpresa= repo.obterTodos(Empresa.class);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaEmpresa;
	}
	
	
	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				setFotoInputStream(uploadFile.getInputStream());
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Message.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Message.addErrorMessage("O tipo da image deve ser png.");
		}
		if (getFotoInputStream() != null) {
			// salvando a imagem
			if (! Upload.saveImageProduto(fotoInputStream, "png", getEntity().getId())) {
				Message.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do produto.");
				return;
			}
		}

	}
	
	
	
	
	public void abrirEmpresaListing() {
		EmpresaListing listing = new EmpresaListing();
		listing.open();
	}
	
	public void obterEmpresaListing(SelectEvent<Empresa> event) {
		getEntity().setEmpresa(event.getObject());
	}

	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}

	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}


}
