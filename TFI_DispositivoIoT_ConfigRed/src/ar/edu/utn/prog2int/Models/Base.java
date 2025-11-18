package ar.edu.utn.prog2int.Models;


public abstract class Base {
    
    protected Long id; // PK autoincrement
    protected boolean eliminado; 

    public Base() {
        this.eliminado = false;
    }

    public Base(Long id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Base)) {
            return false;
        }       
        Base other = (Base) obj;
        return id != null && id.equals(other.id);
    }
    
    
    
}
