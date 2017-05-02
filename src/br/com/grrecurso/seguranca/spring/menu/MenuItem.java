package br.com.grrecurso.seguranca.spring.menu;

import javax.faces.component.FacesComponent;

@FacesComponent(MenuItem.COMPONENT_TYPE)
public class MenuItem extends org.primefaces.component.menuitem.UIMenuItem {
	
	public static final String COMPONENT_TYPE = "br.com.grrecurso.seguranca.spring.menu.MenuItem";
	
	private String permissao;
	
	public MenuItem() {
		super();
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		if(permissao.equals("itemMenuTeste")) {
			setRendered(false);
		}
		this.permissao = permissao;
	}
}
