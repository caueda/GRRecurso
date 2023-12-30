package br.com.grrecurso.managed.usuario;

import br.com.grrecurso.entities.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEvent {
    private Usuario usuario;
}
