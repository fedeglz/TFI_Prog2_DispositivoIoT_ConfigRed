package ar.edu.utn.prog2int.Models;


public abstract class Base {
    
    protected int id; // PK autoincrement
    protected boolean eliminado; 

    public Base() {
        this.eliminado = false;
    }

    public Base(int id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return Integer.hashCode(id);
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
        return this.id == other.id;
    }
    
    
    
}
