package br.com.grrecurso.core.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.hibernate.jdbc.ReturningWork;

import br.com.grrecurso.core.managed.CriteriaBean;
import br.com.grrecurso.core.persistence.GenericEntity;

@Stateless
public class GenericService extends AbstractService<GenericEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7821349291779399207L;

	protected GenericService() {
		super(GenericEntity.class);
	}
	
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
						"   AND lower(p.nome) = lower(?)";
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> list(Map<String, CriteriaBean> filter, Class<T> clazzEntity){
		List<T> result = null;
		int cont = 0;
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT vo FROM " + clazzEntity.getName() + " vo WHERE 1=1 ");
		for(Map.Entry<String, CriteriaBean> entry : filter.entrySet()){
			CriteriaBean bean = entry.getValue();
			if(bean.getClazz() == null){
				hql.append(" and lower(vo." + entry.getKey() + ")" + bean.getOperacao().replace(":value", bean.getSingleValue().toString()));
			} else if(bean.getClazz().isEnum()) {
				String parameterName = entry.getKey() + "" + ++cont;
				hql.append(" and vo." + entry.getKey() + bean.getOperacao().replace(":value", ":" + parameterName));
				Class<Enum> enumerator = (Class<Enum>) bean.getClazz();
				Enum value = Enum.valueOf(enumerator, bean.getSingleValue().toString());
				parameters.put(parameterName, value);
			}
		}
		Query query = getSession().createQuery(hql.toString());
		for(Entry<String, Object> entry : parameters.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		result = query.list();
		return result;
	}
}
