package br.com.grrecurso.managed.publico;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class GreetingDecorator implements Greeting{
    @Inject
    @Delegate
    @Any
    private Greeting greeting;

    @Override
    public String greet(String name) {
        return greeting.greet(name) + " after passing by GreetingDecorator!!!";
    }
}
