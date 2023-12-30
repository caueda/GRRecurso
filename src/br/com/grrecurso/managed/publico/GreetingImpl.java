package br.com.grrecurso.managed.publico;

import javax.enterprise.inject.Default;
import javax.inject.Named;

@Named @Default
public class GreetingImpl implements Greeting {
    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
