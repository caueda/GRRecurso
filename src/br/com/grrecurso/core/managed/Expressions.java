package br.com.grrecurso.core.managed;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELContextEvent;
import javax.el.ExpressionFactory;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.jboss.weld.el.WeldELContextListener;
import org.jboss.weld.el.WeldExpressionFactory;
import org.jboss.weld.util.reflection.Reflections;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleResolver;

public class Expressions implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -793539584326683099L;

	
	@Inject
	protected BeanManager beanManager;
	
	public static final class Factory extends WeldExpressionFactory {
        public Factory() {
            super(new ExpressionFactoryImpl());
        }
    }

    public final static Pattern PATTERN = Pattern.compile("#\\{(.+?)\\}");
    public final static ExpressionFactory expressionFactory = ExpressionFactory.newInstance();

    final protected ELContext context;

    public Expressions() {
        // Chain the resolvers, the Weld resolver first, then
        // read-only "simple" bean/map/list resolvers
        CompositeELResolver compositeELResolver = new CompositeELResolver();
        compositeELResolver.add(beanManager.getELResolver());
        compositeELResolver.add(new SimpleResolver(true));
        context = new de.odysseus.el.util.SimpleContext(compositeELResolver);

        // Let Weld know about the context, so it can handle dependent
        // scope properly if beans are instantiated by EL expressions
        ELContextEvent event = new ELContextEvent(context);
        new WeldELContextListener().contextCreated(event);
    }

    public ExpressionFactory getExpressionFactory() {
        return expressionFactory;
    }

    public ELContext getContext() {
        return context;
    }

    public <T> T evaluateValueExpression(String expression, Class<T> expectedType) {
        Object result = getExpressionFactory()
            .createValueExpression(context, expression, expectedType).getValue(context);
        return result != null ? expectedType.cast(result) : null;
    }

    public <T> T evaluateValueExpression(String expression) {
        Object result = evaluateValueExpression(expression, Object.class);
        return result != null ? Reflections.<T>cast(result) : null;
    }

    public <T> T evaluateMethodExpression(String expression,
                                          Class<T> expectedReturnType,
                                          Object[] params,
                                          Class<?>[] expectedParamTypes) {
        Object result = getExpressionFactory()
        .createMethodExpression(context, expression, expectedReturnType,
                                expectedParamTypes).invoke(context, params);
        return result != null ? expectedReturnType.cast(result) : null;
    }

    public <T> T evaluateMethodExpression(String expression, Class<T> expectedReturnType) {
        return evaluateMethodExpression(
            expression, expectedReturnType, new Object[0], new Class[0]
            );
    }

    public <T> T evaluateMethodExpression(String expression) {
        Object result = evaluateMethodExpression(expression, Object.class);
        return result != null ? Reflections.<T>cast(result) : null;
    }

    public <T> T evaluateMethodExpression(String expression, Object... params) {
        Object result = evaluateMethodExpression(
            expression, Object.class, params, new Class[params.length]
        );
        return result != null ? Reflections.<T>cast(result) : null;
    }

    public String toExpression(String name) {
        return "#{" + name + "}";
    }

    public <T> void addVariableValue(String name, Class<T> type, T value) {
        getContext().getVariableMapper().setVariable(
            name, getExpressionFactory().createValueExpression(value, type)
        );
    }

    public String evaluateAllValueExpressions(String s) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = PATTERN.matcher(s);
        while (matcher.find()) {
            String expression = toExpression(matcher.group(1));
            Object result = evaluateValueExpression(expression);
            matcher.appendReplacement(sb, result != null ? result.toString() : "");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
