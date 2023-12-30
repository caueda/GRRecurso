package br.com.grrecurso.managed.publico;

import java.io.Serializable;

public interface Greeting extends Serializable {
    String greet(String name);
}
