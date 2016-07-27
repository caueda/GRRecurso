package br.com.grrecurso.managed.converter;

import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

@Named("entityConverterImpl")
public class EntityConverter implements Converter {
	
	@PersistenceContext
	private EntityManager em;

	public Object getAsObject(FacesContext fc, UIComponent component, String string) {
		try {
			String[] split = string.split(":");
			return em.find(Class.forName(split[0]), Long.valueOf(split[1]));
		} catch (NumberFormatException | ClassNotFoundException e) {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent component, Object object) {
		try {
			Class<? extends Object> clazz = object.getClass();
			for (Field f : clazz.getDeclaredFields()) {
				if (f.isAnnotationPresent(Id.class)) {
					f.setAccessible(true);
					Long id = (Long) f.get(object);
					return clazz.getCanonicalName() + ":" + id.toString();
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}
		return null;
	}
}
