package br.com.grrecurso.dominio;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

@SuppressWarnings("all")
public class EnumUserType implements UserType, ParameterizedType {
	private Class enumClass;
	private Method enumMethod;
	private Method getDominio;
	private Class<?> returnType;

	public int[] sqlTypes() {
		return new int[] { Types.CHAR };
	}

	public Class returnedClass() {
		return enumClass;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return ObjectUtils.nullSafeEquals(x, y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		Object returnVal = null;
		
		if (value == null)
			return null;
		else {
			try {				
				Enum enumInstance = (Enum)enumClass.getEnumConstants()[0];
				returnVal = getDominio.invoke(enumInstance, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnVal;
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		String object = null;
		
		if (value == null) {
			st.setObject(index, null);
		} else {
			try {
				Object param = enumMethod.invoke(((Enum) value), new Object[]{});				
				st.setObject(index,param, Types.OTHER);
				st.setString(index, object);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		Object deepCopy = deepCopy(value);

		if (!(deepCopy instanceof Serializable))
			return (Serializable) deepCopy;

		return null;

	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}

	@SuppressWarnings("unchecked")
	public void setParameterValues(Properties parameters) {
		if (parameters != null) {			
			String methodName = parameters.getProperty("method");
			String className = parameters.getProperty("enumClassName");

			try {
				enumClass = Class.forName(className);
				Method getMethod = enumClass.getMethod(methodName, new Class[] {});
				returnType = getMethod.getReturnType();
				enumMethod = enumClass.getMethod(methodName, new Class[]  {});
				getDominio = enumClass.getMethod("getDominio", new Class[]{String.class});
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

	}
}