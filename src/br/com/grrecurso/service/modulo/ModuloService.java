package br.com.grrecurso.service.modulo;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

import org.hibernate.Criteria;

import br.com.grrecurso.core.service.AbstractService;
import br.com.grrecurso.entities.usuario.Modulo;

@Stateless
public class ModuloService extends AbstractService<Modulo, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6453031716153136576L;

	protected ModuloService() {
		super(Modulo.class);
	}
	
	@SuppressWarnings("unchecked")
	@Produces
	public List<Modulo> listaModulos(){
		Criteria criteria = getSession().createCriteria(Modulo.class);
		return (List<Modulo>)criteria.list();
	}
}
