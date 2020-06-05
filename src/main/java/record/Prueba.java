/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package record;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author soad_
 */
@Entity
public class Prueba implements Serializable{
    private String campo;
    @Id
    private int funciona;

    public Prueba() {
    }

    public String getCampo() {
        return campo;
    }

    public int getFunciona() {
        return funciona;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public void setFunciona(int funciona) {
        this.funciona = funciona;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.funciona;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prueba other = (Prueba) obj;
        if (this.funciona != other.funciona) {
            return false;
        }
        if (!Objects.equals(this.campo, other.campo)) {
            return false;
        }
        return true;
    }
    
}
