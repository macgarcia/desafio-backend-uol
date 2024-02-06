package com.github.macgarcia.backend.uol.vo.ligadajustica;

import java.util.List;

/**
 *
 * @author macgarcia
 */
public class LigaDaJustica {
    
    private List<String> codinomes;

    public List<String> getCodinomes() {
        return codinomes;
    }

    public void setCodinomes(List<String> codinomes) {
        this.codinomes = codinomes;
    }

    @Override
    public String toString() {
        return "LigaDaJustica{" + "codinomes=" + codinomes + '}';
    }
    
}
