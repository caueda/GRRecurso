package br.com.grrecurso.managed.publico;

import javax.enterprise.inject.Alternative;

@Alternative
public class GreetingImpl2 implements Greeting {
    @Override
    public String greet(String name) {
        return "Olá, " + name + "!";
    }
}
