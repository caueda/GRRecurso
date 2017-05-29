package br.com.grrecurso.core.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.ReturningWork;

import br.com.grrecurso.core.managed.CriteriaBean;
import br.com.grrecurso.core.persistence.GenericEntity;
import br.com.grrecurso.core.persistence.IComboSelect;
import br.com.grrecurso.core.security.annotation.IgnorarPermissoes;

@Stateless
public class GenericService extends AbstractService<GenericEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7821349291779399207L;

	protected GenericService() {
		super(GenericEntity.class);
	}
	
	@IgnorarPermissoes
	public boolean isInRole(Long idUsuario, String role) {
		Integer count = getSession().doReturningWork(new ReturningWork<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				String sql = 
						"SELECT count(r.id_role) qtde\n" + 
						"  FROM usuario u\n" + 
						"  JOIN usuario_perfil_usuario upu on u.id_usuario = upu.id_usuario\n" + 
						"  JOIN perfil_usuario pu on pu.id_perfil_usuario = upu.id_perfil_usuario\n" + 
						"  JOIN perfil_usuario_role pur on pur.id_perfil_usuario = pu.id_perfil_usuario\n" + 
						"  JOIN role r on r.id_role = pur.id_role\n" +						 
						" WHERE u.id_usuario = ?\n" + 
						"   AND lower(r.nome) = lower(?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, idUsuario);
				ps.setString(2, role);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					return rs.getInt("qtde");
				}
				return 0;
			}
		});
		
		return count.intValue() > 0;
	}
	
	@IgnorarPermissoes
	public boolean isPermitido(Long idUsuario, String permissao) {
		Integer count = getSession().doReturningWork(new ReturningWork<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				String sql = 
						"SELECT count(p.id_permissao) qtde\n" + 
						"  FROM usuario u\n" + 
						"  JOIN usuario_perfil_usuario upu on u.id_usuario = upu.id_usuario\n" + 
						"  JOIN perfil_usuario pu on pu.id_perfil_usuario = upu.id_perfil_usuario\n" + 
						"  JOIN perfil_usuario_role pur on pur.id_perfil_usuario = pu.id_perfil_usuario\n" + 
						"  JOIN role r on r.id_role = pur.id_role\n" + 
						"  JOIN role_permissao rp on rp.id_role = r.id_role\n" + 
						"  JOIN permissao p on p.id_permissao = rp.id_permissao\n" + 
						" WHERE u.id_usuario = ?\n" + 
						"   AND lower(p.action) = lower(?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, idUsuario);
				ps.setString(2, permissao);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					return rs.getInt("qtde");
				}
				return 0;
			}
		});
		
		return count.intValue() > 0;
	}
	
	private boolean isIComboSelect(Class<?> clazz){
		Class<?>[] interfaces = clazz.getInterfaces();
		for(Class<?> i : interfaces){
			if(i.getName().equals(IComboSelect.class.getName())){
				return true;
			}
		}
		return false;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> list(Map<String, CriteriaBean> filter, Class<T> clazzEntity){
		List<T> result = null;
		
		Criteria criteria = getSession().createCriteria(clazzEntity);
		
		for(Map.Entry<String, CriteriaBean> entry : filter.entrySet()){
			CriteriaBean bean = entry.getValue();
			if(bean.getClazz() == null){
				if(bean.getOperacao().startsWith("TEXT_")){
					switch(bean.getOperacao()){
						case "TEXT_START_WITH" :
							criteria.add(Restrictions.ilike(entry.getKey(), bean.getSingleValue(), MatchMode.START));
							break;
						case "TEXT_ENDS_WITH":
							criteria.add(Restrictions.ilike(entry.getKey(), bean.getSingleValue(), MatchMode.END));
							break;
						case "TEXT_IGUAL":
							criteria.add(Restrictions.ilike(entry.getKey(), bean.getSingleValue(), MatchMode.EXACT));
							break;
					}
				} else if(bean.getOperacao().startsWith("NUMBER_")){
					//TODO
				} else if(bean.getOperacao().startsWith("DATE_")){
					
				}
			} else if(bean.getClazz().isEnum()) {
				Class<Enum> enumerator = (Class<Enum>) bean.getClazz();
				if(bean.getOperacao().startsWith("SELECT_")){
					switch(bean.getOperacao()){
						case "SELECT_IGUAL" :
							criteria.add(Restrictions.eq(entry.getKey(), Enum.valueOf(enumerator, bean.getSingleValue().toString())));
							break;						
					}
				} 
			} else if(isIComboSelect(bean.getClazz())){
				if(bean.getOperacao().startsWith("SELECT_")){
					
					switch(bean.getOperacao()){
						case "SELECT_IGUAL" :
							criteria.add(Restrictions.eq(entry.getKey(), getSession().load(bean.getClazz(), Long.valueOf(bean.getSingleValue()))));
							break;						
					}
				} 
			}
		}
		
		result = criteria.list();
		return result;
	}
}
