package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String nome;

	
	private String cpf;
	
	private String email;


	private String senha;

	private Perfil perfil;
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	
	private String telefone;
	@ManyToMany
	private List<Produto> listaProduto;
	@OneToMany
	private List<FormaPagamento> listaFormasPagamento;
	private boolean desativo;
	
	
	
	public Endereco getEndereco() {
		if(endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", senha=" + senha
				+ ", perfil=" + perfil + ", endereco=" + endereco.toString() + ", telefone=" + telefone + "]";
	}
	
	public List<Produto> getListaProduto() {
		if(listaProduto == null) {
			listaProduto = new ArrayList<Produto>();		}
		return listaProduto;
	}
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	public List<FormaPagamento> getListaFormasPagamento() {
		return listaFormasPagamento;
	}
	public void setListaFormasPagamento(List<FormaPagamento> listaFormasPagamento) {
		this.listaFormasPagamento = listaFormasPagamento;
	}
	public boolean isDesativo() {
		return desativo;
	}
	public void setDesativo(boolean desativo) {
		this.desativo = desativo;
	}
	
	
	
	
	
	
	
}
