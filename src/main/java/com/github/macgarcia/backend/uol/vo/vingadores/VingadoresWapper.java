package com.github.macgarcia.backend.uol.vo.vingadores;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author macgarcia
 */
public class VingadoresWapper implements Serializable {

    private List<Heroi> vingadores;

    public List<Heroi> getVingadores() {
        return vingadores;
    }

    public void setVingadores(List<Heroi> vingadores) {
        this.vingadores = vingadores;
    }
    
}
