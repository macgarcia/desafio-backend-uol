package com.github.macgarcia.backend.uol.repository;

import com.github.macgarcia.backend.uol.model.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author macgarcia
 */
public class MemoryDataBase {
    
    private final Map<String, Usuario> repo = new HashMap<>();
    
    public Usuario getUsuario(String codinome) {
        return this.repo.get(codinome);
    }
    
    public void persiste(Usuario usuario) {
        this.repo.put(usuario.getCodinome(), usuario);
    }
    
    public List<Usuario> getAll() {
        return new ArrayList<>(this.repo.values());
    }
    
    public boolean existeCadastro(String codinome) {
        Usuario usuario = this.repo.get(codinome);
        return Objects.nonNull(usuario);
    }
    
    public void removerUsuario(String codinome) {
        this.repo.remove(codinome);
    }
    
}
