package com.github.macgarcia.backend.uol.vo.vingadores;

import java.io.Serializable;

/**
 *
 * @author macgarcia
 */
public class Heroi implements Serializable {

    private String codinome;

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    @Override
    public String toString() {
        return "Heroi{" + "codinome=" + codinome + '}';
    }
    
}
