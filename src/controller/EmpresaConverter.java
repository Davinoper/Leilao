package controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import Repository.EmpresaRepository;
import modelo.Empresa;

@FacesConverter(value = "empresaconverter", forClass = Empresa.class)
public class EmpresaConverter implements Converter<Empresa> {

	

	@Override
	public Empresa getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		EmpresaRepository repo = new EmpresaRepository();
		Empresa empresa = repo.obterPorNome(value);
		return empresa;

		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Empresa value) {
		if (value == null) {
			return null;
		}
		
		String empresa = value.toString();
		
		return empresa;
	}

}
