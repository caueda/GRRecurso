package br.com.grrecurso.managed;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ViewBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6201775605466116249L;
	private String page = "user/role.xhtml";

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
