package br.com.grrecurso.service.modulo;

import javax.ejb.Stateless;

import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.service.AbstractService;

@Stateless
public class ModuloService extends AbstractService<Modulo, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6453031716153136576L;

	protected ModuloService() {
		super(Modulo.class);
	}
}
