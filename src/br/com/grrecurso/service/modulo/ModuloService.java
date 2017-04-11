package br.com.grrecurso.service.modulo;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.grrecurso.core.service.AbstractService;
import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.entities.usuario.Usuario;

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
		criteria.add(Restrictions.eq("status", DominioAtivoInativo.ATIVO));
		criteria.addOrder(Order.asc("nome"));
		return (List<Modulo>)criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Modulo> listaModulosExcept(Long idUsuario){
		StringBuilder hql = new StringBuilder();
		hql.append("select m from " + Modulo.class.getSimpleName())
		   .append(" m ")
		   .append(" where m not in ( ")
		   .append("select m from " + Usuario.class.getSimpleName())
		   .append(" u ")
		   .append(" join u.modulos m )");
		Query q = getSession().createQuery(hql.toString());
		return (List<Modulo>)q.list();
	}
}
