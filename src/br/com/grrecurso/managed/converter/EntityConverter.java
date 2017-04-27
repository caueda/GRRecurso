package br.com.grrecurso.managed.converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

@FacesConverter(value="EntityConverter")
@Named("entityConverterImpl")
@SuppressWarnings("all")
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
		if(object == null) return null;
		try {
			Class<? extends Object> clazz = object.getClass();
			for (Field f : clazz.getDeclaredFields()) {
				if (f.isAnnotationPresent(Id.class)) {
					f.setAccessible(true);
					Long id = (Long) f.get(object);
					return clazz.getCanonicalName() + ":" + id.toString();
				}
			}
			for(Method m : clazz.getDeclaredMethods()) {
				if(m.isAnnotationPresent(Id.class)) {
					try {
						Object r = m.invoke(object, null);
						return clazz.getCanonicalName() + ":" + r.toString();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}
		return null;
	}
}
